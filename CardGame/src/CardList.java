import java.util.ArrayList;

/**
 * Represents a list of cards. Can be used as for player hands, full deck, etc.
 * @author Luke Soda
 * @version 1.0
 */
public class CardList {
    final String[] RANKLIST = {"2", "3", "4", "5", "6", "7", "8", "9", "10",    // All possible ranks
                               "J", "Q", "K", "A"};
    final String[] SUITLIST = {"S", "C", "H", "D"};                             // All possible suits
    protected ArrayList<Card> cardList = new ArrayList<>();                     // List of cards. The "hand"

    /**
     * Constructor initiallizes cardList
     * Have the option of empty hand or add all 52 cards to the deck
     * @param empty passed in boolean. If true, object will be a cardList with no card.
     *              If false, cardList will be a full 52 card deck.
     */
    public CardList(boolean empty) {
        if (!empty) {
            for (String rank : RANKLIST) {      // For loops iterate through every possible combination of rank and suit
                for (String suit : SUITLIST) {
                    cardList.add(new Card(rank, suit)); // Add each combo to this list with a new card object
                }
            }
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
        for (int i = 0; i < cardList.size(); i++) { // Iterates through all cards in the list
            if (cardList.get(i).equals(card)) { // If passed in card object is equal to card at current index
                cardList.remove(i);             // remove it
                i = cardList.size();            // Break out of the loop
            }
        }
    }

    /**
     * Adds a passed in card object to the cardList
     * @param card passed in card to be added to the arrayList
     */
    public void add(Card card) {
        cardList.add(card);
    }

    /**
     * Returns the list of cards
     * @return ArrayList<Card> of cards
     */
    public ArrayList<Card> getCardList() {
        return cardList;
    }

    /**
     * toString for cardList class
     * @return Card ArrayList in string form
     */
    @Override
    public String toString() {
        return cardList.toString();
    }
}