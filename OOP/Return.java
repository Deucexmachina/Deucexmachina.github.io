package OOP;

import java.io.*;
import java.util.*;

public class Return {
    private static final String BORROW_FILE = "borrow_records.txt";
    private static ArrayList<BorrowRecord> borrowRecords = new ArrayList<>();

    static {
        loadBorrowRecords();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        returnMaterial(scanner);
    }

    public static void returnMaterial(Scanner scanner) {
        loadBorrowRecords();
        System.out.println("\n--- Return System ---");

        System.out.print("Please Enter Borrower ID: ");
        String borrowerId = scanner.nextLine();

        ArrayList<Borrower> borrowers = BorrowersManagement.getBorrowers();
        Borrower borrower = borrowers.stream()
                .filter(b -> b.getId().equals(borrowerId))
                .findFirst()
                .orElse(null);

        if (borrower == null) {
            System.out.println("No borrower record was found for this ID.");
            return;
        }

        BorrowRecord record = borrowRecords.stream()
                .filter(r -> r.getBorrowerId().equals(borrowerId))
                .findFirst()
                .orElse(null);

        if (record == null) {
            System.out.println("No borrow record found for this Borrower ID.");
            return;
        }

        ArrayList<Material> materials = AssetManagement.getMaterials();
        Material material = materials.stream()
                .filter(m -> m.getMaterialId().equals(record.getMaterialId()))
                .findFirst()
                .orElse(null);

        if (material == null) {
            System.out.println("Material not found in the system.");
            return;
        }

        Date returnDate = new Date();
        Date dueDate = record.getDueDate();

        if (returnDate.after(dueDate)) {
            System.out.println("Material was returned late. Adding a strike to the borrower's record.");
            borrower.setViolations(borrower.getViolations() + 1);
        } else {
            System.out.println("Material was returned on time.");
        }

        logReturnToHistoryFile(borrower.getId(), record.getMaterialId(), returnDate);

        material.copies += 1;
        borrowRecords.remove(record);

        System.out.println("Material was returned successfully!");
        AssetManagement.saveAssets();
        saveBorrowRecords();
    }

    @SuppressWarnings("unchecked")
    private static void loadBorrowRecords() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BORROW_FILE))) {
            Object readObject = ois.readObject();
            if (readObject instanceof ArrayList<?>) {
                borrowRecords = (ArrayList<BorrowRecord>) readObject;
            } else {
                throw new ClassNotFoundException("Data type mismatch in borrow records file.");
            }
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

    private static void logReturnToHistoryFile(String borrowerId, String materialId, Date returnDate) {
        try (FileWriter fw = new FileWriter("historyRecords.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("Returned: Borrower ID: " + borrowerId + ", Material ID: " + materialId + ", Return Date: " + returnDate);
        } catch (IOException e) {
            System.out.println("Error logging return transaction: " + e.getMessage());
        }
    }    
}
