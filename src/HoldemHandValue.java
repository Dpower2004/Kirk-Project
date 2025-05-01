
/**
 * Class evaluates the hand of a given player
 * @author Luke Soda
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class HoldemHandValue {
    protected HoldemPlayer player; // The player passed in
    protected CardList communityCards; // The 5 shared cards
    protected ArrayList<Card> combinedList; // Combined list of shared and player

    protected Map<String, Integer> suitCount; // Keeps the count of all 4 suits
    protected Map<Integer, Integer> rankCount; // Keeps the count of all 13 cards
    protected Map<String, Integer> rankMatchStats; // Holds number of pair, 3 kind, 4 kimd

    protected String fiveCardSuit; // If there is a flush, this stores the suit of it
    protected Hand handValue; // Enum representing the type of hand

    protected Card highCard; // High card in 5 card hand
    protected Card secondHighCard; // High card set if conditions are met

    /**
     * HoldemHandValue constructor
     * Sets up combined list and calls sort and value methods
     * @param player
     * @param communityCards
     */
    public HoldemHandValue(HoldemPlayer player, CardList communityCards) {
        this.player = player;
        combinedList = new ArrayList<>(player.cards.getCardList()); // Copy player cards
        combinedList.addAll(communityCards.getCardList()); // Add community onto copied cards
        sortAll(); // Sorts the hand
        valueHand(); // Values the hand
    }

    /**
     * sortAll methods sorts the combined list of card by card value
     */
    public void sortAll() {
        boolean sorted = false; // While continues till this is true
        while (!sorted) { // Continue until no swaps are needed
            boolean swapped = false; // does current itteration have a swap
            for (int i = 1; i < combinedList.size() ; i++) { // Iterate through valid elements
                int j = i - 1; // j = next index
                if (combinedList.get(j).value > combinedList.get(i).value) { // Swap if elements are out of order
                    swapped = true;
                    Card temp = combinedList.get(j); // Put j element in temp var
                    combinedList.set(j, combinedList.get(i)); // put i element in j element
                    combinedList.set(i, temp); // put temp var in i element. i and j elements are now swapped
                }
            }
            if (!swapped) { // If no swaps occurred, the array is sorted
                sorted = true;
            }
        }
    }

    /**
     * Calls methods that populates required fields for valuing the hand
     * @return
     */
    public Hand valueHand() {
        suitCount = getSuitCount(combinedList); // Maps an integer to a string (the suit)
        rankCount = getRankCount(combinedList); // Maps an integer to an integer (the rank)
        rankMatchStats = getRankMatchStats(); // Gets number of pairs, 3 kinds, 4 kinds into hashmap
        fiveCardSuit = getFiveCardSuit(); // If a flush exists, set its suit to fiveCardSuit
        return getHandValue(); // Returns the type of hand found in getHandValue()
    }

    /**
     * Uses a hashmap to find the fequency of each suit
     * @param cards Combined list of player cards and community cards
     * @return HashMap that holds frequencies
     */
    public Map<String, Integer> getSuitCount(ArrayList<Card> cards) {
        suitCount = new HashMap<>();
        for (Card card : cards) { // Iterate through cards
            String suit = card.cardSuit; // suit = the current card's suit
            /* If the given suit is already in the map, get its count. If it is not, set it to 0 by
               default. Regardless, we add 1 to the count and move on */
            suitCount.put(suit, suitCount.getOrDefault(suit, 0) + 1);
            if (suitCount.get(suit) >= 5) { // If the count of the current card's suit is at least 5, current card is new highCard
                highCard = card; // This is used for determining the highCard in a flush
            }
        }
        return suitCount;
    }

    /**
     * Uses a hashmap to find the fequency of each rank
     * @param cards Combined list of player cards and community cards
     * @return HashMap that holds frequencies
     */
    public Map<Integer, Integer> getRankCount(ArrayList<Card> cards) {
        rankCount = new HashMap<>();
        for (Card card : cards) { // Iterate through cards
            int rank = card.value; // rank = the current card's rank
            /* If the given rank is already in the map, get its count. If it is not, set it to 0 by
               default. Regardless, we add 1 to the count and move on */
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }
        return rankCount;
    }

    /**
     * Determines the actual value of the hand
     * Goes through tree of ifs, representing the hierarchy of hands
     * @return Hand enum representing the type of hand
     */
    public Hand getHandValue() {
        highCard = handHighCard(); // Decide what the highCard should be
        if (isRoyalFlush()) { // If the hand is a royal flush, set handValue and return
            handValue = Hand.ROYAL_FLUSH;
            return handValue;
        }

        if (isStraightFlush(combinedList)) {
            handValue = Hand.STRAIGHT_FLUSH;
            return handValue;
        }

        if (rankMatchStats.get("4k") == 1) { // If the player has 4 cards of the same rank
            handValue = Hand.FOUR_KIND; // Hand is four of a kind, return handValue
            return handValue;
        }

        if (rankMatchStats.get("3k") == 2 || (rankMatchStats.get("3k") == 1 && rankMatchStats.get("2k") >= 1)) {
            // If the player has 2 sets of 3 cards OR has one 3 card set and at least 1 2 card set
            handValue = Hand.FULL_HOUSE; // Its a full house, return value
            return handValue;
        }
        
        if (isFlush()) {
            handValue = Hand.FLUSH;
            return handValue;
        }
        
        if (isStraight(combinedList)) {
            handValue = Hand.STRAIGHT;
            return handValue;
        }
        
        if (rankMatchStats.get("3k") == 1) {
            handValue = Hand.THREE_KIND;
            return handValue;
        }
        
        if (rankMatchStats.get("2k") >= 2) {
            handValue = Hand.TWO_PAIR;
            return handValue;
        }
        
        if (rankMatchStats.get("2k") == 1) {
            handValue = Hand.PAIR;
            return handValue;
        }
        
        handValue = Hand.HIGH_CARD; // If none of the above are true hand is a high card hand
        return handValue;
    }
    
    /**
     * Determines the hand's high card
     * @return the high card
     */
    public Card handHighCard() {
        Card card0 = player.cards.getCard(0); // The first card in the players exclusive hand
        Card card1 = player.cards.getCard(1); // The second card in the players exclusive hand
        if (rankCount.get(card0.value) > rankCount.get(card1.value)) { // If there are more first card ranks than second...
            secondHighCard = card1; // Secondary high card is card1
            return card0; // Return card1 as the high card
            /* This is used for determining who has the better hand when two players have a pair / 2 pair etc. cards that
               have values which appear more than once take precident. The secondary highCard is used to break ties if two
               players have the same pair / two pair */
        }
        else if (rankCount.get(card0.value) < rankCount.get(card1.value)) { // If there are more second ranks than first...
            secondHighCard = card0; // Secondary card is card0
            return card1; // Return card1 as high card
        }
        else { // If the two cards appear with the same frequency...
            if (card0.value > card1.value) { // Check to see which is of greater value. Return greater value card
                secondHighCard = card1;
                return card0;
            }
            else {
                secondHighCard = card0;
                return card1;
            }
        }
    }

    /**
     * Sets the number of pairs, three of a kinds, and four of a kinds for the 7 card hand
     * @return HashMap storing the number of pairs, three of a kinds, and four of a kinds
     */
    public Map<String, Integer> getRankMatchStats() {
        // Counts for each number match
        int fourCount = 0;
        int threeCount = 0;
        int twoCount = 0;
        rankMatchStats = new HashMap<>(); // Declare new hashmap
        for (Integer key : rankCount.keySet()) { // Cycles through all of the different rank frequencies stored in rankCount
            int count = rankCount.get(key); // Rank count for current rank in rankCount map
            switch (count) {
                case 4 -> fourCount++; // There are 4 of a rank add 1 to fourCount
                case 3 -> threeCount++; // There are 3 of a rank add 1 to threeCount
                case 2 -> twoCount++; // There are 2 of a rank add 1 to twoCount
            }
        }
        // Store all of these values in the rankMatchStats HashMap
        rankMatchStats.put("4k", fourCount);
        rankMatchStats.put("3k", threeCount);
        rankMatchStats.put("2k", twoCount);
        return rankMatchStats;
    }

    /**
     * Determines if a set contains a royal flush
     * @return isRoyalFlush true / false
     */
    public boolean isRoyalFlush() {
        ArrayList<Card> flushFilter = new ArrayList<>(combinedList); // Duplicate combined list of cards
        for (int i = flushFilter.size() - 1; i >= 0; i--) { // Iterate through duplicated list
            Card card = flushFilter.get(i); // card = current card
            if (card.value < 10) { // If the value of the current card is less than 10...
                flushFilter.remove(card); // Remove it (A royal flush only contains cards above 9)
            }
        }
        return isStraightFlush(flushFilter); // Check if new list is a straight flush
    }

    /**
     * Determines if a set contains a straight flush
     * @param cards passed in list of cards
     * @return isStraightFlush true / flase
     */
    public boolean isStraightFlush(ArrayList<Card> cards) {
        ArrayList<Card> isolatedFlush = new ArrayList<>(cards); // Duplicate card list
        isolatedFlush = isolateFlush(isolatedFlush); // Isolate cards in the deck that match a flush suit
        return isStraight(isolatedFlush); // Check if isolatedFlush list is a straight
    }
    
    /**
     * Determines if a set contains a straight
     * @param cards passed in list of cards
     * @return isStraight true / false
     */
    public boolean isStraight(ArrayList<Card> cards) {
        int consecGapCount = 0; // Number of differences between cards that = 1 Ex. Q -> K
        /* Loop adds a special card with value of 1 if an ace is present. This is required for a low ace straight. Default ace
           has a value of 14, meaning that a low ace would be impossible without a card with a value of 1 */
        for (int i = 0; i < cards.size(); i++) { // Iterate through passed in cards
            Card currentCard = cards.get(i); // current card
            if (currentCard.cardRank.equals("A")) { // If current card is an ace...
                String suit = currentCard.cardSuit; // Find suit of current card
                combinedList.add(0, new Card("V", suit)); // Add new V card with suit of ace. V corresponds to value = 1
                i++; // Increase current index to accomidate extra card at index 0
            }
        }
        // Get the number of consecutive gaps in passed in card list
        for (int i = 0; i < cards.size() - 1; i++) { // Cycle through cards
            int j = i + 1; // Index j = next index
            int valueDif = cards.get(j).value - cards.get(i).value; // Difference between current and next
            // If the difference between next and current is anything other than 1, the cards are not consecutive
            if (valueDif == 1) { // If the difference between current and next is one...
                consecGapCount++; // Increment the number of consecutive gaps found in the list
                // Note: 5 consecutive cards would = consecGapCount of 4 
                if (consecGapCount >= 4) { // If the number of consecutive gaps is at least 4...
                    highCard = cards.get(j); // Set the highCard to the top of the straight
                }
            }
            // Resewt the gap count if there are less than 4 consec gaps and diff is not 1 and 0
            else if (consecGapCount < 4 && (valueDif != 1 && valueDif != 0)) {
                consecGapCount = 0;
            }
            /* Else if is used to prevent fraudulent straight flush. If you have [2C, 3C, 4S, 5C, 6C, 7C, 10D], not having
               the else if would return true. This is because there are technically 4 consecutive gaps across all 7 cards,
               but not all of the cards are consecutive. */
        }
        return consecGapCount >= 4; // Return true if there are at least 4 consec gaps
    }

    /**
     * Removes all cards that do not belong to the flush suit
     * @param cards passed in list of cards
     * @return updated list of cards (isolated flush)
     */
    public ArrayList<Card> isolateFlush(ArrayList<Card> cards) {
        for (int i = cards.size() - 1; i >= 0; i--) { // Cycle through cards from end of list
            Card card = cards.get(i); // Card = current card
            if (!card.cardSuit.equals(fiveCardSuit)) { // If the suit != the flush suit remove it
                cards.remove(card);
            }
        }
        return cards; // return isolated flush
    }

    /**
     * Finds the 5 cards suit if a flush is present
     * @return
     */
    public String getFiveCardSuit() {
        fiveCardSuit = "N"; // Suit is N by default (no flush)
        for (Map.Entry<String, Integer> entry : suitCount.entrySet()) { // Go through all of the suit values from the map
            if (entry.getValue() > 4) { // If any are greater than 4 you have a flush
                fiveCardSuit = entry.getKey(); // Flush suit = current suit in map
            }
        }
        return fiveCardSuit;
    }

    /**
     * Determines if a hand has a flush
     * @return Returns true if the fiveCardSuit in getFiveCardSuit() != N
     */
    public boolean isFlush() {
        return !fiveCardSuit.equals("N");
    }

    /**
     * toString for HoldemHandValue
     */
    @Override
    public String toString() {
        return "Hand: " + player.cards + "Hand Value: " + handValue + "\nHigh Card: " + highCard + "\n";
    }
}