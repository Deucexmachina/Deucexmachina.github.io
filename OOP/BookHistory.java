package OOP;

import java.io.*;
import java.util.Scanner;

public class BookHistory {
    private static final String HISTORY_FILE = "historyRecords.txt";

    public static void displayBookHistory(String materialId) {
        try (BufferedReader br = new BufferedReader(new FileReader(HISTORY_FILE))) {
            System.out.println("\n--- Book History ---");
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                if (line.contains("Material ID: " + materialId)) {
                    System.out.println(line);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No history found for Material ID: " + materialId);
            }
        } catch (FileNotFoundException e) {
            System.out.println("History file not found.");
        } catch (IOException e) {
            System.out.println("Error reading history file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Material ID to view history: ");
        String materialId = scanner.nextLine();
        displayBookHistory(materialId);
        scanner.close();
    }
}
