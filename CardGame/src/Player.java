/**
 * Player class, represents a player in one of the games
 * @author Luke Soda
 * @version 1.0
 */

public abstract class Player {
    protected int playerID; // The player's ID
    protected int money;
    protected CardList cards; // A card list, the player's hand
    protected ChipStack chipBank; // Amount of chips (Will probably be an object later, this is a placeholder)
    protected boolean isMain; // Is the player the user?

    /**
     * Constructor for player class
     * @param chips Pass in int number of chips
     * @param isMain Pass in whether or not the player is the main user
     */
    public Player (int chips, boolean isMain) {
        this.isMain = isMain;
        chipBank = new ChipStack(chips);
        cards = new CardList(true); // Create new empty hand for the player
    }

    /**
     * toString for player class
     * @return String representing the players status. Contains name, isMain, chips, and current hand
     */
    @Override
    public String toString() {
        return "\nPlayer " + playerID + "\nBank: " + chipBank.chipAmount;
    }
}
