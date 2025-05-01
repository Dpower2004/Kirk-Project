/**
 * Class representing the game texas holdem. It inherits the abstract game class
 * @author Luke Soda
 * @version 1.0
 */

import java.util.ArrayList;

public class Holdem extends CardGame {
    protected HoldemState gameState; // Enum representing the state of the game

    protected int highBet; // Current highest bet for a round. Contains big blind at start
    protected ChipStack pot = new ChipStack(0); // Total pot that is taken at the end of each game

    protected CardList communityCards = new CardList(true); // 5 community cards that make up the river. Start empty
    protected ArrayList<HoldemPlayer> activePlayers = new ArrayList<>(); // List of players who haven't folded
    protected ArrayList<HoldemPlayer> winners = new ArrayList<>(); // List of players who split the pot at the end of the game

    private boolean betMade; // Keeps track of if a bet has been made to change raise / bet prompt

    protected int turnIndex; // Keeps track of the index of the current player betting
    protected int remainingTurns; // Keeps track of the turns left in a betting round

    /**
     * Constructor for a round of texas holdem
     * @param playerList // List of players in the game. Will always be 4 including the dealer for our purposes
     * @param highBet // Stores initial big blind for the first round of betting
     */
    public Holdem(Player[] playerList, int highBet) {
        super(playerList); // Calls super class, abstract game, passing in playerList for creation
        this.highBet = highBet;
    }

    /**
     * Function called to setup a round of holdem
     * Adds all players to the list of active players (People who haven't folded)
     * Sets up initial bet values for the blinds
     */
    @Override
    public void setup() {
        for (int i = 0; i < playerList.length ; i++) { // Cycle through all players to set up blinds
            playerList[i].playerID = i;
            HoldemPlayer currentPlayer = (HoldemPlayer) playerList[i]; // Type casting for HoldemPlayer method calls
            currentPlayer.setFoldPosition(); // Sets JavaFX position of imageView containing fold marker
            activePlayers.add(currentPlayer); // Add all players to active players arrayList
            if (i == 1) { // Player at index 1 will ALWAYS be little blind
                currentPlayer.setHandChips(highBet / 2);
            } 
            else if (i == 2) { // Player at index 2 will ALWAYS be big blind
                currentPlayer.setHandChips(highBet);
            }
        }
        super.deal(2); // Deal 2 cards to all players
    }

    /**
     * Initiates a betting round that will eventually cycle through all players
     */
    public void bettingRound() {
        remainingTurns = activePlayers.size(); // Remaining turns equal to number of active players
        if (gameState == HoldemState.FIRST_BET) { // If its the first bet (blinds present) start from 4th player (3)
            turnIndex = 3;
        }
        else {                              // Else start at dealer (0)
            turnIndex = 0;
        }
        nextTurn();                         // Initiate the first turn
    }

    /**
     * Called on button press in JavaFX main
     */
    public void nextTurn() {
        bettingTurn();                      // Initiate a betting turn. Prompts all 4 players for what they want to do
        remainingTurns--; turnIndex++;      // take away a turn, move to next index
    }
    
    /**
     * Handles possible user inputs for a betting round
     */
    public void bettingTurn() {
        // If turn goes above player count, cycle back to player 0
        if (turnIndex > activePlayers.size() - 1) {
            turnIndex = 0;
        }
        /* pTurn and remainingTurns must be held seperate. This allows for all players to respond to a bet or raise if
        they have already gone once. */
        HoldemPlayer currentPlayer = activePlayers.get(turnIndex); // Type casting for HoldemPlayer method calls
        String choice = currentPlayer.chooseAction(highBet, betMade); // Prompt player for choice in betting round
        switch (choice) {
            case "C" -> {
                // If check / call
                if (currentPlayer.chipBank.chipAmount < highBet) {
                    currentPlayer.setHandChips(currentPlayer.handChips.chipAmount + currentPlayer.chipBank.chipAmount);
                    currentPlayer.currentAction = "A";
                }
                else {
                    currentPlayer.setHandChips(highBet); // Match the current high bet
                }
            }
            case "B" -> {
                // If bet
                int bet = getBet(currentPlayer);
                currentPlayer.setHandChips(bet); // Set chips in to players bet input
                highBet = bet; // Update highBet to be the current bet
                resetPlayerResponses(currentPlayer, turnIndex); // Resets the responses of all other players
                betMade = true; // Tell the game that a bet was made 
                if (currentPlayer.chipBank.chipAmount <= 0) {
                    currentPlayer.currentAction = "A";
                }
                remainingTurns = activePlayers.size(); // RESET the number of turns remaining, other players must respond
            }
            case "R" -> {
                // If raise
                int raise = getBet(currentPlayer); // Get the raise to be put up
                currentPlayer.setHandChips(highBet + raise); // Set chips in to highBet + the raise
                highBet += raise; // Add the raise onto the current highBet
                resetPlayerResponses(currentPlayer, turnIndex); // Resets the responses of other players
                if (currentPlayer.chipBank.chipAmount <= 0) {
                    currentPlayer.currentAction = "A";
                }
                remainingTurns = activePlayers.size(); // // RESET the number of turns remaining, other players must respond
            }
            case "F" -> {
                // If fold
                currentPlayer.fold(); // isActive = false
                activePlayers.remove(currentPlayer);
                turnIndex--;
            }
        }
    }

    /**
     * Updates the state of the pot and resets player chips for next betting round
     */
    public void updatePot() {
        highBet = 0;             // Reset high bet
        for (Player p : playerList) {   // For all players...
            HoldemPlayer hp = (HoldemPlayer) p;
            pot.addChips(hp.handChips.chipAmount); // Add chips to pot
            hp.handChips.setChips(0);       // reset chips
        }
    }

    /**
     * Resets responses for all players EXCEPT the current player
     * Used to allow all players to respond to change in the bet / raise
     * @param currentPlayer Current player in loop
     * @param pTurn Index of current player in playerList
     */
    public void resetPlayerResponses(HoldemPlayer currentPlayer, int pTurn) {
        // Update high bet to be the highest player's chip value
        int rTurn = pTurn + 1; // reset turn needs to start one more than current turn
        // This for loop is to reset the currentAction of all OTHER players on a raise. They need to react to raise
        for (int j = 0 ; j < activePlayers.size() - 1 ; j++, rTurn++) { // Iterate number of players - 1. Don't erase current
            if (rTurn > activePlayers.size() - 1) { // If reset turn goes above player count, cycle back to player 0
                rTurn = 0;
            }
            HoldemPlayer resetPlayer = (HoldemPlayer) activePlayers.get(rTurn); // Use casting to select current rTurn player
            resetPlayer.currentAction = ""; // Reset current rTurn player
        }
    }

    /**
     * Gets the bet for the currentPlayer turn. Used in bettingTurn()
     * @param currentPlayer Current player turn
     * @return Integer bet
     */
    public int getBet(HoldemPlayer currentPlayer) {
        int bet;
        if (currentPlayer.isMain) {
            bet = currentPlayer.mainPlayerBet; // If player is user, set bet equal to what is determined by javaFX main file
        }
        else {
            bet = currentPlayer.cpuBet(highBet); // Else calculate random in cpuBet() HoldemPlayer method
        }
        return bet; // Return bet to bettingTurn()
    }

    /**
     * Adds a card to the community cards CardList
     */
    public void addCommunityCard() {
        Card inCard = deck.getCard(0); // inCard = card at index 0
        communityCards.add(inCard); // Add inCard to community
        deck.remove(inCard); // Remove inCard from deck
    }

    /**
     * Initiates the flop. First 3 community cards
     */
    public void flop() {
        for (int i = 0 ; i < 3 ; i++) { // Deals 3 cards from the deck to the community cards
            addCommunityCard();
        }
    }

    /**
     * Initiates the turn. Fourth community card
     */
    public void turn() {
        addCommunityCard();
    }

    /**
     * Initiates the river. Fifth and final card
     */
    public void river() {
        addCommunityCard();
    }

    /**
     * Determins which players have the best hand
     * @return List of winners (only ever > 1 if there is a tie)
     */
    public ArrayList<HoldemPlayer> getWinner() {
        int highHandVal = -1; // Set val to lowest possible (0 is used for high card)
        for (Player p : activePlayers) { // Cycle through active players
            HoldemPlayer hp = (HoldemPlayer) p; // type cast p to HoldemPlayer type
            hp.assignHandValue(communityCards); // Assign hand value in HoldemPlayer method. Finds the type of hand a player has
            if (hp.getHandValue() > highHandVal) { // If current players hand is better than the previous
                highHandVal = hp.getHandValue(); // current players is now the highest
                winners.clear(); // clear the winners and add current in
                winners.add(hp);
            }
            else if (hp.getHandValue() == highHandVal) { // If hand values are equal...
                if (winners.get(0).highCard.value < hp.highCard.value) { // If current winner's high card is less than current player
                    winners.clear(); // ERASE HIM
                    winners.add(hp); // Add in current player
                }
                else if (winners.get(0).highCard.value == hp.highCard.value) { // If the high cards are equal...
                    if (winners.get(0).secondHighCard.value < hp.secondHighCard.value) { // Look at the second high card (of 2 card hand)
                        winners.clear();
                        winners.add(hp);
                    }
                    else if (winners.get(0).secondHighCard.value == hp.secondHighCard.value) { // If all is equal, the two tie
                        winners.add(hp);
                    }
                }
            }
        }
        return winners; // Return list of winners
    }

    /**
     * Sets state of current bet and initiates a betting round
     * @param state Passed in state to be set for the betting round
     */
    protected void handleBettingRound(HoldemState state) {
        gameState = state;
        bettingRound();
    }
    
    /**
     * Test if all players have folded
     * @return Boolean if folded or not
     */
    protected boolean allPlayersFolded() {
        // Logic to check if all players have folded
        if (activePlayers.size() > 1) {
            return false;
        }
        winners.add(activePlayers.get(0)); // Add the only player to winners
        return true;  // All players have folded
    }

    /**
     * toString for Holdem class
     */
    @Override
    public String toString() {
        return super.toString() + "Pot: " + pot + "\nCommunity Cards: " + communityCards + "\nDeck: " + super.deck;
    }
}
