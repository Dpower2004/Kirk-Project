public class Demo {
    public static void main(String[] args) {
        Player[] players = new Player[3];
        players[0] = new Player("p1", 30, false);
        players[1] = new Player("p2", 20, false);
        players[2] = new Player("p3", 10, true);
        CardGame game = new CardGame(players);
        game.deal(3);
        System.out.println(game);
    }
}
