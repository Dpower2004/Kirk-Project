/**
 * Class represents an individual stack of chips
 * Nothing much here right now. It will be more useful when we begin to implement graphics
 * @author Luke Soda
 * @version 1.0
 */

public class ChipStack {
    protected int chipAmount;

    public ChipStack(int amount) {
        setChips(amount);
    }

    public void setChips(int amount) {
        chipAmount = amount;
    }

    public void removeChips(int amount) {
        chipAmount -= amount;
    }

    public void addChips(int amount) {
        chipAmount += amount;
    }

    public int getChips(){
        return chipAmount;
    }

    @Override
    public String toString() {
        return "" + chipAmount;
    }
}
