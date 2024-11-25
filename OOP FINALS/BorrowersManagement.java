import java.io.*;
import java.util.*;

class Borrower implements Serializable {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String birthday;
    private String contactNumber;
    private String email;
    private String address;
    private int violations;

    public Borrower(String id, String firstName, String middleName, String lastName, String gender,
                    String birthday, String contactNumber, String email, String address, int violations) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
        this.violations = violations;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return firstName + " " + middleName + " " + lastName;
    }

    public void setViolations(int violations) {
        this.violations = violations;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + getFullName() + ", Gender: " + gender +
               ", Birthday: " + birthday + ", Contact: " + contactNumber +
               ", Email: " + email + ", Address: " + address +
               ", Violations: " + violations;
    }
}

public class BorrowersManagement {
    private static final String FILE_NAME = "borrowers.txt";
    private static ArrayList<Borrower> borrowers = new ArrayList<>();

    public static void main(String[] args) {
        loadBorrowers();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Borrowers Management System ---");
            System.out.println("1. Add Borrower");
            System.out.println("2. Edit Borrower");
            System.out.println("3. Delete Borrower");
            System.out.println("4. View Borrowers");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> addBorrower(scanner);
                case 2 -> editBorrower(scanner);
                case 3 -> deleteBorrower(scanner);
                case 4 -> viewBorrowers();
                case 5 -> {
                    saveBorrowers();
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void addBorrower(Scanner scanner) {
        System.out.println("\n--- Add Borrower ---");

        System.out.print("Enter Borrower ID: ");
        String id = scanner.nextLine();

        if (borrowers.stream().anyMatch(b -> b.getId().equals(id))) {
            System.out.println("Borrower with this ID already exists.");
            return;
        }

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Middle Name: ");
        String middleName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Birthday (YYYY-MM-DD): ");
        String birthday = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Number of Violations: ");
        int violations = scanner.nextInt();
        scanner.nextLine();

        Borrower newBorrower = new Borrower(id, firstName, middleName, lastName, gender, birthday,
                                            contactNumber, email, address, violations);
        borrowers.add(newBorrower);
        System.out.println("Borrower added successfully!");
    }

    private static void editBorrower(Scanner scanner) {
        System.out.println("\n--- Edit Borrower ---");
        System.out.print("Enter Borrower ID to edit: ");
        String id = scanner.nextLine();

        Borrower borrower = borrowers.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);

        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }

        System.out.print("Enter new number of violations: ");
        int violations = scanner.nextInt();
        scanner.nextLine();

        borrower.setViolations(violations);
        System.out.println("Borrower updated successfully!");
    }

    private static void deleteBorrower(Scanner scanner) {
        System.out.println("\n--- Delete Borrower ---");
        System.out.print("Enter Borrower ID to delete: ");
        String id = scanner.nextLine();

        if (borrowers.removeIf(b -> b.getId().equals(id))) {
            System.out.println("Borrower deleted successfully!");
        } else {
            System.out.println("Borrower not found.");
        }
    }

    private static void viewBorrowers() {
        System.out.println("\n--- Borrowers List ---");
        if (borrowers.isEmpty()) {
            System.out.println("No borrowers to display.");
        } else {
            borrowers.forEach(System.out::println);
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadBorrowers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            borrowers = (ArrayList<Borrower>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private static void saveBorrowers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(borrowers);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}