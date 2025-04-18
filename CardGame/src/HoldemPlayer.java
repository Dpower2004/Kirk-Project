import java.util.Scanner;

/**
 * Class representing the game texas holdem. It inherits the abstract game class
 * @author Luke Soda
 * @version 1.0
 */
public class HoldemPlayer extends Player {
    protected int inChips; // Chips up for a given round
    protected int raise; // Holds number to bet that is stored in the scanner. User input
    protected Scanner playerInput;
    protected String currentAction; // Current action the player is taking for a current round
                                    // of betting (C/B/F)
    protected boolean isActive; // Is the player still playing? (Have they folded)

    /**
     * HoldemPlayer constructor, pass in chips, is the player main, and type of blind
     * @param chips Total number of chips the player has with them
     * @param isMain Is the player the user?
     * @param blind Blind of the player (BIG, SMALL, NONE)
     */
    public HoldemPlayer(int chips, boolean isMain) {
        super(chips, isMain); // Super constructor for abstract player class
        isActive = true; // Player is set to active by default (they have not folded)
        if (isMain) { // Only create a new scanner class if the player is the user (Not needed
                      // for CPUs)
            playerInput = new Scanner(System.in);
        }
    }

    /**
     * Adds chips to what the player has up in front of them
     * Used on calls, checks, bets, raises
     * @param bet
     */
    public void setInChips(int inChips) {
        //totalChips -= bet; // Subtract the bet from the total chips owned
        this.inChips = inChips; // Add the bet to the chips in play for the round
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
    public void chooseAction(int highBet) {
        currentAction = "";
        raise = 0; // Reset value set by scanner for betting
        if (isMain) { // If player is user, prompt in console
            System.out.print("Check/Call, Bet/Raise, Fold? (C/R/F): ");
            currentAction = playerInput.nextLine(); // Current action = user input string
            if (currentAction.equals("R")) { // If user wants to bet...
                System.out.print("Enter bet: "); // Prompt a bet amount
                raise = playerInput.nextInt(); // Set the toBet to the user input int
                playerInput.nextLine(); // Consume extra \n from int
            }
        }
        else {
            currentAction = "R"; // CPUs always call for now
            raise = 10;
        }
    }

    /**
     * toString for HoldemPlayer
     * Contains player string, blind, and inChips
     */
    @Override
    public String toString() {
        return super.toString() + "\nChips in: " + inChips + "\nAction Taken: " + currentAction + "\n\n";
    }
}
