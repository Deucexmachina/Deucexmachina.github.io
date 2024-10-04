package Java;

public class LabExer1A {
    public static void main(String[] args) {
        int faveNumber = 12; // Type your favorite number
        String faveCartChar = "Doraemon"; // Type your favorite cartoon or anime character
        char mi = 'V'; // Type your middle initial
        char[] nickNameArray = {'N', 'i', 'c', 'a'}; // Every index should contain each of the letterd of your nickname
        
        // Output
        System.out.println(faveNumber + " is my favorite number.");
        System.out.println("I love " + faveCartChar + "!");
        System.out.print("My name is Veronica " + mi + ". Velasquez.");
        System.out.print(" You can call me ");
        for(char letter : nickNameArray) {
            System.out.print(letter);
        }
        System.out.println(".");
    }
}
