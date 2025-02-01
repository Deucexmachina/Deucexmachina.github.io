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

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getAuthor() {
        return author;
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

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }
    
    public String getJournalName() {
        return journalName;
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

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }
    
    public String getJournalName() {
        return magazineName;
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

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getAuthor() {
        return author;
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

    static {
        loadAssets();
    }

    private static boolean isValidString(String input) {
        return input.matches("[a-zA-Z0-9 .'-]+");
    }
    private static boolean isValidMaterialType(String type) {
        return type.equalsIgnoreCase("Book") || type.equalsIgnoreCase("Journal") ||
               type.equalsIgnoreCase("Magazine") || type.equalsIgnoreCase("ThesisBook");
    }
    private static boolean isValidMaterialId(String materialId) {
        return materialId.matches("\\d+");
    }    
    private static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

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

        if (!isValidMaterialId(materialId)) {
            System.out.println("Invalid Material ID. Only numbers are allowed.");
            return;
        }
        
        if (materials.stream().anyMatch(m -> m.getMaterialId().equals(materialId))) {
            System.out.println("Material with this ID already exists.");
            return;
        }

        System.out.print("Enter Material Type (Book/Journal/Magazine/ThesisBook): ");
        String type = scanner.nextLine();
        if (!isValidMaterialType(type)) {
            System.out.println("Invalid material type. Only Book, Journal, Magazine, or ThesisBook are allowed.");
            return;
        }
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        if (!isValidString(title)) {
            System.out.println("Invalid title. Only letters, numbers, and common symbols are allowed.");
            return;
        }
        System.out.print("Enter Year Published: ");
        int yearPublished;
        try {
            yearPublished = scanner.nextInt();
            scanner.nextLine();
            if (yearPublished <= 0) {
                System.out.println("Invalid year. Must be a positive number.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid year.");
            scanner.nextLine();
            return;
        }        
        System.out.print("Enter Publisher: ");
        String publisher = scanner.nextLine();
        if (!isValidString(publisher)) {
            System.out.println("Invalid publisher name. Only letters, numbers, and common symbols are allowed.");
            return;
        }
        System.out.print("Enter Number of Copies: ");
        int copies;
        try {
            copies = scanner.nextInt();
            scanner.nextLine();
            if (copies <= 0) {
                System.out.println("Invalid number of copies. Must be a positive number.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
            return;
        }

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
    
        Material material = materials.stream()
                                     .filter(m -> m.getMaterialId().equals(materialId))
                                     .findFirst()
                                     .orElse(null);
    
        if (material == null) {
            System.out.println("Material not found.");
            return;
        }
    
        System.out.println("Editing Material: " + material);
    
        System.out.print("Enter new Title (leave blank to keep current): ");
        String title = scanner.nextLine();
        if (!isBlank(title) && isValidString(title)) {
            material.title = title;
        }
    
        System.out.print("Enter new Year Published (leave blank to keep current): ");
        String yearInput = scanner.nextLine();
        if (!isBlank(yearInput)) {
            try {
                int year = Integer.parseInt(yearInput);
                if (year > 0) {
                    material.yearPublished = year;
                } else {
                    System.out.println("Invalid year. Must be positive.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid year. Must be a number.");
            }
        }
    
        System.out.print("Enter new Publisher (leave blank to keep current): ");
        String publisher = scanner.nextLine();
        if (!isBlank(publisher) && isValidString(publisher)) {
            material.publisher = publisher;
        }
    
        System.out.print("Enter new Number of Copies (leave blank to keep current): ");
        String copiesInput = scanner.nextLine();
        if (!isBlank(copiesInput)) {
            try {
                int copies = Integer.parseInt(copiesInput);
                if (copies > 0) {
                    material.setCopies(copies);
                } else {
                    System.out.println("Invalid number of copies. Must be positive.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    
        if (material instanceof Book) {
            Book book = (Book) material;
            System.out.print("Enter new Author (leave blank to keep current): ");
            String author = scanner.nextLine();
            if (!isBlank(author) && isValidString(author)) {
                book.setAuthor(author); 
            }
        } else if (material instanceof Journal) {
            Journal journal = (Journal) material;
            System.out.print("Enter new Journal Name (leave blank to keep current): ");
            String journalName = scanner.nextLine();
            if (!isBlank(journalName) && isValidString(journalName)) {
                journal.setJournalName(journalName);
            }
        } else if (material instanceof Magazine) {
            Magazine magazine = (Magazine) material;
            System.out.print("Enter new Magazine Name (leave blank to keep current): ");
            String magazineName = scanner.nextLine();
            if (!isBlank(magazineName) && isValidString(magazineName)) {
                magazine.setMagazineName(magazineName);
            }
        } else if (material instanceof ThesisBook) {
            ThesisBook thesisBook = (ThesisBook) material;
            System.out.print("Enter new Author (leave blank to keep current): ");
            String author = scanner.nextLine();
            if (!isBlank(author) && isValidString(author)) {
                thesisBook.setAuthor(author);
            }
        }
        
    
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

    public static void saveAssets() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(materials);
            System.out.println("Assets saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    

    public static ArrayList<Material> getMaterials() {
        return materials;
    }    
}
