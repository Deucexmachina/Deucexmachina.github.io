package OOP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=================== Library System ===================");
            System.out.println("1. Borrowers Management");
            System.out.println("2. Asset Management");
            System.out.println("3. Borrow Material");
            System.out.println("4. Return Material");
            System.out.println("5. Borrower History");
            System.out.println("6. Book History");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    BorrowersManagement.main(new String[]{});
                    break;
                case 2:
                    AssetManagement.main(new String[]{});
                    break;
                case 3:
                    Borrow.main(new String[]{});
                    break;
                case 4:
                    // Return.main(new String[]{});
                    break;
                case 5:
                    // BorrowerHistory.main(new String[]{});
                    break;
                case 6:
                    // BookHistory.main(new String[]{});
                    break;
                case 7:
                    System.out.println("\n================ Thank You! ================");
                    System.out.println("Group: [Your Group Name]");
                    System.out.println("Members: [List of Members]");
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }
}
