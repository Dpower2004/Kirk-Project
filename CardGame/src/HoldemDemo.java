public class HoldemDemo {
    public static void main(String[] args) {
        HoldemPlayer sample = new HoldemPlayer(10000000, false);
        sample.cards.add(new Card("2", "H"));
        sample.cards.add(new Card("3", "H"));
        
        CardList communityCards = new CardList(true);
        communityCards.add(new Card("4", "H"));
        communityCards.add(new Card("5", "H"));
        communityCards.add(new Card("6", "H"));
        communityCards.add(new Card("5", "D"));
        communityCards.add(new Card("6", "H"));
        HoldemHandValue test = new HoldemHandValue(sample, communityCards);
        System.out.println(test);

        /*
        players[1] = new HoldemPlayer(50, false);
        players[2] = new HoldemPlayer(150, true);
        players[3] = new HoldemPlayer(60, false);
        Holdem game = new Holdem(players, 2);
        game.startGame();*/
    }
}
