;
public enum Hand {
    ROYAL_FLUSH(9),
    STRAIGHT_FLUSH(8),
    FOUR_KIND(7),
    FULL_HOUSE(6),
    FLUSH(5),
    STRAIGHT(4),
    THREE_KIND(3),
    TWO_PAIR(2),
    PAIR(1),
    HIGH_CARD(0);

    protected final int VALUE;

    Hand(int VALUE) {
        this.VALUE = VALUE;
    }
}
