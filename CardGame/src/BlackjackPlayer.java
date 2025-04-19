/**
 * PUSH
 */

import java.util.Scanner;

public class BlackjackPlayer extends Player {
    private Scanner scanner;

    public BlackjackPlayer(int chips, boolean isMain) {
        super(chips, isMain);
        if (isMain) {
            scanner = new Scanner(System.in);
        }
    }

    @Override
    public void chooseAction() {
        // Not needed for Blackjack, we’ll use isHitting()
    }

    public boolean isHitting() {
        if (!isMain) {
            return getScore() < 17;
        }
        System.out.print("Your hand: " + cards + " (Score: " + getScore() + "). Hit or Stand? (H/S): ");
        String input = scanner.nextLine().toUpperCase();
        return input.equals("H");
    }

    public int getScore() {
        int score = 0;
        int aceCount = 0;

        for (Card card : cards.getCardList()) {
            String rank = card.getRank();
            if (rank.equals("A")) {
                score += 11;
                aceCount++;
            } else if ("KQJ".contains(rank) || rank.equals("10")) {
                score += 10;
            } else {
                score += Integer.parseInt(rank);
            }
        }

        while (score > 21 && aceCount > 0) {
            score -= 10; // Convert an Ace from 11 to 1
            aceCount--;
        }

        return score;
    }
}
