import java.util.Scanner;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;

public class InputValidator {

    /**
     * Prompts the user for a positive non-zero integer and validates the input
     * without using a try-catch block.
     */
    public static int validateBet(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            // Check if the input is all digits and not starting with zero (unless it's just "0")
            if (input.matches("[1-9][0-9]*")) {
                return Integer.parseInt(input);
            }
        }
    }

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
}