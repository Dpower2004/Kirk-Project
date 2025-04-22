import java.util.Scanner;
import java.util.Random;

/**
 * Class representing the game texas holdem. It inherits the abstract game class
 * @author Luke Soda
 * @version 1.0
 */
public class HoldemPlayer extends Player {
    protected ChipStack handChips; // Chips up for a given round
    protected String currentAction; // Current action the player is taking for a current round
    protected boolean isActive; // Is the player still playing? (Have they folded)
    protected int maxRoundChips = bank.chipAmount;

    private Scanner input; // User input

    /**
     * HoldemPlayer constructor, pass in chips, is the player main, and type of blind
     * @param chips Total number of chips the player has with them
     * @param isMain Is the player the user?
     * @param blind Blind of the player (BIG, SMALL, NONE)
     */
    public HoldemPlayer(int chips, boolean isMain) {
        super(chips, isMain); // Super constructor for abstract player class
        isActive = true; // Player is set to active by default (they have not folded)
        handChips = new ChipStack(0);
        if (isMain) { // Only create a new scanner class if the player is the user (Not needed
                      // for CPUs)
            input = new Scanner(System.in);
        }
    }

    /**
     * Adds chips to what the player has up in front of them
     * Used on calls, checks, bets, raises
     * @param bet
     */
    public void setHandChips(int amount) {
        bank.setChips(maxRoundChips - amount);
        handChips.setChips(amount); // Add the bet to the chips in play for the round
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
    public String chooseAction(int highBet, boolean betMade) {
        if (isMain) { // If player is user, prompt in console
            if (!betMade && handChips.chipAmount == 0) {
                currentAction = InputValidator.validateCustomPrompt(input, "C, B, F", "Check, Bet, Fold? (C/B/F): ");
            }
            else {
                currentAction = InputValidator.validateCustomPrompt(input, "C, R, F", "Check, Raise, Fold? (C/R/F): ");
            }
        }
        else {
            currentAction = "C";
            /*if (handChips.chipAmount != 0 || betMade == true) {
                currentAction = "R";
            }
            else {
                currentAction = "B";
            }*/
        }
        return currentAction;
    }

    public int promptAmount() {
        int bet = InputValidator.validateBet(input, "Amount: ");
        return bet;
    }

    public int cpuBet() {
        Random rand = new Random();
        int bet = rand.nextInt(20 - 3 + 1) + 3;
        return bet;
    }

    /**
     * toString for HoldemPlayer
     * Contains player string, blind, and handChips
     */
    @Override
    public String toString() {
        String retString = super.toString() + "\nChips in: " + handChips + "\nAction Taken: " + currentAction;
        if (isMain) {
            retString += ("\nHand: " + cards);
        }
        retString += "\n";
        return retString;
    }
}
