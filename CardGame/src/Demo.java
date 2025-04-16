public class Demo {
    public static void main(String[] args) {
        Player[] players = new Player[4];
        players[3] = new HoldemPlayer(10000000, false, Blind.NONE);
        players[0] = new HoldemPlayer(50, false, Blind.SMALL);
        players[1] = new HoldemPlayer(150, true, Blind.BIG);
        players[2] = new HoldemPlayer(60, false, Blind.NONE);
        Holdem game = new Holdem(players, 50, 2);
        game.setup();
        System.out.println(game);
        game.bettingRound();
        System.out.println(game);
        game.bettingRound();
        System.out.println(game);
        game.bettingRound();
        System.out.println(game);
    }
}
