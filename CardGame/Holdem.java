import java.util.ArrayList;

/**
 * Class representing the game texas holdem. It inherits the abstract game class
 * @author Luke Soda
 * @version 1.0
 */

public class Holdem extends CardGame {
    protected int buyIn; // Minimum number of chips to play
    protected int highBet; // Holds current highest bet for a current betting round. Is also used as passed in
                           // big blind for first round of bettings
    protected CardList communityCards; // 5 community cards that make up the river
    protected int pot; // Total pot that is taken at the end of each game
    protected ArrayList<HoldemPlayer> activePlayers = new ArrayList<>(); // List of players who haven't folded

    /**
     * Constructor for a round of texas holdem
     * @param playerList // List of players in the game. Will always be 4 including the dealer for our purposes
     * @param buyIn // Minimum number of chips a player needs to have in order to play
     * @param highBet // Stores initial big blind for the first round of betting
     */
    public Holdem(Player[] playerList, int buyIn, int highBet) {
        super(playerList); // Calls super class, abstract game, passing in playerList for creation
        this.buyIn = buyIn;
        this.highBet = highBet;
    }

    /**
     * Function called to setup a round of holdem
     */
    @Override
    public void setup() {
        for (int i = 0 ; i < playerList.length ; i++) { // Cycle through all players to determine who needs to put
                                                        // money in first
            HoldemPlayer currentPlayer = (HoldemPlayer) playerList[i]; // Uses type casting to allow HoldemPlayer method calls
            activePlayers.add(currentPlayer); // Add all players to active players arrayList
            switch(currentPlayer.blind) { // Looking for who is what blind
                case SMALL:
                    currentPlayer.addInChips(highBet / 2); // Small blind puts in half the passed in high bet
                    break;
                case BIG:
                    currentPlayer.addInChips(highBet); // Big blind puts in the high bet
                    break;
                default:
                    currentPlayer.addInChips(0); // Everyone else puts in 0 for setup
            }
        }
        super.deal(2); // Deal 2 cards to all players
    }

    /**
     * Initiates a single betting round for all players
     */
    public void bettingRound() {
        int pTurn = 0; // Keeps track of player turn
        for (int i = 0 ; i < playerList.length ; i++) { // Iterate a number of time = how many players
            if (pTurn > playerList.length - 1) { // If turn goes above player count, cycle back to player 0
                pTurn = 0;
            }
            HoldemPlayer currentPlayer = (HoldemPlayer) playerList[pTurn]; // Uses type casting to allow HoldemPlayer method calls
            currentPlayer.chooseAction(); // Prompts each player for their choise to check, raise, or fold
            switch (currentPlayer.currentAction) {
                case "C": // If check
                    currentPlayer.addInChips(highBet - currentPlayer.inChips); // Match the current bet
                    break;
                case "B": // If bet
                    currentPlayer.addInChips(currentPlayer.toBet); // Add bet onto player's chips
                    highBet += currentPlayer.toBet; // Update high bet to be the highest player's chip value
                    i = -1; // RESET the number iterations, allows for all players to respond to the new bet
                    pTurn = 0; // Reset turn counter 
                    break;
                case "F": // If fold
                    currentPlayer.fold(); // isActive = false
                    activePlayers.remove(currentPlayer); // Remove player from the active list
                    break;
            }
            pTurn++; // Increment turn counter
        }
        for (int i = 0 ; i < playerList.length ; i++) { // For all the players...
            HoldemPlayer hp = (HoldemPlayer) playerList[i];
            pot += hp.inChips; // Add their chips into the pot
            hp.inChips = 0; // Reset what they have up to 0
        }
    }

    /**
     * toString for Holdem class
     */
    @Override
    public String toString() {
        return super.toString() + "Pot: " + pot + "\nDeck: " + super.deck;
    }
}
