import java.util.Objects;

/**
 * Represents an individual card to be passed into the CardList class.
 * @author Luke Soda
 * @version 1.01
 */
public class Card {
    protected String cardRank; // The rank of the card
    protected String cardSuit; // The suit of the card
    protected boolean hidden = true; // Whether or not card is flipped up or down.
    protected int value;

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
        assignValue();
    }

    /**
     * Copy constructor for Card class
     * @param duplicate Card to be duplicated (For returning references)
     */
    public Card(Card duplicate) {
        this.cardRank = duplicate.cardRank;
        this.cardSuit = duplicate.cardSuit;
        assignValue();
    }

    public void assignValue() {
        switch (cardRank) {
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "10":
                value = Integer.parseInt(cardRank);
                break;
            case "J":
                value = 11;
                break;
            case "Q":
                value = 12;
                break;
            case "K":
                value = 13;
                break;
            case "A":
                value = 14;
                break;
            case "V":
                value = 1;
                break;
        }
    }

    /**
     * Equals method for Card class
     * Check to see if two cards have the same rank and suit. Print bool accordingly
     * @param foreignCard Card to be compared to calling object
     * @return boolean indicating if the two cards are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return cardRank.equals(card.cardRank) && cardSuit.equals(card.cardSuit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardRank, cardSuit);
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
     * Determines whether or not the card is hidden or visible (flipped or not)
     * @param hidden Boolean deciding if hidden or not
     */
    public void setFlip(boolean hidden) {
        this.hidden = hidden;
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
