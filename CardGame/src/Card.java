/**
 * Represents an individual card to be passed into the CardList class.
 * @author Luke Soda
 * @version 1.0
 */
public class Card {
    protected String cardRank; // The rank of the card
    protected String cardSuit; // The suit of the card

    /**
     * Constructor for Card object
     * Pass in rank and suit. Ex. "2", "S"
     * Creates new card object based on parameters
     * @param cardRank Rank of new card
     * @param cardSuit Suit of new card
     */
    public Card(String cardRank, String cardSuit) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
    }

    /**
     * Equals method for Card class
     * Check to see if two cards have the same rank and suit. Print bool accordingly
     * @param foreignCard Card to be compared to calling object
     * @return boolean indicating if the two cards are equal
     */
    public boolean equals (Card foreignCard) {
        // If both suit and rank are equal, return true. Else, return false
        if (this.cardRank == foreignCard.getRank() && this.cardSuit == foreignCard.getSuit()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Getter for cardRank
     * @return the card's rank
     */
    public String getRank() {
        return cardRank;
    }

    /**
     * Getter for cardSuit
     * @return the card's suit
     */
    public String getSuit() {
        return cardSuit;
    }

    /**
     * toString for card class
     * @return string representing the card's rank and suit. Ex. "2S" = 2 of spades
     */
    @Override
    public String toString() {
        return cardRank + cardSuit;
    }
}
