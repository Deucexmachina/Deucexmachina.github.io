package OOP;

import java.io.*;
import java.util.*;

class BorrowTransaction implements Serializable {
    private String borrowerId;
    private String materialId;
    private Date borrowDate;
    private Date dueDate;

    public BorrowTransaction(String borrowerId, String materialId, Date borrowDate, Date dueDate) {
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
    private static final String TRANSACTION_FILE = "transactions.txt";
    private static ArrayList<BorrowTransaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        loadTransactions();
        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n--- Borrow Menu ---");
            System.out.println("1. Borrow Material");
            System.out.println("2. View Borrowed Materials");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    borrowMaterial(scanner);
                    break;
                case 2:
                    viewBorrowedMaterials();
                    break;
                case 3:
                    saveTransactions();
                    System.out.println("Exiting borrow menu. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);
    }

    private static void borrowMaterial(Scanner scanner) {
        System.out.println("\n--- Borrow Material ---");

        System.out.print("Enter Borrower ID: ");
        String borrowerId = scanner.nextLine();

        Borrower borrower = BorrowersManagement.borrowers.stream()
                .filter(b -> b.getId().equals(borrowerId))
                .findFirst()
                .orElse(null);

        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }

        if (borrower.getViolations() >= 3) {
            System.out.println("Borrower has 3 strikes. Borrowing not allowed.");
            return;
        }

        if (transactions.stream().anyMatch(t -> t.getBorrowerId().equals(borrowerId))) {
            System.out.println("Borrower already has a borrowed material.");
            return;
        }

        System.out.print("Enter Material ID: ");
        String materialId = scanner.nextLine();

        Material material = AssetManagement.materials.stream()
                .filter(m -> m.getMaterialId().equals(materialId))
                .findFirst()
                .orElse(null);

        if (material == null) {
            System.out.println("Material not found.");
            return;
        }

        if (material.copies <= 0) {
            System.out.println("No copies of this material are available for borrowing.");
            return;
        }

        int borrowDays = switch (material.getMaterialType().toLowerCase()) {
            case "book" -> 7;
            case "journal" -> 3;
            case "magazine" -> 0; // Same-day return
            case "thesis book" -> 2;
            default -> {
                System.out.println("Unknown material type.");
                return;
            }
        };

        Date borrowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrowDate);
        calendar.add(Calendar.DATE, borrowDays);
        Date dueDate = borrowDays == 0 ? borrowDate : calendar.getTime();

        BorrowTransaction transaction = new BorrowTransaction(borrowerId, materialId, borrowDate, dueDate);
        transactions.add(transaction);
        material.setCopies(material.copies - 1);

        System.out.println("Borrowing successful! Due date: " + dueDate);
    }

    private static void viewBorrowedMaterials() {
        System.out.println("\n--- Borrowed Materials ---");
        if (transactions.isEmpty()) {
            System.out.println("No transactions to display.");
        } else {
            transactions.forEach(System.out::println);
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadTransactions() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TRANSACTION_FILE))) {
            transactions = (ArrayList<BorrowTransaction>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No saved transactions found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }

    private static void saveTransactions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRANSACTION_FILE))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }
}
