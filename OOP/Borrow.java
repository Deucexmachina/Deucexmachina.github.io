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

    static {
        loadBorrowRecords();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        borrowMaterial(scanner);
    }

    private static void borrowMaterial(Scanner scanner) {
        loadBorrowRecords();
        System.out.println("\n--- Borrow System ---");

        System.out.print("Enter Borrower ID: ");
        String borrowerId = scanner.nextLine();

        ArrayList<Borrower> borrowers = BorrowersManagement.getBorrowers();
        Borrower borrower = borrowers.stream()
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

        ArrayList<Material> materials = AssetManagement.getMaterials();
        Material material = materials.stream()
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

        logBorrowToHistoryFile(record);

        material.setCopies(material.copies - 1);

        System.out.println("Material borrowed successfully!");
        System.out.println("Borrow Date: " + borrowDate);
        System.out.println("Due Date: " + (borrowDays > 0 ? dueDate : "End of Day"));

        AssetManagement.saveAssets();
        saveBorrowRecords();
    }

    @SuppressWarnings("unchecked")
    public static void loadBorrowRecords() {
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

    private static void logBorrowToHistoryFile(BorrowRecord record) {
        try (FileWriter fw = new FileWriter("historyRecords.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("Borrowed: " + record);
        } catch (IOException e) {
            System.out.println("Error logging borrow transaction: " + e.getMessage());
        }
    }
}
