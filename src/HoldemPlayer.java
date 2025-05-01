
/**
 * Class representing the game texas holdem. It inherits the abstract game class
 * @author Luke Soda
 * @version 1.0
 */

import java.util.Random;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class HoldemPlayer extends Player {
    protected ChipStack handChips; // Chips up for a given round
    protected String currentAction; // Current action the player is taking for a current round
    protected boolean isActive; // Is the player still playing? (Have they folded)
    protected int maxRoundChips = chipBank.chipAmount;
    protected Hand handValue; // Value of the player's hand (enum)
    protected Card highCard; // Highest card in 2 card hand
    protected Card secondHighCard; // Second highest card (whats left over)
    protected HBox cardBox = new HBox(48); // Box that holds each player's card images for javafx
    protected String mainPlayerAction; // JavaFX sets this when using buttons for things like betting
    protected int mainPlayerBet; // Same as above but for entering an integer bet / raise
    protected Font myFont = Font.loadFont(getClass().getResourceAsStream("Super-Mario-64-DS.ttf"), 20); // Declare font
    protected Label bankLabel; // Bank display for holdem main player
    protected ImgExpand foldMarker = new ImgExpand(new Image("/gameAssets/foldMarker.png")); // Every player has a fold marker
                                                                                             // which is visible when they fold

    /**
     * HoldemPlayer constructor, pass in chips, is the player main, and type of blind
     * @param chips Total number of chips the player has with them
     * @param isMain Is the player the user?
     *
     */
    public HoldemPlayer(int chips, boolean isMain) {
        super(chips, isMain,0.0); // Super constructor for abstract player class
        foldMarker.setVisible(false);
        isActive = true; // Player is set to active by default (they have not folded)
        handChips = new ChipStack(0);
        bankLabel = new Label("Bank: " + chipBank.chipAmount);
        bankLabel.setFont(myFont);
        bankLabel.setStyle("-fx-text-fill: white;");
    }
  
    /**
     * Sets the foldMarker position for each player. Must be aligned properly based on playerID set in Holdem.java
     */
    public void setFoldPosition() {
        switch (playerID) { // tests all four players, sets positions accordingly
            case 0:
                foldMarker.setLayoutX(1064);
                foldMarker.setLayoutY(416);
                break;
            case 1:
                foldMarker.setLayoutX(1064);
                foldMarker.setLayoutY(636);
                break;
            case 2:
                foldMarker.setLayoutX(652);
                foldMarker.setLayoutY(636);
                break;
            case 3:
                foldMarker.setLayoutX(244);
                foldMarker.setLayoutY(636);
                break;
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
        if (isMain) {
            bankLabel.setText("Bank: " + chipBank.chipAmount);
        }
    }

    /**
     * Player folds
     */
    public void fold() {
        foldMarker.setVisible(true); // Show foldMarker
        isActive = false; // Set active state to false
    }

    /**
     * Handles CPUs choosing their action for a round
     * Handles user input for main player
     * @Return Action string landed on. Returned to betting turn in Holdem.java
     */
    public String chooseAction(int highBet, boolean betMade) {
        if (isMain) { // If player is user, currentAction = JavaFX input for player action
            currentAction = mainPlayerAction;
            if (currentAction.equals("B")) { // Only set up with bet button, so it will always count as B, so...
                if (handChips.chipAmount != 0 || betMade == true) { // If it should be a raise instead, make it so
                    currentAction = "R";
                }
                else {
                    currentAction = "B"; // Else keep it a standard bet
                }
            }
        }
        else { // If the player is NOT the user
            Random rand = new Random();
            int actionInt = rand.nextInt(100); // Find random number up to 100
            if (actionInt < 70) { // Check / Call 70% chance
                currentAction = "C";
            }
            else if (actionInt >= 70 && actionInt < 90) { // Bet / raise 20%
                if (highBet < chipBank.chipAmount) {
                    if (handChips.chipAmount != 0 || betMade == true) {
                        currentAction = "R";
                    }
                    else {
                        currentAction = "B";
                    }
                }
                else { // Even then if they cannot afford to they must check
                    currentAction = "C";
                }
            }
            else { // 10% chance to fold
                currentAction = "F";
            }
        }
        return currentAction;
    }

    /**
     * calculates what the cpu should be (basically random)
     * @param highBet
     * @Return Integer bet returned to Holdem.java
     */
    public int cpuBet(int highBet) {
        Random rand = new Random();
        int bet = rand.nextInt(22) + 3; // between 3 and 25
        if (currentAction.equals("R") && highBet + bet > chipBank.chipAmount) { // Ensures that computer cannot go above bank
            bet = maxRoundChips - highBet; // Makes sure to meet at bankMax
        }
        if (currentAction.equals("B") && bet > chipBank.chipAmount) { // Same for this but with bet
            bet = maxRoundChips;
        }
        return bet;
    }

    /**
     * Assigns an enum value to the current 7 card hand
     * @param communityCards 5 community cards in holdem
     */
    public void assignHandValue(CardList communityCards) {
        HoldemHandValue value = new HoldemHandValue(this, communityCards); // New hand evaluator for each player
        handValue = value.handValue; // handValue enum = result of evaluation
        highCard = value.highCard; // Finds the highCard
        secondHighCard = value.secondHighCard; // Second highest card in 2 card hand
    }

    /**
     * Returns a numerical value for each enum stage
     * @return
     */
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
