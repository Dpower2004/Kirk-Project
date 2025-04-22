import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the game texas holdem. It inherits the abstract game class
 * @author Luke Soda
 * @version 1.0
 */

public class Holdem extends CardGame {
    protected HoldemState gameState; // Game state

    protected int highBet; // Current highest bet for a round. Contains big blind at start
    protected int pot; // Total pot that is taken at the end of each game

    protected CardList communityCards = new CardList(true); // 5 community cards that make up the river
    protected ArrayList<HoldemPlayer> activePlayers = new ArrayList<>(); // List of players who haven't folded

    private final Scanner advanceIn = new Scanner(System.in);
    private boolean betMade; // Keeps track of if a bet has been made to change raise / bet prompt

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
            HoldemPlayer currentPlayer = (HoldemPlayer) playerList[i]; // Type casting for HoldemPlayer method calls
            activePlayers.add(currentPlayer); // Add all players to active players arrayList
            if (i == 1) { // Player at index 1 will ALWAYS be little blind
                currentPlayer.setHandChips(highBet / 2);
            } 
            else if (i == 2) { // Player at index 2 will ALWAYS be big blind
                currentPlayer.setHandChips(highBet);
            }
        }
        super.deal(2); // Deal 2 cards to all players
        consoleOut(); // Update the console on whats going on
    }

    /**
     * Initiates a single betting round for all players
     */
    public void bettingRound() {
        int pTurn = 3; // Keeps track of player turn. The player to the left of the big blind will always go first (3)
        // Iterate through through the loop for a number of turns. Also update pTurn int for each iteration
        for (int remainingTurns = playerList.length ; remainingTurns > 0 ; remainingTurns--, pTurn++) {
            // If turn goes above player count, cycle back to player 0
            if (pTurn > playerList.length - 1) {
                pTurn = 0;
            }
            /* pTurn and remainingTurns must be held seperate. This allows for all players to respond to a bet or raise if
               they have already gone once. */
            HoldemPlayer currentPlayer = (HoldemPlayer) playerList[pTurn]; // Type casting for HoldemPlayer method calls
            String choice = currentPlayer.chooseAction(highBet, betMade); // Prompt player for choice in betting round
            switch (choice) {
                case "C" -> // If check / call
                    currentPlayer.handChips.setChips(highBet); // Match the current high bet
                case "B" -> {
                    // If bet
                    int bet = getBet(currentPlayer);
                    currentPlayer.setHandChips(bet); // Set chips in to players bet input
                    highBet = bet; // Update highBet to be the current bet
                    resetPlayerResponses(currentPlayer, pTurn); // Resets the responses of all other players
                    betMade = true; // Tell the game that a bet was made 
                    remainingTurns = 4; // RESET the number of turns remaining, other players must respond
                }
                case "R" -> {
                    // If raise
                    int raise = getBet(currentPlayer); // Get the raise to be put up
                    currentPlayer.setHandChips(highBet + raise); // Set chips in to highBet + the raise
                    highBet += raise; // Add the raise onto the current highBet
                    resetPlayerResponses(currentPlayer, pTurn); // Resets the responses of other players
                    remainingTurns = 4; // // RESET the number of turns remaining, other players must respond
                }
                case "F" -> {
                    // If fold
                    currentPlayer.fold(); // isActive = false
                    activePlayers.remove(currentPlayer); // Remove player from the active list
                }
            }
            consoleOut(); // Update the console to show player action
        }
        // Reset players for next round
        for (Player p : playerList) { // For all the players...
            HoldemPlayer hp = (HoldemPlayer) p; // Type casting for HoldemPlayer method calls
            pot += hp.handChips.chipAmount; // Add their chips into the pot
            hp.handChips.setChips(0); // Reset what they have up to 0
            hp.maxRoundChips = hp.bank.chipAmount;
            hp.currentAction = ""; // Erase current action
        }
        // Reset bets for next round
        highBet = 0; // Reset highBet
        betMade = false; // Reset betMade;
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
        for (int j = 0 ; j < playerList.length - 1 ; j++, rTurn++) { // Iterate number of players - 1. Don't erase current
            if (rTurn > playerList.length - 1) { // If reset turn goes above player count, cycle back to player 0
                rTurn = 0;
            }
            HoldemPlayer resetPlayer = (HoldemPlayer) playerList[rTurn]; // Use casting to select current rTurn player
            resetPlayer.currentAction = ""; // Reset current rTurn player
        }
    }

    public int getBet(HoldemPlayer currentPlayer) {
        int bet;
        if (currentPlayer.isMain) {
            bet = currentPlayer.promptAmount(); // Get the bet to be put up
        }
        else {
            bet = currentPlayer.cpuBet();
        }
        return bet;
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
        consoleOut();
    }

    /**
     * Initiates the turn. Fourth community card
     */
    public void turn() {
        addCommunityCard();
        consoleOut();
    }

    /**
     * Initiates the river. Fifth and final card
     */
    public void river() {
        addCommunityCard();
        consoleOut();
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
        bettingRound();
        Map<Integer, Hand> playerHands = new HashMap<>();
        for (Player p : playerList) {
            HoldemPlayer hp = (HoldemPlayer) p;
            HoldemHandValue playerHandValue = new HoldemHandValue(hp, communityCards);
            playerHands.put(playerHandValue.player.playerID, playerHandValue.hand);
        }
        System.out.println(playerHands);
    }

    public void consoleOut() {
        String statusString = "Round: " + gameState + "\n";
        statusString += playerStrings();
        statusString += "\nPot: " + pot + "\nCommunity Cards: " + communityCards;
        System.out.println(statusString);
        consoleAdvance(advanceIn);
    }

    public String playerStrings() {
        String statusString = "";
        for (Player p : playerList) {
            HoldemPlayer hp = (HoldemPlayer) p;
            statusString += hp.toString();
        }
        return statusString;
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
