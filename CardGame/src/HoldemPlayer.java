import java.util.Scanner;

public class HoldemPlayer extends Player {
    protected Blind blind;
    protected int inChips;
    protected int toBet;
    protected Scanner scanner;
    protected String currentAction;
    protected boolean isActive; // fold or no

    public HoldemPlayer(int chips, boolean isMain, Blind blind) {
        super(chips, isMain);
        isActive = true;
        this.blind = blind;
        if (isMain) {
            scanner = new Scanner(System.in);
        }
    }

    public void setBlind(Blind blind) {
        this.blind = blind;
    }

    public void addInChips(int bet) {
        chips -= bet;
        inChips += bet;
    }

    public void fold() {
        isActive = false;
    }

    @Override
    public void chooseAction() {
        toBet = 0;
        if (isMain) {
            System.out.print("Check/Call, Bet/Raise, Fold? (C/B/F): ");
            currentAction = scanner.nextLine();
            if (currentAction.equals("B")) {
                System.out.print("Enter bet: ");
                toBet = scanner.nextInt();
            }
        }
        else {
            currentAction = "C";
        }
    }

    @Override
    public String toString() {
        return  super.toString() + "\nBlind: " + blind + "\n" + "inChips: " + inChips + "\n\n";
    }
}
