import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HoldemHandValue {
    protected HoldemPlayer player;
    protected CardList communityCards;
    protected ArrayList<Card> combinedList;
    protected Map<String, Integer> suitCount;
    protected Map<Integer, Integer> rankCount;
    protected Map<String, Integer> rankMatchStats;
    protected String fiveCardSuit;
    protected Hand hand;
    protected Card highCard;

    public HoldemHandValue(HoldemPlayer player, CardList communityCards) {
        this.player = player;
        combinedList = new ArrayList<>(player.cards.getCardList());
        combinedList.addAll(communityCards.getCardList());
        highCard();
        sortAll();
        valueHand();
    }

    public void sortAll() {
        boolean sorted = false;
        while (!sorted) { // Continue until no swaps are needed
            boolean swapped = false;
            for (int i = 1; i < combinedList.size() ; i++) { // Iterate through valid elements
                int j = i - 1;
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

    public void highCard() {
        highCard = combinedList.get(0);
        for (Card card : combinedList) {
            if (card.value > highCard.value) {
                highCard = card;
            }
        }
    }

    public Hand valueHand() {
        suitCount = getSuitCount(combinedList); // Maps an integer to a string (the suit)
        rankCount = getRankCount(combinedList); // Maps an integer to an integer (the rank)
        rankMatchStats = getRankMatchStats();
        fiveCardSuit = getFiveCardSuit();
        return getHandValue();
    }

    public Hand getHandValue() {
        if (isRoyalFlush()) {
            hand = Hand.ROYAL_FLUSH;
            return hand;
        }

        if (isStraightFlush(combinedList)) {
            hand = Hand.STRAIGHT_FLUSH;
            return hand;
        }

        if (rankMatchStats.get("4k") == 1) {
            hand = Hand.FOUR_KIND;
            return hand;
        }

        if (rankMatchStats.get("3k") >= 1 && rankMatchStats.get("2k") >= 1) {
            hand = Hand.FULL_HOUSE;
            return hand;
        }
        
        if (isFlush()) {
            hand = Hand.FLUSH;
            return hand;
        }
        
        if (isStraight(combinedList)) {
            hand = Hand.STRAIGHT;
            return hand;
        }
        
        if (rankMatchStats.get("3k") == 1) {
            hand = Hand.THREE_KIND;
            return hand;
        }
        
        if (rankMatchStats.get("2k") == 2) {
            hand = Hand.TWO_PAIR;
            return hand;
        }
        
        if (rankMatchStats.get("2k") == 1) {
            hand = Hand.PAIR;
            return hand;
        }
        
        hand = Hand.HIGH_CARD;
        return hand;
    }
    

    public Map<String, Integer> getRankMatchStats() {
        int fourCount = 0;
        int threeCount = 0;
        int twoCount = 0;
        Map<String, Integer> rankMatchStats = new HashMap<>();
        for (Integer key : rankCount.keySet()) {
            Integer count = rankCount.get(key);
            if (count == 4) {
                fourCount++;
            }
            else if (count == 3) {
                threeCount++;
            }
            else if (count == 2) {
                twoCount++;
            }
        }
        rankMatchStats.put("4k", fourCount);
        rankMatchStats.put("3k", threeCount);
        rankMatchStats.put("2k", twoCount);
        return rankMatchStats;
    }

    public Map<String, Integer> getSuitCount(ArrayList<Card> cards) {
        Map<String, Integer> suitCount = new HashMap<>();
        for (Card card : cards) { // Iterate through cards
            String suit = card.cardSuit; // suit = the current card's suit
            /* If the given suit is already in the map, get its count. If it is not, set it to 0 by
               default. Regardless, we add 1 to the count and move on */
            suitCount.put(suit, suitCount.getOrDefault(suit, 0) + 1);
        }
        return suitCount;
    }

    public Map<Integer, Integer> getRankCount(ArrayList<Card> cards) {
        Map<Integer, Integer> rankCount = new HashMap<>();
        for (Card card : cards) { // Iterate through cards
            int rank = card.value; // rank = the current card's suit
            /* If the given rank is already in the map, get its count. If it is not, set it to 0 by
               default. Regardless, we add 1 to the count and move on */
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }
        return rankCount;
    }

    public boolean isRoyalFlush() {
        ArrayList<Card> flushFilter = new ArrayList<>(combinedList);
        // Get the number of consecutive gaps to be compared to 4
        for (int i = flushFilter.size() - 1; i >= 0; i--) {
            Card card = flushFilter.get(i);
            if (card.value < 10) {
                flushFilter.remove(card);
            }
        }
        return isStraightFlush(flushFilter);
    }

    public boolean isStraightFlush(ArrayList<Card> cards) {
        ArrayList<Card> isolatedFlush = new ArrayList<>(cards);
        isolatedFlush = isolateFlush(isolatedFlush);
        System.out.println(cards);
        return isStraight(isolatedFlush);
    }
    
    public boolean isStraight(ArrayList<Card> cards) {
        int consecGapCount = 0;
        // Get the number of consecutive gaps to be compared to 4
        for (int i = 0; i < cards.size() - 1; i++) { // Cycle through cards
            int j = i + 1; // Index j = next index (current + 1)
            // If the difference between next and current is anything other than 1, the cards are not consecutive
            int valueDif = cards.get(j).value - cards.get(i).value; // Diffence in next value and current
            if (valueDif == 1) {  // If the difference is one...
                consecGapCount++; // Increment the number of consecutive gaps found in the list
                                    // meaning that 5 consecutive cards would = consecCount of 4 
            }
            else if (consecGapCount < 4 && (valueDif != 1 && valueDif != 0)) {
                consecGapCount = 0;
            }
            /* Else if is used to prevent fraudulent straight flush. If you have [2C, 3C, 4S, 5C, 6C, 7C, 10D], not having
                the else if would return true. This is because there are technically 4 consecutive gaps across all 7 cards,
                but not all of the cards are consecutive. */
        }
        return consecGapCount >= 4; // Return true if there are at least 4 consec gaps
    }


    public ArrayList<Card> isolateFlush(ArrayList<Card> cards) {
        for (int i = cards.size() - 1; i >= 0; i--) {
            Card card = cards.get(i);
            if (!card.cardSuit.equals(fiveCardSuit)) {
                cards.remove(card);
            }
        }
        return cards;
    }

    public String getFiveCardSuit() {
        String fiveCardSuit = "N";
        for (Map.Entry<String, Integer> entry : suitCount.entrySet()) { // Go through all of the suit values from the map
            if (entry.getValue() > 4) { // If any are greater than 4 you have a flush
                fiveCardSuit = entry.getKey();
            }
        }
        return fiveCardSuit;
    }

    public boolean isFlush() {
        if (fiveCardSuit.equals("N")) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Hand: " + hand + "\nHigh Card: " + highCard;
    }
}