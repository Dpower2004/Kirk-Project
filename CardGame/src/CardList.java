import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a list of cards. Can be used as for player hands, full deck, etc.
 * @author Luke Soda
 * @version 1.01
 */
public class CardList {
    final String[] RANKLIST = {"2", "3", "4", "5", "6", "7", "8", "9", "10",    // All possible ranks
                               "J", "Q", "K", "A"};
    final String[] SUITLIST = {"S", "C", "H", "D"};                             // All possible suits
    protected ArrayList<Card> cardArray = new ArrayList<>();                     // List of cards. The "hand"

    /**
     * Constructor initiallizes cardArray
     * Have the option of empty hand or add all 52 cards to the deck
     * @param empty passed in boolean. If true, object will be a cardArray with no card.
     *              If false, cardArray will be a full 52 card deck.
     */
    public CardList(boolean empty) {
        if (!empty) {
            for (String rank : RANKLIST) {      // For loops iterate through every possible combination of rank and suit
                for (String suit : SUITLIST) {
                    cardArray.add(new Card(rank, suit)); // Add each combo to this list with a new card object
                }
            }
            this.shuffle(); // Shuffle the deck upon creation. Only shuffled for full 52 card decks
        }
    }

    /**
     * No arg default constructor.
     * If no bool is passed in, it assume empty card list.
     */
    public CardList() {
        this(true);
    }

    /**
     * Removes a passed in card object from the hand.
     * Looks through each card in the list. Calls the equals method of the card object to check
     * if passed in is equal to current index in list. If it is it removes it and exits the loop
     * @param card passed in card object to be removed
     */
    public void remove(Card card) {
        for (int i = 0 ; i < cardArray.size() ; i++) { // Iterates through all cards in the list
            if (cardArray.get(i).equals(card)) { // If passed in card object is equal to card at current index
                cardArray.remove(i);             // remove it
                i = cardArray.size();            // Break out of the loop
            }
        }
    }

    /**
     * Remove all cards from card list, clearing the hand / deck
     */
    public void clear() {
        for (int i = (cardArray.size() - 1); i >= 0; i--) { // Start at final index, remove backwards
            cardArray.remove(i);
        }
    }

    /**
     * Adds a passed in card object to the cardArray
     * @param card passed in card to be added to the arrayList
     */
    public void add(Card card) {
        cardArray.add(card);
    }

    /**
     * Shuffles all card elements in the deck
     * Using Collections utility class
     */
    public void shuffle() {
        Collections.shuffle(cardArray); // Shuffle the deck (It's just that easy)
    }

    /**
     * Returns the list of cards
     * @return ArrayList<Card> of cards
     */
    public ArrayList<Card> getCardList() {
        return cardArray;
    }

    /**
     * Returns card at passed in index
     * @return Card object at specified index
     */
    public Card getCard(int index) {
        return new Card(cardArray.get(index)); // Return specified index
    }

    /**
     * toString for cardArray class
     * @return Card ArrayList in string form
     */
    @Override
    public String toString() {
        return cardArray.toString();
    }
}
