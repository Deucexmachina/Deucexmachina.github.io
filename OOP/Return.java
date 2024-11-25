package OOP;

import java.io.*;
import java.util.*;

public class Return {
    private static final String BORROW_FILE = "borrow_books_library.txt";
    private static ArrayList<BorrowRecord> borrowRecords = new ArrayList<>();

    static {
        loadBorrowRecords();
    }
    
    public static void returnMaterial(Scanner scanner) {
        System.out.println("\n=================== Return Material ===================");

        System.out.print("Please Enter Borrower ID: ");
        String borrowerId = scanner.nextLine();
        BorrowRecord record = borrowRecords.stream()
                .filter(r -> r.getBorrowerId().equals(borrowerId))
                .findFirst()
                .orElse(null);

        if (record == null) {
            System.out.println("No borrow record was found from this ID.");
            return;
        }

        Borrower borrower = BorrowersManagement.getBorrowers().stream()
                .filter(b -> b.getId().equals(borrowerId))
                .findFirst()
                .orElse(null);

        if (borrower == null) {
            System.out.println("Borrower was not found. Please register the borrower first.");
            return;
        }

        Material material = AssetManagement.getMaterials().stream()
                .filter(m -> m.getMaterialId().equals(record.getMaterialId()))
                .findFirst()
                .orElse(null);

        if (material == null) {
            System.out.println("Material was not found in the Library System's records.");
            return;
        }

        Date returnDate = new Date();
        if (returnDate.after(record.getDueDate())) {
            System.out.println("Material was returned late. Adding a strike to the borrower's record.");
            borrower.setViolations(borrower.getViolations() + 1);
        } else {
            System.out.println("Material was returned on time.");
        }

        material.setCopies(material.copies + 1);
        borrowRecords.remove(record);

        System.out.println("Material was returned successfully!");
        saveBorrowRecords();
    }

    public static void viewBorrowRecords() {
        System.out.println("\n=================== Borrow Records ===================");
        if (borrowRecords.isEmpty()) {
            System.out.println("There are no borrow records to show.");
        } else {
            borrowRecords.forEach(System.out::println);
        }
    }

    private static void loadBorrowRecords() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BORROW_FILE))) {
            Object readObject = ois.readObject();
            if (readObject instanceof ArrayList<?>) {
                borrowRecords = (ArrayList<BorrowRecord>) readObject;
            } else {
                throw new ClassNotFoundException("There was a data type mismatch in borrow records file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved borrow records were found");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("There was an error loading borrow records: " + e.getMessage());
        }
    }

    private static void saveBorrowRecords() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BORROW_FILE))) {
            oos.writeObject(borrowRecords);
        } catch (IOException e) {
            System.out.println("There was an error saving borrow records: " + e.getMessage());
        }
    }
}
