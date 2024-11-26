package OOP;

import java.io.*;
import java.util.Scanner;

public class BorrowerHistory {
    private static final String HISTORY_FILE = "historyRecords.txt";

    public static void displayBorrowerHistory(String borrowerId) {
        try (BufferedReader br = new BufferedReader(new FileReader(HISTORY_FILE))) {
            System.out.println("\n--- Borrower History ---");
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                if (line.contains("Borrower ID: " + borrowerId)) {
                    System.out.println(line);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No history found for Borrower ID: " + borrowerId);
            }
        } catch (FileNotFoundException e) {
            System.out.println("History file not found.");
        } catch (IOException e) {
            System.out.println("Error reading history file: " + e.getMessage());
        }
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Do not close the scanner here
        System.out.print("Enter Borrower ID to view history: ");
        String borrowerId = scanner.nextLine();
        displayBorrowerHistory(borrowerId);
    }
}
