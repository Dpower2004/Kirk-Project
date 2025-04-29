import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Class representing a game of Blackjack. It inherits the abstract game class.
 * @author Thomas Huber
 *
 * @version 1.5
 */

public class Blackjack extends CardGame
{
    /**
     * Make sure dealer is created -- essential for game
     */
    private BlackjackPlayer dealer;
    private Scanner reader = new Scanner(System.in);
    private int playerBet = 5;
    private boolean canDoubleDown = true;

    /**
     * Constructor for Blackjack object. Sends array of players to CardGame super class
     * Defines dealer as a non-main player
     * @param playerList the list of players
     */
    public Blackjack(Player[] playerList)
    {
        super(playerList);
        dealer = new BlackjackPlayer(0, false); // Dealer is not the main player
    }

    /**
     * Sets up game for playing
     * Overridden from CardGame
     */
    @Override
    public void setup()
    {
        playerList[0].playerID = 0;
        super.deal(2); // deals 2 cards
        dealer.cards = new CardList(true);
        dealer.cards.add(deck.getCard(0));
        deck.remove(deck.getCard(0));
        dealer.cards.add(deck.getCard(0));
        deck.remove(deck.getCard(0));
    }

    /**
     * Some logic to handle blackjack betting fucntionality
     * @param bp the blackjack player in question
     */
    public void bet(BlackjackPlayer bp)
    {
        if (bp.isMain == false)
        {
            bp.comBetAmt = ThreadLocalRandom.current().nextInt(5, 41);
            System.out.println("Player " + bp.playerID + " bets " + bp.comBetAmt + " chips.");
            bp.chipBank.removeChips(bp.comBetAmt);
        }
        else
        {
            System.out.println("Player " + bp.playerID + " bets " + playerBet + " chips.");
            bp.chipBank.removeChips(playerBet);
        }
    }

    /**
     * Method that initiates the start of the game
     */
    public void play()
    {
        // betting stuff
        playerBet = InputValidator.validateBJBet(reader, "\nHow much would you like to bet? (Min = 5 chips, Max = 40 chips) ", playerList[0].chipBank.chipAmount, 40, 5);
        
        // loop to show chips
        for (int i = 0; i < playerList.length; i++)
        {
            Player p = playerList[i];
            BlackjackPlayer bp = (BlackjackPlayer) p;
            bp.playerID = playerList[i].playerID + i + 1;
            System.out.println("Player " + bp.playerID + "'s chips: "+ bp.chipBank.chipAmount);
            bet(bp);
            System.out.println("Player " + bp.playerID + "'s chips after bet: "+ bp.chipBank.chipAmount);
        }
        //loop to show other player's initial hands
        for (int i = 1; i < playerList.length; i++)
        {
            Player p = playerList[i];
            BlackjackPlayer bp = (BlackjackPlayer) p;
            System.out.println("Player " + bp.playerID + "'s intial hand: " + bp.cards + " (Score: " + bp.getScore() + ")");
        }
        Player main = playerList[0];
        BlackjackPlayer bpMain = (BlackjackPlayer) main;
        System.out.println("\nPlayer " + bpMain.playerID + "'s (your) intial hand: " + bpMain.cards + " (Score: " + bpMain.getScore() + ")");
        System.out.println("Dealer reveals a [" + dealer.cards.getCard(0) + "] ...");
        for (int i = 0; i < playerList.length; i++) // for each player
        {
            Player p = playerList[i];
            BlackjackPlayer bp = (BlackjackPlayer) p;
            //bp.playerID = playerList[i].playerID + i + 1;
            while (bp.isHitting(bp.chipBank.chipAmount, playerBet))
            {
                // add card from deck if hit
                Card card = deck.getCard(0);
                bp.cards.add(card);
                deck.remove(card);
                if (bp.isDoubling && canDoubleDown)
                {
                    System.out.println("Player " + bp.playerID + " doubles down and gets " + card);
                    bp.chipBank.chipAmount = bp.chipBank.chipAmount - playerBet;
                    playerBet = playerBet * 2;
                    System.out.println("Player " + bp.playerID + "'s new bet: " + playerBet);
                    System.out.println("Player " + bp.playerID + "'s new chip amount: " + bp.chipBank.chipAmount);
                    canDoubleDown = false;
                }
                else
                {
                    System.out.println("Player " + bp.playerID + " hits and gets " + card);
                }
                if(bp.isMain)
                {
                    System.out.println("Player " + bp.playerID + "'s hand: " + bp.cards + " (Score: " + bp.getScore() + ")");
                }
                if (bp.getScore() > 21) // bust of score > 21
                {
                    System.out.println("Player " + bp.playerID + " busts!");
                    break;
                }
            }
            System.out.println("\nPlayer " + bp.playerID + "'s hand: " + bp.cards + " (Score: " + bp.getScore() + ")");
        }

        // Dealer plays -- must hit if score < 17
        while (dealer.getScore() < 17)
        {
            // draw card
            Card card = deck.getCard(0);
            dealer.cards.add(card);
            deck.remove(card);
            System.out.println("Dealer hits and gets " + card);
        }
        if (dealer.getScore() > 21)
        {
            System.out.println("Dealer's final hand: " + dealer.cards + " (Score: " + dealer.getScore() + ")");
            System.out.println("Dealer busts!");
        }
        else
        {
            System.out.println("Dealer's final hand: " + dealer.cards + " (Score: " + dealer.getScore() + ")");
        }
        
        // Determine winners
        for (Player p : playerList)
        {
            BlackjackPlayer bp = (BlackjackPlayer) p;
            int score = bp.getScore();
            int dealerScore = dealer.getScore();
            if (score > 21)
            {
                System.out.println("Player " + bp.playerID + " loses.");
                System.out.println("Player " + bp.playerID + " chips: " + bp.chipBank.chipAmount);
            }
            else if (dealerScore > 21 || score > dealerScore)
            {
                System.out.println("Player " + bp.playerID + " wins!");
                if (bp.isMain)
                {
                    bp.chipBank.chipAmount = bp.chipBank.chipAmount + playerBet * 2;
                    System.out.println("Player " + bp.playerID + " chips: " + bp.chipBank.chipAmount);
                }
                else
                {
                    bp.chipBank.chipAmount = bp.chipBank.chipAmount + bp.comBetAmt * 2;
                    System.out.println("Player " + bp.playerID + " chips: " + bp.chipBank.chipAmount);
                }
            }
            else if (score == dealerScore)
            {
                System.out.println("Player " + bp.playerID + " pushes.");
                if (bp.isMain)
                {
                    bp.chipBank.chipAmount = bp.chipBank.chipAmount + playerBet;
                    System.out.println("Player " + bp.playerID + " chips: " + bp.chipBank.chipAmount);
                }
                else
                {
                    bp.chipBank.chipAmount = bp.chipBank.chipAmount + bp.comBetAmt;
                    System.out.println("Player " + bp.playerID + " chips: " + bp.chipBank.chipAmount);
                }
            }
            else
            {
                System.out.println("Player " + bp.playerID + " loses.");
                System.out.println("Player " + bp.playerID + " chips: " + bp.chipBank.chipAmount);
            }
        }
    }
    public BlackjackPlayer getDealer(){
        return dealer;
    }
}