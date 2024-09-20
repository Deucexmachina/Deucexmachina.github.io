package Java;

import java.util.Scanner;

public class Loop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("(For Loop) Input a Number = ");
        int fornum = scanner.nextInt();

        long factorial1 = 1;
        for (int i = 1; i <= fornum; i++) {
            factorial1 *= i;
        }

        System.out.println("The Input Value is " + fornum + ", factorial is " + factorial1);

        System.out.print("(While Loop) Input a Number = ");
        int whilenum = scanner.nextInt();

        int factorial2 = 1;
        int i = 1;
        while (i <= whilenum) {
            factorial2 *= i;
            i++;
        }

        System.out.println("The Input Value is " + whilenum + ", factorial is " + factorial2);

        System.out.print("(Do Loop) Input a Number = ");
        int donum = scanner.nextInt();

        long factorial3 = 1;
        int j = 1;
        do {
            factorial3 *= j;
            j++;
        } while (j <= donum);

        System.out.println("The Input Value is " + donum + ", factorial is " + factorial3);

        scanner.close();
    }
}
