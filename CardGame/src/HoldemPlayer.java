import java.util.Scanner;

/**
 * Class representing the game texas holdem. It inherits the abstract game class
 * @author Luke Soda
 * @version 1.0
 */
public class HoldemPlayer extends Player {
    protected Blind blind; // Which blind is the player, BIG, SMALL, NONE
    protected int inChips; // Chips up for a given round
    protected int toBet; // Holds number to bet that is stored in the scanner. User input
    protected Scanner scanner;
    protected String currentAction; // Current action the player is taking for a current round
                                    // of betting (C/B/F)
    protected boolean isActive; // Is the player still playing? (Have they folded)

    /**
     * HoldemPlayer constructor, pass in chips, is the player main, and type of blind
     * @param chips Total number of chips the player has with them
     * @param isMain Is the player the user?
     * @param blind Blind of the player (BIG, SMALL, NONE)
     */
    public HoldemPlayer(int chips, boolean isMain, Blind blind) {
        super(chips, isMain); // Super constructor for abstract player class
        isActive = true; // Player is set to active by default (they have not folded)
        this.blind = blind; // Set the blind
        if (isMain) { // Only create a new scanner class if the player is the user (Not needed
                      // for CPUs)
            scanner = new Scanner(System.in);
        }
    }

    /**
     * Adds chips to what the player has up in front of them
     * Used on calls, checks, bets, raises
     * @param bet
     */
    public void addInChips(int bet) {
        chips -= bet; // Subtract the bet from the total chips owned
        inChips += bet; // Add the bet to the chips in play for the round
    }

    /**
     * Player folds
     */
    public void fold() {
        isActive = false; // Set active state to false
    }

    /**
     * Handles CPUs choosing their action for a round
     * Handles user input for main player
     */
    @Override 
    public void chooseAction() {
        toBet = 0; // Reset value set by scanner for betting
        if (isMain) { // If player is user, prompt in console
            System.out.print("Check/Call, Bet/Raise, Fold? (C/B/F): ");
            currentAction = scanner.nextLine(); // Current action = user input string
            if (currentAction.equals("B")) { // If user wants to bet...
                System.out.print("Enter bet: "); // Prompt a bet amount
                toBet = scanner.nextInt(); // Set the toBet to the user input int
            }
        }
        else {
            currentAction = "C"; // CPUs always call for now
        }
    }

    /**
     * toString for HoldemPlayer
     * Contains player string, blind, and inChips
     */
    @Override
    public String toString() {
        return  super.toString() + "\nBlind: " + blind + "\n" + "inChips: " + inChips + "\n\n";
    }
}
