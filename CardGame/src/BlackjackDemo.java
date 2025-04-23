/**
 * Class that tests the BHlackjack game.
 * @author Thomas Huber
 * @version 1.0
 */

public class BlackjackDemo
{
    public static void main(String[] args)
    {
        Player[] players = new Player[3];
        players[0] = new BlackjackPlayer(100, true);
        players[1] = new BlackjackPlayer(100, false);
        players[2] = new BlackjackPlayer(100, false);

        Blackjack game = new Blackjack(players);
        game.setup();
        game.play();
    }
}
