package OOP;

import java.io.*;
import java.util.*;

public class BorrowerHistory {
    private static final String BORROW_FILE = "borrow_records.txt";
    private static ArrayList<BorrowRecord> borrowRecords = new ArrayList<>();

    public static void main(String[] args) {
        loadBorrowRecords();
        viewBorrowRecords();
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
}
