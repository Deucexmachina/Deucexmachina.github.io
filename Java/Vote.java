package Java;

import java.util.Scanner;

public class Vote {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int votesFPJ = 0, votesROCO = 0, votesGMA = 0;
        char choice;
        
        System.out.println("PRESIDENTIAL ELECTIONS");
        System.out.println("Candidates:");
        System.out.println("A. FPJ");
        System.out.println("B. ROCO");
        System.out.println("C. GMA");
        
        do {
            System.out.println("Enter 'V' to vote, 'R' to view results, and 'Q' to quit: ");
            choice = scanner.next().toUpperCase().charAt(0);

            switch (choice) {
                case 'V':
                    System.out.println("Enter your vote (A - FPJ, B - ROCO, C - GMA): ");
                    char vote = scanner.next().toUpperCase().charAt(0);
                    switch (vote) {
                        case 'A':
                            votesFPJ++;
                            System.out.println("You voted for FPJ.");
                            break;
                        case 'B':
                            votesROCO++;
                            System.out.println("You voted for ROCO.");
                            break;
                        case 'C':
                            votesGMA++;
                            System.out.println("You voted for GMA.");
                            break;
                        default:
                            System.out.println("Invalid vote! Please try again.");
                            break;
                    }
                    break;
                
                case 'R':
                    System.out.println("PRESIDENTIAL ELECTIONS");
                    System.out.println("Candidates:           Results:");
                    System.out.println("A. FPJ                " + votesFPJ);
                    System.out.println("B. ROCO               " + votesROCO);
                    System.out.println("C. GMA                " + votesGMA);
                    System.out.println("Total Votes: " + (votesFPJ + votesROCO + votesGMA));
                    System.out.println("And the current winner is: " + getWinner(votesFPJ, votesROCO, votesGMA));
                    break;

                case 'Q':
                    System.out.println("PRESIDENTIAL ELECTIONS FINAL RESULTS");
                    System.out.println("Candidates:           Results:");
                    System.out.println("A. FPJ                " + votesFPJ);
                    System.out.println("B. ROCO               " + votesROCO);
                    System.out.println("C. GMA                " + votesGMA);
                    System.out.println("Total Votes: " + (votesFPJ + votesROCO + votesGMA));
                    System.out.println("And the winner is: " + getWinner(votesFPJ, votesROCO, votesGMA));
                    break;

                default:
                    System.out.println("Invalid option! Please enter 'V', 'R', or 'Q'.");
                    break;
            }
        } while (choice != 'Q');

        scanner.close();
    }

    public static String getWinner(int votesFPJ, int votesROCO, int votesGMA) {
        if (votesFPJ > votesROCO && votesFPJ > votesGMA) {
            return "FPJ";
        } else if (votesROCO > votesFPJ && votesROCO > votesGMA) {
            return "ROCO";
        } else if (votesGMA > votesFPJ && votesGMA > votesROCO) {
            return "GMA";
        } else {
            return "No clear winner (it's a tie)";
        }
    }
}
