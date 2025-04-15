/**
 * Player class, represents a player in one of the games
 * @author Luke Soda
 * @version 1.0
 */

public class Player {
    protected String name; // Player name ex. P1
    protected CardList cards; // A card list, the player's hand
    protected int chips; // Amount of chips (Will probably be an object later, this is a placeholder)
    protected boolean isMain; // Is the player the user?

    /**
     * Constructor for player class
     * @param name Pass in player name
     * @param chips Pass in int number of chips
     * @param isMain Pass in whether or not the player is the main user
     */
    public Player (String name, int chips, boolean isMain) {
        this.name = name;
        this.chips = chips;
        this.isMain = isMain;
        cards = new CardList(true); // Create new empty hand for the player
    }

    /**
     * toString for player class
     * @return String representing the players status. Contains name, isMain, chips, and current hand
     */
    @Override
    public String toString() {
        return ("Name: " + name + "\nMain: " + isMain + "\nChips: " + chips + "\nCards: " + cards + "\n\n");
    }
}
