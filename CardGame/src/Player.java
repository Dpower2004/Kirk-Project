/**
 * Player class, represents a player in one of the games
 * @author Luke Soda
 * @version 1.0
 */

public abstract class Player {
    public static int playerCount; // Static int used to keep track of the number of players

    protected int playerID; // The player's ID
    protected CardList cards; // A card list, the player's hand
    protected int totalChips; // Amount of chips (Will probably be an object later, this is a placeholder)
    protected boolean isMain; // Is the player the user?

    /**
     * Constructor for player class
     * @param chips Pass in int number of chips
     * @param isMain Pass in whether or not the player is the main user
     */
    public Player (int chips, boolean isMain) {
        totalChips = chips;
        this.isMain = isMain;
        playerID = playerCount;
        playerCount++;
        cards = new CardList(true); // Create new empty hand for the player
    }

    /**
     * toString for player class
     * @return String representing the players status. Contains name, isMain, chips, and current hand
     */
    @Override
    public String toString() {
        return "\nPlayer " + playerID + "\nBank: " + totalChips;
    }
}
