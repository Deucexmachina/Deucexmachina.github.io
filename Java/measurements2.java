package Java;

import java.util.Scanner;

public class measurements2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String repeat;

        do {
            int from = 0, to = 0;
            double m = 0, mm = 0, cm = 0, km = 0, in = 0, ft = 0, yd = 0, ml = 0, value;

            System.out.println("Choose the unit to convert from:");
            System.out.println("1. Meters");
            System.out.println("2. Millimeters");
            System.out.println("3. Centimeters");
            System.out.println("4. Kilometers");
            System.out.println("5. Inches");
            System.out.println("6. Feet");
            System.out.println("7. Yards");
            System.out.println("8. Miles");

            do {
                System.out.println("What unit do you want to convert from (1-8)? ");
                from = scanner.nextInt();
            } while (from < 1 || from > 8);

            do {
                System.out.println("What unit do you want to convert to (1-8)? ");
                to = scanner.nextInt();
            } while (to < 1 || to > 8);

            do {
                System.out.println("Enter the value to convert (positive number): ");
                value = scanner.nextDouble();
            } while (value < 0);

            System.out.println("Converting from unit " + from + " to unit " + to);

            switch (from) {
                case 1:
                    m = value;
                    break;
                case 2:
                    m = value / 1000;
                    break;
                case 3:
                    m = value / 100;
                    break;
                case 4:
                    m = value * 1000;
                    break;
                case 5:
                    m = value * 0.0254;
                    break;
                case 6:
                    m = value * 0.3048;
                    break;
                case 7:
                    m = value * 0.9144;
                    break;
                case 8:
                    m = value * 1609.344;
                    break;
            }

            switch (to) {
                case 1:
                    System.out.println(value + " in chosen unit is " + m + " meters.");
                    break;
                case 2:
                    mm = m * 1000;
                    System.out.println(value + " in chosen unit is " + mm + " millimeters.");
                    break;
                case 3:
                    cm = m * 100;
                    System.out.println(value + " in chosen unit is " + cm + " centimeters.");
                    break;
                case 4:
                    km = m * 0.001;
                    System.out.println(value + " in chosen unit is " + km + " kilometers.");
                    break;
                case 5:
                    in = m * 39.37;
                    System.out.println(value + " in chosen unit is " + in + " inches.");
                    break;
                case 6:
                    ft = m * 3.281;
                    System.out.println(value + " in chosen unit is " + ft + " feet.");
                    break;
                case 7:
                    yd = m * 1.0936;
                    System.out.println(value + " in chosen unit is " + yd + " yards.");
                    break;
                case 8:
                    ml = m * 0.0006214;
                    System.out.println(value + " in chosen unit is " + ml + " miles.");
                    break;
            }

            System.out.print("Would you like to convert another? (yes/no): ");
            scanner.nextLine();
            repeat = scanner.nextLine();

        } while (repeat.equalsIgnoreCase("yes"));

        System.out.println("Thank you for using the converter!");
        scanner.close();
    }
}
