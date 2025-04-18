import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representing the game texas holdem. It inherits the abstract game class
 * @author Luke Soda
 * @version 1.0
 */

public class Holdem extends CardGame {
    protected HoldemState gameState; // Game state
    protected int buyIn; // Minimum number of chips to play
    protected int highBet; // Holds current highest bet for a current betting round. Is also used as passed in
                           // big blind for first round of bettings
    protected CardList communityCards = new CardList(true); // 5 community cards that make up the river
    protected int pot; // Total pot that is taken at the end of each game
    protected ArrayList<HoldemPlayer> activePlayers = new ArrayList<>(); // List of players who haven't folded
    private Scanner advanceIn = new Scanner(System.in);
    private boolean betMade; // Keeps track of if a bet has been made to change raise / bet prompt

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
        for (int i = 0; i < playerList.length ; i++) { // Cycle through all players to determine who needs to put
                                                       // money in first
            HoldemPlayer currentPlayer = (HoldemPlayer) playerList[i]; // Uses type casting to allow HoldemPlayer method calls
            activePlayers.add(currentPlayer); // Add all players to active players arrayList
            if (i == 1) { // Player at index 1 will ALWAYS be little blind
                currentPlayer.setInChips(highBet / 2);
            } 
            else if (i == 2) { // Player at index 2 will ALWAYS be big blind
                currentPlayer.setInChips(highBet);
            }
        }
        super.deal(2); // Deal 2 cards to all players
        consoleOut();
    }

    /**
     * Initiates a single betting round for all players
     */
    public void bettingRound() {
        int pTurn = 3; // Keeps track of player turn, start at 2, always player to the left of the big blind
        for (int i = 0 ; i < playerList.length ; i++, pTurn++) { // Iterate a number of time = how many players
                                                                           // Also keep track of number of bets
            if (pTurn > playerList.length - 1) { // If turn goes above player count, cycle back to player 0
                pTurn = 0;
            }
            HoldemPlayer currentPlayer = (HoldemPlayer) playerList[pTurn]; // Uses type casting to allow HoldemPlayer method calls
            currentPlayer.chooseAction(highBet, betMade); // Prompts each player for their choise to check, raise, or fold
            switch (currentPlayer.currentAction) {
                case "C": // If check
                    currentPlayer.setInChips(highBet); // Match the current bet
                    break;
                case "B": // If bet
                    betMade = true;
                case "R": // If raise
                    currentPlayer.setInChips(highBet + currentPlayer.raise); // Add bet onto player's chips
                    highBet = currentPlayer.inChips; // Update high bet to be the highest player's chip value
                    int rTurn = pTurn + 1; // reset turn needs to start one more than current turn
                    // This for loop is to reset the currentAction of all OTHER players on a raise. They need to react to raise
                    for (int j = 0 ; j < playerList.length - 1 ; j++, rTurn++) { // Iterate number of players - 1. Don't erase current
                        if (rTurn > playerList.length - 1) { // If reset turn goes above player count, cycle back to player 0
                            rTurn = 0;
                        }
                        HoldemPlayer resetPlayer = (HoldemPlayer) playerList[rTurn]; // Use casting to select current rTurn player
                        resetPlayer.currentAction = ""; // Reset current rTurn player
                    }
                    i = 0; // RESET the number iterations, allows for all players to respond to the new bet
                    break;
                case "F": // If fold
                    currentPlayer.fold(); // isActive = false
                    activePlayers.remove(currentPlayer); // Remove player from the active list
                    break;
            }
            consoleOut();
        }
        // Reset for next round
        for (int i = 0 ; i < playerList.length ; i++) { // For all the players...
            HoldemPlayer hp = (HoldemPlayer) playerList[i];
            pot += hp.inChips; // Add their chips into the pot
            highBet = 0; // Reset highBet
            betMade = false; // Reset betMade;
            hp.inChips = 0; // Reset what they have up to 0
            hp.currentAction = ""; // Erase current action
        }
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
     * Game controller for holdem. Handles calling methods to advance the game forward
     * when the player presses enter
     */
    public void startGame() {
        gameState = HoldemState.SETUP;
        setup();
        gameState = HoldemState.FIRST_BET;
        bettingRound();
        gameState = HoldemState.FLOP;
        flop();
        gameState = HoldemState.SECOND_BET;
        bettingRound();
        gameState = HoldemState.TURN;
        turn();
        gameState = HoldemState.THIRD_BET;
        bettingRound();
        gameState = HoldemState.RIVER;
        river();
        gameState = HoldemState.FINAL_BET;
    }

    public void consoleOut() {
        String statusString = "Round: " + gameState + "\n";
        switch (gameState) {
            case HoldemState.SETUP:
            case HoldemState.FIRST_BET:
                for (Player p : playerList) {
                    HoldemPlayer hp = (HoldemPlayer) p;
                    statusString += hp.toString();
                }
                break;
            case HoldemState.FLOP:
            case HoldemState.SECOND_BET:
                for (Player p : playerList) {
                    HoldemPlayer hp = (HoldemPlayer) p;
                    statusString += hp.toString();
                }
                statusString += "\nFlop: " + communityCards;
        }
        System.out.println(statusString);
        consoleAdvance(advanceIn);
    }

    public void consoleAdvance(Scanner advanceIn) {
        System.out.println("Press [Enter] to continue");
        advanceIn.nextLine();
    }

    /**
     * toString for Holdem class
     */
    @Override
    public String toString() {
        return super.toString() + "Pot: " + pot + "\nCommunity Cards: " + communityCards + "\nDeck: " + super.deck;
    }
}
