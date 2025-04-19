public class Blackjack extends CardGame {
    private BlackjackPlayer dealer;

    public Blackjack(Player[] playerList) {
        super(playerList);
        dealer = new BlackjackPlayer(0, false); // Dealer is not the main player
    }

    @Override
    public void setup() {
        super.deal(2);
        dealer.cards = new CardList(true);
        dealer.cards.add(deck.getCard(0));
        deck.remove(deck.getCard(0));
        dealer.cards.add(deck.getCard(0));
        deck.remove(deck.getCard(0));
    }

    public void play() {
        for (Player p : playerList) {
            BlackjackPlayer bp = (BlackjackPlayer) p;
            while (bp.isHitting()) {
                Card card = deck.getCard(0);
                bp.cards.add(card);
                deck.remove(card);
                System.out.println("Player " + bp.playerID + " hits and gets " + card);
                if (bp.getScore() > 21) {
                    System.out.println("Player " + bp.playerID + " busts!");
                    break;
                }
            }
        }

        // Dealer plays
        while (dealer.getScore() < 17) {
            Card card = deck.getCard(0);
            dealer.cards.add(card);
            deck.remove(card);
            System.out.println("Dealer hits and gets " + card);
        }
        System.out.println("Dealer's final hand: " + dealer.cards + " (Score: " + dealer.getScore() + ")");

        // Determine winners
        for (Player p : playerList) {
            BlackjackPlayer bp = (BlackjackPlayer) p;
            int score = bp.getScore();
            int dealerScore = dealer.getScore();
            if (score > 21) {
                System.out.println("Player " + bp.playerID + " loses.");
            } else if (dealerScore > 21 || score > dealerScore) {
                System.out.println("Player " + bp.playerID + " wins!");
            } else if (score == dealerScore) {
                System.out.println("Player " + bp.playerID + " pushes.");
            } else {
                System.out.println("Player " + bp.playerID + " loses.");
            }
        }
    }
}
