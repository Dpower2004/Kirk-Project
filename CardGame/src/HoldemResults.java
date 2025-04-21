import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HoldemResults {
    protected Player[] playerList;
    protected CardList communityCards;
    protected ArrayList<Card> combinedList;
    protected Map<String, Integer> suitCount;
    protected Map<Integer, Integer> rankCount;
    protected String fiveCardSuit;

    public HoldemResults(Player[] playerList, CardList communityCards) {
        this.playerList = playerList;
        this.communityCards = communityCards;
    }

    public void sortAll() {
        for (Player p : playerList) {
            ArrayList<Card> combinedList = new ArrayList<>(p.cards.getCardList());
            combinedList.addAll(communityCards.getCardList());

            boolean swapped;
            boolean sorted = false;
            while (!sorted) { // Continue until no swaps are needed
                swapped = false;
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
            getBestHand(combinedList);
        }
    }

    public void getBestHand(ArrayList<Card> combinedList) {
        suitCount = getSuitCount(combinedList); // Maps an integer to a string (the suit)
        rankCount = getRankCount(combinedList); // Maps an integer to an integer (the rank)
        fiveCardSuit = getFiveCardSuit();
        System.out.println(combinedList);
        System.out.println("flush: " + isFlush());
        System.out.println("royal: " + isRoyalFlush(combinedList));
        
        /*for (String key : suitCount.keySet()) {
            Integer value = suitCount.get(key);
            System.out.println(key + ": " + value);
        }
        for (Integer key : rankCount.keySet()) {
            Integer value = rankCount.get(key);
            System.out.println(key + ": " + value);*/
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

    public boolean isRoyalFlush(ArrayList<Card> combinedList) {
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
        cards = isolateFlush(cards);
        return isStraight(cards);
    }
    
    public boolean isStraight(ArrayList<Card> combinedList) {
        int consecGapCount = 0;
        // Get the number of consecutive gaps to be compared to 4
        for (int i = 0; i < combinedList.size() - 1; i++) { // Cycle through cards
            int j = i + 1; // Index j = next index (current + 1)
            // If the difference between next and current is anything other than 1, the cards are not consecutive
            int valueDif = combinedList.get(j).value - combinedList.get(i).value; // Diffence in next value and current
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
}