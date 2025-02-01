package PersonnelManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Employee> employees = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int idCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nPersonnel Management System");
            System.out.println("1. Add New Employee");
            System.out.println("2. Edit Employee Record");
            System.out.println("3. View Employee Record");
            System.out.println("4. Display All Employees");
            System.out.println("5. Delete Employee Record");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    editEmployee();
                    break;
                case 3:
                    viewEmployee();
                    break;
                case 4:
                    displayAllEmployees();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Method to add a new employee
    public static void addEmployee() {
        System.out.print("Enter Last Name: ");
        String lastname = scanner.next();
        System.out.print("Enter First Name: ");
        String firstname = scanner.next();
        System.out.print("Enter Middle Name: ");
        String middlename = scanner.next();
        System.out.print("Enter Gender: ");
        String gender = scanner.next();
        System.out.print("Enter Birthday: ");
        String birthday = scanner.next();
        System.out.print("Enter Employee Type (GeneralServices or Regulars): ");
        String empType = scanner.next();

        if (empType.equalsIgnoreCase("GeneralServices")) {
            System.out.print("Enter Department: ");
            String department = scanner.next();
            System.out.print("Enter Status (Contractual or Job Order): ");
            String status = scanner.next();
            employees.add(new GeneralServices(idCounter++, lastname, firstname, middlename, gender, birthday, department, status));
        } else if (empType.equalsIgnoreCase("Regulars")) {
            System.out.print("Enter Role (Managerial or Rank and File): ");
            String role = scanner.next();
            System.out.print("Enter Department: ");
            String department = scanner.next();
            System.out.print("Enter Status (Regular or Contractual): ");
            String status = scanner.next();
            employees.add(new Regulars(idCounter++, lastname, firstname, middlename, gender, birthday, role, department, status));
        } else {
            System.out.println("Invalid Employee Type.");
        }
    }

    // Method to edit an employee record
    public static void editEmployee() {
        System.out.print("Enter Employee ID to edit: ");
        int id = scanner.nextInt();
        for (Employee employee : employees) {
            if (employee.id == id) {
                System.out.print("Enter new Last Name: ");
                employee.setLastname(scanner.next());
                System.out.print("Enter new First Name: ");
                employee.setFirstname(scanner.next());
                System.out.print("Enter new Middle Name: ");
                employee.setMiddlename(scanner.next());
                System.out.print("Enter new Gender: ");
                employee.setGender(scanner.next());
                System.out.print("Enter new Birthday: ");
                employee.setBirthday(scanner.next());
                System.out.println("Employee record updated successfully.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    // Method to view an employee record
    public static void viewEmployee() {
        System.out.print("Enter employee ID to view: ");
        int id = scanner.nextInt();
        for (Employee employee : employees) {
            if (employee.id == id) {
                employee.displayEmployeeDetails();
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    // Method to display all employees
    public static void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            for (Employee employee : employees) {
                employee.displayEmployeeDetails();
                System.out.println();
            }
        }
    }

    // Method to delete an employee record
    public static void deleteEmployee() {
        System.out.print("Enter employee ID to delete: ");
        int id = scanner.nextInt();
        employees.removeIf(employee -> employee.id == id);
        System.out.println("Employee record deleted successfully.");
    }
}
