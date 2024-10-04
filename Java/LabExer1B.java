package Java;

import java.util.Scanner;

public class LabExer1B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Ask the user for an integer input
        System.out.print("Enter an integer: ");
        int number = scanner.nextInt();
        
        // Calling the methods
        showNumberPlus10(number);
        showNumberPlus100(number);
        showNumberPlus1000(number);

        scanner.close();
    }
    
    // Add 10 to the number
    public static void showNumberPlus10(int num) {
        System.out.println(num + " plus 10 is " + (num + 10) + ".");
    }

    // Add 100 to the number
    public static void showNumberPlus100(int num) {
        System.out.println(num + " plus 100 is " + (num + 100) + ".");
    }

    // Add 1000 to the number
    public static void showNumberPlus1000(int num) {
        System.out.println(num + " plus 1000 is " + (num + 1000) + ".");
    }
}
