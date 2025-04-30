
/**
 * Abstract class that all card games will inherit from
 * @author Luke Soda
 * @version 1.0
 */

public abstract class CardGame {
    protected Player[] playerList; // Array of players. There will likely always be 3
    protected CardList deck = new CardList(false); // Create a new full shuffled deck to be delt from

    /**
     * Constructor. Pass in array of player objects
     * @param playerList Array of players in the game
     */
    public CardGame(Player[] playerList) {
        this.playerList = playerList;
    }

    /**
     * Deals specified number of cards out to each players
     * @param cardNum Number of cards to deal
     */
    public void deal(int cardNum) {
        Card currentCard; // Holds index 0
        for (int j = 1 ; j <= cardNum ; j++) { // Deal 1 card to each player, cycle through this for specified number
            for (int i = 0 ; i < playerList.length ; i++) {
                currentCard = deck.getCard(0); // Set currentCard to 0th index
                playerList[i].cards.add(currentCard); // Add the current card to the current player's hand
                deck.remove(currentCard); // Remove the card from the deck
            }
        }
    }
    public abstract void setup();

    /**
     * toString for CardGame class
     * @return retString String containing values about the game, such as player info and the deck
     */
    @Override
    public String toString() {
        String retString = "";
        for (int i = 0 ; i < playerList.length ; i++) { // Cycle through Player toStrings, add them on
            retString += playerList[i].toString();
        }
        return retString;
    }
}
