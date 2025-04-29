import java.util.Scanner;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;

/**
 * Class to help validate various thingies.
 * @author Luke Soda & Thomas Huber
 * @version 1.3
 */
public class InputValidator {

    /**
     * Prompts the user for a positive non-zero integer and validates the input
     * without using a try-catch block.
     * @param scanner Scanner object to get user input
     * @param prompt String with prompt to prompt user
     * @param playerBank player's total amount of chips in reserve
     * @param highBet bet that needs to be matched
     */
    public static int validateBet(Scanner scanner, String prompt, int playerBank, int highBet) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            // Check if the input is all digits and not starting with zero (unless it's just "0")
            if (input.matches("[1-9][0-9]*")) {
                if (Integer.parseInt(input) > playerBank || highBet + Integer.parseInt(input) > playerBank) {
                    System.out.println("Value exceeds bank!");
                }
                else {
                    return Integer.parseInt(input);
                }
            }
        }
    }

    /**
     * validates various custom inputs
     * @param scanner Scanner object for user input
     * @param validString String of consecutive valid inputs to act as a key
     * @param prompt String to prompt user
     * @return String the selected valid input
     */
    public static String validateCustomPrompt(Scanner scanner, String validString, String prompt) {
        String[] validInputs = validString.split(", ");
        Set<String> validSet = new HashSet<>(Arrays.asList(validInputs));
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (validSet.contains(input)) {
                return input;
            }
        }
    }

    /**
     * validates user's bet in blackjack
     * @param scanner Scanner object to get user input
     * @param prompt String to prompt user
     * @param playerChips amount of chips player has
     * @param max max amount of chips player can bet
     * @param min min amount of chips player can bet
     * @return valid int amount of chips to bet
     */
    public static int validateBJBet(Scanner scanner, String prompt, int playerChips, int max, int min) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            // Check if the input is all digits and not starting with zero (unless it's just "0")
            if (input.matches("[1-9][0-9]*")) {
                if (Integer.parseInt(input) >= min && Integer.parseInt(input) <= max && Integer.parseInt(input) <= playerChips) {
                    return Integer.parseInt(input);
                }
                else if (Integer.parseInt(input) < min){
                    System.out.println("Enter value above or equal to the minimum!");
                }
                else if (Integer.parseInt(input) > max){
                    System.out.println("Enter value below or equal to the maximum!");
                }
                else if (Integer.parseInt(input) > playerChips){
                    System.out.println("You don't have that many chips to bet!");
                }
            }
            else
            {
                System.out.println("Enter a valid number!");
            }
        }
    }

    /**
     * makes sure player can actually double down
     * @param playerChips the amount of chips the player has
     * @param playerBet the amount the player initially bet
     * @return true or false; true if player can double down, false if not
     */
    public static boolean validateDoubleDown(int playerChips, int playerBet) {
        if (playerBet * 2 > playerChips)
        {
            System.out.println("Not enough chips to double down! You will hit instead.");
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * makes sure user inputs a valid move (hit, stand, or double down)
     * @param prompt the text that serves to prompt the user
     * @param scanner scanner object to get user's input
     * @return H, S, or D in String forme based on user input
     */
    public static String validateHitStandDouble(String prompt, Scanner scanner)
    {
        while (true)
        {
            System.out.print(prompt);
        
            char input = scanner.next().charAt(0);
            input = Character.toUpperCase(input); // make it uppercase for easy comparison
        
            if (input == 'H' || input == 'S' || input == 'D') {
                return "" + input;
            } else
            {
                System.out.println("Please type H (hit), S (stand), or D (double down).");
            }
        }
    }
    
}