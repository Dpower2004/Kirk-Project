public class Demo {
    public static void main(String[] args) {
        CardList deck = new CardList(false); // Create a full 52 card deck
        System.out.println(deck); // Print the deck to verify functionality
        deck.remove(new Card("2", "S")); // Remove the 2 of spades
        System.out.println(deck);
        deck.add(new Card("2", "S")); // Add back the 2 of spades
        System.out.println(deck);
    }
}
