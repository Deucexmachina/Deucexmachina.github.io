package OOP;

import java.io.*;
import java.util.*;

abstract class Material implements Serializable {
    protected String materialId;
    protected String title;
    protected String publisher;
    protected int yearPublished;
    protected int copies;

    public Material(String materialId, String title, int yearPublished, String publisher, int copies) {
        this.materialId = materialId;
        this.title = title;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
        this.copies = copies;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "Material ID: " + materialId + ", Title: " + title + ", Year: " + yearPublished + ", Publisher: " + publisher + ", Copies: " + copies;
    }

    public abstract String getMaterialType();
}

class Book extends Material {
    private String author;

    public Book(String materialId, String title, int yearPublished, String publisher, String author, int copies) {
        super(materialId, title, yearPublished, publisher, copies);
        this.author = author;
    }

    @Override
    public String toString() {
        return super.toString() + ", Author: " + author;
    }

    @Override
    public String getMaterialType() {
        return "Book";
    }
}

class Journal extends Material {
    private String journalName;

    public Journal(String materialId, String journalName, int yearPublished, String publisher, int copies) {
        super(materialId, journalName, yearPublished, publisher, copies);
        this.journalName = journalName;
    }

    @Override
    public String toString() {
        return super.toString() + ", Journal Name: " + journalName;
    }

    @Override
    public String getMaterialType() {
        return "Journal";
    }
}

class Magazine extends Material {
    private String magazineName;

    public Magazine(String materialId, String magazineName, int yearPublished, String publisher, int copies) {
        super(materialId, magazineName, yearPublished, publisher, copies);
        this.magazineName = magazineName;
    }

    @Override
    public String toString() {
        return super.toString() + ", Magazine Name: " + magazineName;
    }

    @Override
    public String getMaterialType() {
        return "Magazine";
    }
}

class ThesisBook extends Material {
    private String author;

    public ThesisBook(String materialId, String title, int yearPublished, String publisher, String author, int copies) {
        super(materialId, title, yearPublished, publisher, copies);
        this.author = author;
    }

    @Override
    public String toString() {
        return super.toString() + ", Author: " + author;
    }

    @Override
    public String getMaterialType() {
        return "Thesis Book";
    }
}

public class AssetManagement {
    private static final String FILE_NAME = "assets.txt";
    private static ArrayList<Material> materials = new ArrayList<>();

    public static void main(String[] args) {
        loadAssets();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Asset Management System ---");
            System.out.println("1. Add Material");
            System.out.println("2. Edit Material");
            System.out.println("3. Delete Material");
            System.out.println("4. View Materials");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1: addMaterial(scanner);
                break;
                case 2: editMaterial(scanner);
                break;
                case 3: deleteMaterial(scanner);
                break;
                case 4: viewMaterials();
                break;
                case 5: {
                    saveAssets();
                    System.out.println("Exiting system. Goodbye!");
                }
                break;
                default: System.out.println("Invalid choice. Please try again.");
                break;
            }
        } while (choice != 5);
    }

    private static void addMaterial(Scanner scanner) {
        System.out.println("\n--- Add Material ---");

        System.out.print("Enter Material ID: ");
        String materialId = scanner.nextLine();

        if (materials.stream().anyMatch(m -> m.getMaterialId().equals(materialId))) {
            System.out.println("Material with this ID already exists.");
            return;
        }

        System.out.print("Enter Material Type (Book/Journal/Magazine/ThesisBook): ");
        String type = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Year Published: ");
        int yearPublished = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter Number of Copies: ");
        int copies = scanner.nextInt();
        scanner.nextLine();

        Material newMaterial;
        switch (type.toLowerCase()) {
            case "book":
                System.out.print("Enter Author: ");
                String bookAuthor = scanner.nextLine();
                newMaterial = new Book(materialId, title, yearPublished, publisher, bookAuthor, copies);
                break;
            case "journal":
                System.out.print("Enter Journal Name: ");
                String journalName = scanner.nextLine();
                newMaterial = new Journal(materialId, journalName, yearPublished, publisher, copies);
                break;
            case "magazine":
                System.out.print("Enter Magazine Name: ");
                String magazineName = scanner.nextLine();
                newMaterial = new Magazine(materialId, magazineName, yearPublished, publisher, copies);
                break;
            case "thesisbook":
                System.out.print("Enter Author: ");
                String thesisAuthor = scanner.nextLine();
                newMaterial = new ThesisBook(materialId, title, yearPublished, publisher, thesisAuthor, copies);
                break;
            default:
                System.out.println("Invalid material type.");
                return;
        }

        materials.add(newMaterial);
        System.out.println("Material added successfully!");
    }

    private static void editMaterial(Scanner scanner) {
        System.out.println("\n--- Edit Material ---");
        System.out.print("Enter Material ID to edit: ");
        String materialId = scanner.nextLine();

        Material material = materials.stream().filter(m -> m.getMaterialId().equals(materialId)).findFirst().orElse(null);

        if (material == null) {
            System.out.println("Material not found.");
            return;
        }

        System.out.print("Enter new number of copies: ");
        int copies = scanner.nextInt();
        scanner.nextLine();

        material.setCopies(copies);
        System.out.println("Material updated successfully!");
    }

    private static void deleteMaterial(Scanner scanner) {
        System.out.println("\n--- Delete Material ---");
        System.out.print("Enter Material ID to delete: ");
        String materialId = scanner.nextLine();

        if (materials.removeIf(m -> m.getMaterialId().equals(materialId))) {
            System.out.println("Material deleted successfully!");
        } else {
            System.out.println("Material not found.");
        }
    }

    private static void viewMaterials() {
        System.out.println("\n--- Materials List ---");
        if (materials.isEmpty()) {
            System.out.println("No materials to display.");
        } else {
            materials.forEach(System.out::println);
        }
    }

    public static ArrayList<Material> getMaterials() {
        return materials;
    }    

    @SuppressWarnings("unchecked")
    private static void loadAssets() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            materials = (ArrayList<Material>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private static void saveAssets() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(materials);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
