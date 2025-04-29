;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representing the game texas holdem. It inherits the abstract game class
 * @author Luke Soda
 * @version 1.0
 */

public class Holdem extends CardGame {
    protected HoldemState gameState; // Enum representing the state of the game

    protected int highBet; // Current highest bet for a round. Contains big blind at start
    protected ChipStack pot = new ChipStack(0); // Total pot that is taken at the end of each game

    protected CardList communityCards = new CardList(true); // 5 community cards that make up the river. Start empty
    protected ArrayList<HoldemPlayer> activePlayers = new ArrayList<>(); // List of players who haven't folded
    protected ArrayList<HoldemPlayer> winners = new ArrayList<>(); // List of players who split the pot at the end of the game

    private final Scanner advanceIn = new Scanner(System.in); // Used for advancing the game by pressing enter
    private boolean betMade; // Keeps track of if a bet has been made to change raise / bet prompt
    private boolean allIn; // Keeps track of if a player has gone all in. I didn't have time to implement side pots. If someone
                           // goes all in, no more betting happens and the round advances to the end of the game

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
        if (!allIn) {
            int pTurn = 3; // Keeps track of player turn. The player to the left of the big blind will always go first (3)
            // Iterate through through the loop for a number of turns. Also update pTurn int for each iteration
            for (int remainingTurns = activePlayers.size() ; remainingTurns > 0 ; remainingTurns--, pTurn++) {
                // If turn goes above player count, cycle back to player 0
                if (pTurn > activePlayers.size() - 1) {
                    pTurn = 0;
                }
                /* pTurn and remainingTurns must be held seperate. This allows for all players to respond to a bet or raise if
                they have already gone once. */
                HoldemPlayer currentPlayer = activePlayers.get(pTurn); // Type casting for HoldemPlayer method calls
                String choice = currentPlayer.chooseAction(highBet, betMade); // Prompt player for choice in betting round
                switch (choice) {
                    case "C" -> {
                        // If check / call
                        if (currentPlayer.chipBank.chipAmount < highBet) {
                            currentPlayer.setHandChips(currentPlayer.handChips.chipAmount + currentPlayer.chipBank.chipAmount);
                            currentPlayer.currentAction = "A";
                            allIn = true;
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
                        resetPlayerResponses(currentPlayer, pTurn); // Resets the responses of all other players
                        betMade = true; // Tell the game that a bet was made 
                        if (currentPlayer.chipBank.chipAmount <= 0) {
                            allIn = true;
                            currentPlayer.currentAction = "A";
                        }
                        remainingTurns = activePlayers.size(); // RESET the number of turns remaining, other players must respond
                    }
                    case "R" -> {
                        // If raise
                        int raise = getBet(currentPlayer); // Get the raise to be put up
                        currentPlayer.setHandChips(highBet + raise); // Set chips in to highBet + the raise
                        highBet += raise; // Add the raise onto the current highBet
                        resetPlayerResponses(currentPlayer, pTurn); // Resets the responses of other players
                        if (currentPlayer.chipBank.chipAmount <= 0) {
                            allIn = true;
                            currentPlayer.currentAction = "A";
                        }
                        remainingTurns = activePlayers.size(); // // RESET the number of turns remaining, other players must respond
                    }
                    case "F" -> {
                        // If fold
                        currentPlayer.fold(); // isActive = false
                        activePlayers.remove(currentPlayer);
                        pTurn--;
                    }
                }
                consoleOut(); // Update the console to show player action
            }
            // Reset players for next round
            for (Player p : playerList) { // For all the players...
                HoldemPlayer hp = (HoldemPlayer) p;
                pot.addChips(hp.handChips.chipAmount); // Add their chips into the pot
                hp.handChips.setChips(0); // Reset what they have up to 0
                hp.maxRoundChips = hp.chipBank.chipAmount;
                hp.currentAction = ""; // Erase current action
            }
            // Reset bets for next round
            highBet = 0; // Reset highBet
            betMade = false; // Reset betMade;
        }
        else {
            System.out.println("Round skipped");
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

    public int getBet(HoldemPlayer currentPlayer) {
        int bet;
        if (currentPlayer.isMain) {
            bet = currentPlayer.promptAmount(highBet); // Get the bet to be put up
        }
        else {
            bet = currentPlayer.cpuBet(highBet);
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

        // Handle all betting rounds using a single method
        if (handleBettingRound(HoldemState.FIRST_BET)) return;
        gameState = HoldemState.FLOP;
        flop();

        if (handleBettingRound(HoldemState.SECOND_BET)) return;
        gameState = HoldemState.TURN;
        turn();

        if (handleBettingRound(HoldemState.THIRD_BET)) return;
        gameState = HoldemState.RIVER;
        river();

        if (handleBettingRound(HoldemState.FINAL_BET)) return;

        int highHandVal = -1;
        for (Player p : activePlayers) {
            HoldemPlayer hp = (HoldemPlayer) p;
            hp.assignHandValue(communityCards);
            System.out.println("Player " + hp.playerID + " got a " + hp.handValue + "\nHand: " + hp.cards + "\nHigh Card: " + hp.highCard + "\n");
            if (hp.getHandValue() > highHandVal) {
                highHandVal = hp.getHandValue();
                winners.clear();
                winners.add(hp);
            }
            else if (hp.getHandValue() == highHandVal) {
                if (winners.get(0).highCard.value < hp.highCard.value) {
                    winners.clear();
                    winners.add(hp);
                }
                else if (winners.get(0).highCard.value == hp.highCard.value) {
                    if (winners.get(0).secondHighCard.value < hp.secondHighCard.value) {
                        winners.clear();
                        winners.add(hp);
                    }
                    else if (winners.get(0).secondHighCard.value == hp.secondHighCard.value) {
                        winners.add(hp);
                    }
                }
            }
        }
        showWinner();
    }

    public void showWinner() {
        int take = pot.chipAmount / winners.size();
        for (HoldemPlayer winner : winners) {
            System.out.println("Player " + winner.playerID + " wins! +" + take + " chips.");
            winner.chipBank.addChips(take);
            System.out.println(winner.chipBank.chipAmount);
        }
    }

    private boolean handleBettingRound(HoldemState state) {
        gameState = state;
        bettingRound();
        if (allPlayersFolded()) {
            System.out.println("All players folded. Ending the game.");
            showWinner();
            return true;  // Return true to break out of the game
        }
        return false;  // Continue if players didn't fold
    }
    
    private boolean allPlayersFolded() {
        // Logic to check if all players have folded
        if (activePlayers.size() > 1) {
            return false;
        }
        winners.add(activePlayers.get(0));
        return true;  // All players have folded
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

    
    public void test() {
        for (int i = 0; i < playerList.length ; i++) { // Cycle through all players to set up blinds
            playerList[i].playerID = i;
            HoldemPlayer currentPlayer = (HoldemPlayer) playerList[i]; // Type casting for HoldemPlayer method calls
            activePlayers.add(currentPlayer); // Add all players to active players arrayList
        }
        activePlayers.get(0).cards.add(new Card("9", "H"));
        activePlayers.get(0).cards.add(new Card("A", "C"));
        activePlayers.get(1).cards.add(new Card("Q", "C"));
        activePlayers.get(1).cards.add(new Card("A", "H"));
        communityCards.add(new Card("5", "D"));
        communityCards.add(new Card("8", "H"));
        communityCards.add(new Card("5", "C"));
        communityCards.add(new Card("3", "S"));
        communityCards.add(new Card("3", "H"));
        System.out.println(communityCards);
        int highHandVal = -1;
        for (Player p : activePlayers) {
            HoldemPlayer hp = (HoldemPlayer) p;
            hp.assignHandValue(communityCards);
            System.out.println("Player " + hp.playerID + " got a " + hp.handValue + "\nHand: " + hp.cards + "\nHigh Card: " + hp.highCard + "\n");
            if (hp.getHandValue() > highHandVal) {
                highHandVal = hp.getHandValue();
                winners.clear();
                winners.add(hp);
            }
            else if (hp.getHandValue() == highHandVal) {
                if (winners.get(0).highCard.value < hp.highCard.value) {
                    winners.clear();
                    winners.add(hp);
                }
                else if (winners.get(0).highCard.value == hp.highCard.value) {
                    if (winners.get(0).secondHighCard.value < hp.secondHighCard.value) {
                        winners.clear();
                        winners.add(hp);
                    }
                    else if (winners.get(0).secondHighCard.value == hp.secondHighCard.value) {
                        winners.add(hp);
                    }
                }
            }
        }
        showWinner();
    }
}
