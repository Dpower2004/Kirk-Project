public class HoldemDemo {
    public static void main(String[] args) {
        /*HoldemPlayer sample = new HoldemPlayer(10000000, false);
        sample.cards.add(new Card("3", "D"));
        sample.cards.add(new Card("3", "C"));
        
        CardList communityCards = new CardList(true);
        communityCards.add(new Card("5", "D"));
        communityCards.add(new Card("5", "H"));
        communityCards.add(new Card("10", "H"));
        communityCards.add(new Card("10", "C"));
        communityCards.add(new Card("Q", "H"));
        HoldemHandValue test = new HoldemHandValue(sample, communityCards);
        System.out.println(test);*/

        Player[] players = new Player[4];
        players[0] = new HoldemPlayer(10000000, false);
        players[1] = new HoldemPlayer(50, false);
        players[2] = new HoldemPlayer(150, true);
        players[3] = new HoldemPlayer(60, false);
        Holdem game = new Holdem(players, 2);
        game.startGame();
    }
}
