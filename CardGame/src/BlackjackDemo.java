public class BlackjackDemo {
    public static void main(String[] args) {
        Player[] players = new Player[1];
        players[0] = new BlackjackPlayer(100, true);

        Blackjack game = new Blackjack(players);
        game.setup();
        game.play();
    }
}
