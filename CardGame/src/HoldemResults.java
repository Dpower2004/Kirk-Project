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
        int pairCount = 0;
        boolean flush = false;
        boolean straight = false;
        boolean royal = false;
        boolean threeKind = false;
        boolean fourKind = false;
        boolean fullHouse = false;
        Map<String, Integer> suitCount = new HashMap<>(); // Maps and integer to a string (the suit)
        Map<String, Integer> rankCount = new HashMap<>();
        for (Card card : combinedList) { // Iterate through cards
            String suit = card.cardSuit; // suit = the current card's suit
            String rank = card.cardRank;
            /* If the given suit is already in the map, get its count. If it is not, set it to 0 by
               default. Regardless, we add 1 to the count and move on */
            suitCount.put(suit, suitCount.getOrDefault(suit, 0) + 1);
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : suitCount.entrySet()) {
            if (entry.getValue() > 4) {
                flush = true;
            }
        }
        System.out.println("");
        for (Map.Entry<String, Integer> entry : rankCount.entrySet()) {
            int count = entry.getValue();
            switch (count) {
                case 2:
                    pairCount++;
                    break;
                case 3:
                    threeKind = true;
                    break;
                case 4:
                    fourKind = true;
                    break;
            }
        }
        System.out.println("pairCount: " + pairCount);
        System.out.println("threeKind: " + threeKind);
        System.out.println("fourKind: " + fourKind);
    }
}
