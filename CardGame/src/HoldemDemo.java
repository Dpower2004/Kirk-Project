public class HoldemDemo {
    public static void main(String[] args) {
        Player[] players = new Player[4];
        players[0] = new HoldemPlayer(10000000, false);
        players[1] = new HoldemPlayer(50, false);
        players[2] = new HoldemPlayer(150, true);
        players[3] = new HoldemPlayer(60, false);
        Holdem game = new Holdem(players, 2);
        game.startGame();
    }
}
