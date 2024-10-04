package Java;

import java.util.Scanner;

public class LabExer2 {
    private String itemName;
    private double itemPrice;
    private int itemQuantity;
    private double amountDue;
    
    // Set the item name
    public void setItemName(String newItemName) {
        this.itemName = newItemName;
    }

    // Set the total cost
    public void setTotalCost(int quantity, double price) {
        this.itemQuantity = quantity;
        this.itemPrice = price;
        this.amountDue = quantity * price;
    }

    // Get the item name
    public String getItemName() {
        return this.itemName;
    }

    // Get the total cost
    public double getTotalCost() {
        return this.amountDue;
    }

    // Read input
    public void readInput() {
        Scanner scanner = new Scanner(System.in);
        
        // Get the item name
        System.out.print("Enter the name of the item you are purchasing: ");
        setItemName(scanner.nextLine());
        
        // Get the item quantity and price
        System.out.print("Enter the quantity and price separated by a space: ");
        int quantity = scanner.nextInt();
        double price = scanner.nextDouble();
        setTotalCost(quantity, price);

        scanner.close();
    }

    // Write the output
    public void writeOutput() {
        System.out.println("You are purchasing " + itemQuantity + " " + itemName + "(s) at " + itemPrice + " each.");
        System.out.println("Amount due is " + amountDue);
    }

    public static void main(String[] args) {
        LabExer2 purchase = new LabExer2();
        
        purchase.readInput();
        
        purchase.writeOutput();
    }
}
