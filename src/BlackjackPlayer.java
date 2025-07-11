

/**
 * Class representing Blackjack player. It inherits the abstract player class 
 * @author Thomas Huber
 * @version 1.3
 */

import java.util.Scanner;

public class BlackjackPlayer extends Player
{
    /**
     * Make Scanner object for BlackjackPlayer
     */
    private Scanner scanner;
    /**
     * Amount of chips computer bets
     */
    protected int comBetAmt;
    /**
     * boolean for if player is doubling down
     */
    protected boolean isDoubling;


    /**
     *
     * Constructor for BlackjackPlayer
     * @param chips the amount of chips the player has
     * @param isMain if true, this is the user (not a computer player)
     */
    public BlackjackPlayer(int chips, boolean isMain)
    {

        super(chips, isMain, 0.0); // Calls parent constructor
        if (isMain)
        {
            scanner = new Scanner(System.in); // only make scanner if the player is the main player
        }
    }

    /**
     * Boolean to see if player (user or otherwise) wants to hit or stand
     * @return getScore() < 17 -- player hits if they're not main and score is < 17
     * OR input.equals("H") -- true if user is hitting (inputs H)
     */
    public boolean isHitting(int chipAmount, int playerBet)
    {
        if (!isMain)
        {
            return getScore() < 17;// Dealer must hit if less than 17
            // Other computer players will also follow this behavior
        }
        String input = InputValidator.validateHitStandDouble("Hit, Stand or Double Down? (H/S/D): ", scanner);
        if (input.equals("D"))
        {
            isDoubling = InputValidator.validateDoubleDown(chipAmount, playerBet);
        }
        return input.equals("H") || input.equals("D");
    }

    /**
     * Method to set/get score of arbitratry player
     * @return score the score of arbitrary player
     */
    public int getScore()
    {
        int score = 0;
        int aceCount = 0;

        for (Card card : cards.getCardList()) // Go thru every card in player's hand
        {
            String rank = card.getRank();
            if (rank.equals("A"))
            {
                score += 11; // increase score by 11 by default for ace
                aceCount++;
            }
            else if ("KQJ".contains(rank) || rank.equals("10"))
            {
                score += 10; // face cards have a value of 10
            }
            else
            {
                score += Integer.parseInt(rank);
            }
        }
        
        // Dynamically change value of ace if it would make player bust
        while (score > 21 && aceCount > 0)
        {
            score -= 10; // Convert an Ace from 11 to 1
            aceCount--;
        }

        return score;
    }

    
}
