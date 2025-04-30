

import java.util.Random;
import java.util.Scanner;

import javafx.scene.layout.HBox;

/**
 * Class representing the game texas holdem. It inherits the abstract game class
 * @author Luke Soda
 * @version 1.0
 */
public class HoldemPlayer extends Player {
    protected ChipStack handChips; // Chips up for a given round
    protected String currentAction; // Current action the player is taking for a current round
    protected boolean isActive; // Is the player still playing? (Have they folded)
    protected int maxRoundChips = chipBank.chipAmount;
    protected Hand handValue;
    protected Card highCard;
    protected Card secondHighCard;
    protected HBox cardBox = new HBox(48);

    private Scanner input; // User input

    /**
     * HoldemPlayer constructor, pass in chips, is the player main, and type of blind
     * @param chips Total number of chips the player has with them
     * @param isMain Is the player the user?
     *
     */
    public HoldemPlayer(int chips, boolean isMain) {
        super(chips, isMain,0.0); // Super constructor for abstract player class
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
     * @param amount
     */
    public void setHandChips(int amount) {
        chipBank.setChips(maxRoundChips - amount);
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
            /*if (!betMade && handChips.chipAmount == 0) {
                currentAction = InputValidator.validateCustomPrompt(input, "C, B, F", "Check, Bet, Fold? (C/B/F): ");
            }
            else {
                currentAction = InputValidator.validateCustomPrompt(input, "C, R, F", "Check, Raise, Fold? (C/R/F): ");
            }*/
            currentAction = "C";
        }
        else {
            Random rand = new Random();
            int actionInt = rand.nextInt(100);
            if (actionInt < 60) { // Check / Call
                currentAction = "C";
            }
            else if (actionInt >= 60 && actionInt < 90) {
                if (highBet < chipBank.chipAmount) {
                    if (handChips.chipAmount != 0 || betMade == true) {
                        currentAction = "R";
                    }
                    else {
                        currentAction = "B";
                    }
                }
                else {
                    currentAction = "C";
                }
            }
            else {
                currentAction = "F";
            }
        }
        return currentAction;
    }

    public int promptAmount(int highBet) {
        int bet = InputValidator.validateBet(input, "Amount: ", chipBank.chipAmount, highBet);
        return bet;
    }

    public int cpuBet(int highBet) {
        Random rand = new Random();
        int bet = rand.nextInt(22) + 4;
        if (currentAction.equals("R") && highBet + bet > chipBank.chipAmount) {
            bet = maxRoundChips - highBet;
        }
        if (currentAction.equals("B") && bet > chipBank.chipAmount) {
            bet = maxRoundChips;
        }
        return bet;
    }

    public void assignHandValue(CardList communityCards) {
        HoldemHandValue value = new HoldemHandValue(this, communityCards);
        handValue = value.handValue;
        highCard = value.highCard;
        secondHighCard = value.secondHighCard;
    }

    public int getHandValue() {
        return handValue.VALUE;
    }

    /**
     * toString for HoldemPlayer
     * Contains player string, blind, and handChips
     */
    @Override
    public String toString() {
        String retString = super.toString() + "\nChips in: " + handChips + "\nAction Taken: " + currentAction + "\nIn: " + isActive;
        if (isMain) {
            retString += ("\nHand: " + cards);
        }
        retString += "\n";
        return retString;
    }
}
