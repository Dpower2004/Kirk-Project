import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HoldemResults {
    protected Player[] playerList;
    protected CardList communityCards;

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
            System.out.println(combinedList);
            getBestHand(combinedList);
        }
    }

    public void getBestHand(ArrayList<Card> combinedList) {
        Map<String, Integer> suitCount = getSuitCount(combinedList); // Maps an integer to a string (the suit)
        Map<Integer, Integer> rankCount = getRankCount(combinedList); // Maps an integer to an integer (the rank)
        
        /*for (String key : suitCount.keySet()) {
            Integer value = suitCount.get(key);
            System.out.println(key + ": " + value);
        }
        for (Integer key : rankCount.keySet()) {
            Integer value = rankCount.get(key);
            System.out.println(key + ": " + value);*/
        }
    }

    public Map<String, Integer> getSuitCount(ArrayList<Card> cardList) {
        Map<String, Integer> suitCount = new HashMap<>();
        for (Card card : cardList) { // Iterate through cards
            String suit = card.cardSuit; // suit = the current card's suit
            /* If the given suit is already in the map, get its count. If it is not, set it to 0 by
               default. Regardless, we add 1 to the count and move on */
            suitCount.put(suit, suitCount.getOrDefault(suit, 0) + 1);
        }
        return suitCount;
    }

    public Map<Integer, Integer> getRankCount(ArrayList<Card> cardList) {
        Map<Integer, Integer> rankCount = new HashMap<>();
        for (Card card : cardList) { // Iterate through cards
            int rank = card.value; // rank = the current card's suit
            /* If the given rank is already in the map, get its count. If it is not, set it to 0 by
               default. Regardless, we add 1 to the count and move on */
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }
        return rankCount;
    }

    public boolean isFlush(Map<String, Integer> suitCount) {
        boolean isFlush = false;
        for (Map.Entry<String, Integer> entry : suitCount.entrySet()) { // Go through all of the flush values from the map
            if (entry.getValue() > 4) { // If any are greater than 4 you have a flush
                return true;
            }
        }
        return isFlush;
    }

    public boolean isStraight(ArrayList<Card> combinedList) {
        int consecGapCount = 0;
        // Get the number of consecutive gaps to be compared to 4
        for (int i = 0; i < combinedList.size() - 1; i++) { // Cycle through cards
            int j = i + 1; // Index j = next index (current + 1)
            // If the difference between next and current is anything other than 1, the cards are not consecutive
            int valueDif = combinedList.get(j).value - combinedList.get(i).value;
            if (valueDif == 1) {
                consecGapCount++; // Increment the number of consecutive gaps found in the list
                                  // meaning that 5 consecutive cards would = consecCount of 4 
            }
        }
        System.out.println("consec: " + consecGapCount);
        return consecGapCount >= 4; // Return true if there are at least 4 consec gaps
    }
}