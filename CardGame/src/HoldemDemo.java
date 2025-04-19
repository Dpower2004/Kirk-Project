public class HoldemDemo {
    public static void main(String[] args) {
        Player[] players = new Player[1];
        HoldemPlayer sample = new HoldemPlayer(10000000, false);
        players[0] = sample;
        sample.cards.add(new Card("2", "S"));
        sample.cards.add(new Card("2", "C"));
        
        CardList communityCards = new CardList(true);
        communityCards.add(new Card("2", "H"));
        communityCards.add(new Card("3", "D"));
        communityCards.add(new Card("6", "S"));
        communityCards.add(new Card("7", "S"));
        communityCards.add(new Card("8", "S"));
        HoldemResults test = new HoldemResults(players, communityCards);
        test.sortAll();

        /*
        players[1] = new HoldemPlayer(50, false);
        players[2] = new HoldemPlayer(150, true);
        players[3] = new HoldemPlayer(60, false);
        Holdem game = new Holdem(players, 2);
        game.startGame();*/
    }
}
