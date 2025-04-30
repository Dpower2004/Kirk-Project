
/**
 * Class that tests the Blackjack game.
 * @author Thomas Huber
 * @version 1.2
 */

public class BlackjackDemo
{
    public static void main(String[] args)
    {
        Player[] players = new Player[3];
        players[0] = new BlackjackPlayer(50, true); // players[0] should ALWAYS be main for things to work right
        players[1] = new BlackjackPlayer(100, false);
        players[2] = new BlackjackPlayer(100, false);

        Blackjack game = new Blackjack(players);
        game.setup();
        game.play();
    }
}
