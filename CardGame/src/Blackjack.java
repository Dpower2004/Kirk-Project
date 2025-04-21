/**
 * Class representing a game of Blackjack. It inherits the abstract game class.
 * @author Thomas Huber
 * @version 1.0
 */

public class Blackjack extends CardGame
{
    /**
     * Make sure dealer is created -- essential for game
     */
    private BlackjackPlayer dealer;

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
        super.deal(2); // deals 2 cards
        dealer.cards = new CardList(true);
        dealer.cards.add(deck.getCard(0));
        deck.remove(deck.getCard(0));
        dealer.cards.add(deck.getCard(0));
        deck.remove(deck.getCard(0));
    }

    /**
     * Method that initiates the start of the game
     */
    public void play()
    {
        System.out.println("Dealer reveals a [" + dealer.cards.getCard(0) + "] ...");
        for (Player p : playerList) // for each player
        {
            BlackjackPlayer bp = (BlackjackPlayer) p;
            while (bp.isHitting())
            {
                // add card from deck if hit
                Card card = deck.getCard(0);
                bp.cards.add(card);
                deck.remove(card);
                System.out.println("Player " + bp.playerID + " hits and gets " + card);
                if (bp.getScore() > 21) // bust of score > 21
                {
                    System.out.println("Player " + bp.playerID + " busts!");
                    break;
                }
            }
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
        System.out.println("Dealer's final hand: " + dealer.cards + " (Score: " + dealer.getScore() + ")");

        // Determine winners
        for (Player p : playerList)
        {
            BlackjackPlayer bp = (BlackjackPlayer) p;
            int score = bp.getScore();
            int dealerScore = dealer.getScore();
            if (score > 21)
            {
                System.out.println("Player " + bp.playerID + " loses.");
            }
            else if (dealerScore > 21 || score > dealerScore)
            {
                System.out.println("Player " + bp.playerID + " wins!");
            }
            else if (score == dealerScore)
            {
                System.out.println("Player " + bp.playerID + " pushes.");
            }
            else
            {
                System.out.println("Player " + bp.playerID + " loses.");
            }
        }
    }
}
