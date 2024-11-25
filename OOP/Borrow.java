package OOP;

import java.io.*;
import java.util.*;

class BorrowRecord implements Serializable {
    private String borrowerId;
    private String materialId;
    private Date borrowDate;
    private Date dueDate;

    public BorrowRecord(String borrowerId, String materialId, Date borrowDate, Date dueDate) {
        this.borrowerId = borrowerId;
        this.materialId = materialId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "Borrower ID: " + borrowerId + ", Material ID: " + materialId +
               ", Borrow Date: " + borrowDate + ", Due Date: " + dueDate;
    }
}

public class Borrow {
    private static final String BORROW_FILE = "borrow_records.txt";
    private static ArrayList<BorrowRecord> borrowRecords = new ArrayList<>();

    public static void main(String[] args) {
        loadBorrowRecords();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Borrow System ---");
            System.out.println("1. Borrow Material");
            System.out.println("2. View Borrow Records");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: borrowMaterial(scanner);
                break;
                case 2: viewBorrowRecords();
                break;
                case 3: {
                    saveBorrowRecords();
                    System.out.println("Exiting system. Goodbye!");
                }
                break;
                default: System.out.println("Invalid choice. Please try again.");
                break;
            }
        } while (choice != 3);
    }

    private static void borrowMaterial(Scanner scanner) {
        System.out.println("\n--- Borrow Material ---");

        System.out.print("Enter Borrower ID: ");
        String borrowerId = scanner.nextLine();
        Borrower borrower = BorrowersManagement.getBorrowers().stream()
            .filter(b -> b.getId().equals(borrowerId))
            .findFirst()
            .orElse(null);

        if (borrower == null) {
            System.out.println("Borrower not found. Please register the borrower first.");
            return;
        }

        if (borrower.getViolations() >= 3) {
            System.out.println("Borrower has 3 violations. Cannot borrow materials.");
            return;
        }

        if (borrowRecords.stream().anyMatch(r -> r.getBorrowerId().equals(borrowerId))) {
            System.out.println("Borrower already has a borrowed material. Return it first.");
            return;
        }

        System.out.print("Enter Material ID: ");
        String materialId = scanner.nextLine();
        Material material = AssetManagement.getMaterials().stream()
            .filter(m -> m.getMaterialId().equals(materialId))
            .findFirst()
            .orElse(null);

        if (material == null) {
            System.out.println("Material not found. Please add it first.");
            return;
        }

        if (material.copies <= 0) {
            System.out.println("No copies available for this material.");
            return;
        }

        Calendar calendar = Calendar.getInstance();
        int borrowDays;

        switch (material.getMaterialType().toLowerCase()) {
            case "book": borrowDays = 7; break;
            case "journal": borrowDays = 3; break;
            case "magazine": borrowDays = 0; break;
            case "thesis book": borrowDays = 2; break;
            default: System.out.println("Invalid material type."); return;
        }

        Date borrowDate = calendar.getTime();
        if (borrowDays > 0) {
            calendar.add(Calendar.DAY_OF_YEAR, borrowDays);
        }
        Date dueDate = calendar.getTime();

        BorrowRecord record = new BorrowRecord(borrowerId, materialId, borrowDate, dueDate);
        borrowRecords.add(record);

        material.setCopies(material.copies - 1);

        System.out.println("Material borrowed successfully!");
        System.out.println("Borrow Date: " + borrowDate);
        System.out.println("Due Date: " + (borrowDays > 0 ? dueDate : "End of Day"));
    }

    private static void viewBorrowRecords() {
        System.out.println("\n--- Borrow Records ---");
        if (borrowRecords.isEmpty()) {
            System.out.println("No borrow records to display.");
        } else {
            borrowRecords.forEach(System.out::println);
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadBorrowRecords() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BORROW_FILE))) {
            borrowRecords = (ArrayList<BorrowRecord>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No saved borrow records found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading borrow records: " + e.getMessage());
        }
    }

    private static void saveBorrowRecords() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BORROW_FILE))) {
            oos.writeObject(borrowRecords);
        } catch (IOException e) {
            System.out.println("Error saving borrow records: " + e.getMessage());
        }
    }
}
