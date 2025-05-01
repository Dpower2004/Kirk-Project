/**
 * Player class, represents a player in one of the games
 * @author Luke Soda
 * @version 1.0
 */

public  class Player {
    protected int playerID; // The player's ID
    protected double money; // Player money, different from chips
    protected CardList cards; // A card list, the player's hand
    protected ChipStack chipBank; // Amount of chips (Will probably be an object later, this is a placeholder)
    protected boolean isMain; // Is the player the user?

    /**
     * Constructor for player class
     * @param chips Pass in int number of chips
     * @param isMain Pass in whether or not the player is the main user
     */
    public Player (int chips, boolean isMain, Double money) {
        this.isMain = isMain;
        this.money = money;
        chipBank = new ChipStack(chips); // Make new chipBank based on passed in chips
        cards = new CardList(true); // Create new empty hand for the player
    }

    /**
     * getMoney method - method to get double value in money
     * @return money - Player cash balance
     */
    public double getMoney() {
        return money;
    }

    /**
     * getChips method - method to get int value in chipBank's chip variable
     * @return int - Player chip balance
     */
    public int getChips() {
        return chipBank.getChips();
    }

    /**
     * setMoney method - method to set the current cash balance of a Player
     * @param money - new balance of player
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * setChips - method to set current chip Balance of PLayer
     * @param chips - new chip balance of Player
     */
    public void setChips(int chips) {
        chipBank.setChips(chips);
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
