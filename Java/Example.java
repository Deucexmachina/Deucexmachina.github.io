package Java;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.io.IOException;

public class Example {
    public static void main(String[] args) throws IOException {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter final grade: ");
        int put = Integer.parseInt(buff.readLine());
        if (put < 50 || put > 100) {
            System.out.println("Invalid Input, Final Grade must be 50-100 only");
            return;
        }
        switch (put){
        case 100: case 99: case 98:
        System.out.println("1.00");
        break;
        case 97: case 96: case 95:
        System.out.println("1.25");
        break;
        case 94: case 93: case 92:
        System.out.println("1.50");
        break;
        case 91: case 90: case 89:
        System.out.println("1.75");
        break;
        case 88: case 87: case 86:
        System.out.println("2.00");
        break;
        case 85: case 84: case 83:
        System.out.println("2.25");
        break;
        case 82: case 81: case 80:
        System.out.println("2.50");
        break;
        case 79: case 78: case 77:
        System.out.println("2.75");
        break;
        case 76: case 75:
        System.out.println("3.00");
        break;
        default:
        System.out.println("5.00 (FAILED)");
        break;
        }
    }
}
