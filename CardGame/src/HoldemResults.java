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
        Map<String, Integer> suitCount = new HashMap<>(); // Maps and integer to a string (the suit)
        Map<Integer, Integer> rankCount = new HashMap<>();
        for (Card card : combinedList) { // Iterate through cards
            String suit = card.cardSuit; // suit = the current card's suit
            int value = card.value;
            /* If the given suit is already in the map, get its count. If it is not, set it to 0 by
               default. Regardless, we add 1 to the count and move on */
            suitCount.put(suit, suitCount.getOrDefault(suit, 0) + 1);
            rankCount.put(value, rankCount.getOrDefault(value, 0) + 1);
        }

        boolean straightFlush = false;
        // Look for royal flush
        
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
        ArrayList<Card> straightList = new ArrayList<Card>(combinedList);
        int consecCount = 0;
        // Remove all repeated values from the flushList
        for (int i = 0; i < straightList.size(); i++) {
            int current = straightList.get(i).value;
            for (int j = i + 1; j < straightList.size(); j++) {
                if (current == straightList.get(j).value) { // If current index = j index, remove j index
                    straightList.remove(j);
                    j--; // Decrement j since list shifts left after removal
                }
            }
        }
        System.out.println("remCards: " + straightList);
        // If less than 5 cards are left we know that a straight is not around
        if (straightList.size() < 5) {
            return false;
        }
        if (straightList.contains())
        else { // If 5 cards present...
            for (int i = 0; i < straightList.size() - 1; i++) { // Cycle through cards
                int j = i + 1; // Index j = next index (current + 1)
                // If the difference between next and current is anything other than 1, the cards are not consecutive
                if (straightList.get(j).value - straightList.get(i).value == 1) {
                    consecCount++; // Increments on in betweens in list 
                                   // meaning that 5 consecutive cards would = consecCount of 4 
                }
            }
            if (consecCount >= 4) { // Return true if there are at least 4 consec gaps
                return true;
            }
            else {
                return false;
            }
        }
    }
}