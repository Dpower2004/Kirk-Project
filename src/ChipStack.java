import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.image.Image;

/**
 * Class represents an individual stack of chips
 * Nothing much here right now. It will be more useful when we begin to implement graphics
 * @author Luke Soda
 * @version 1.0
 */

public class ChipStack {
    protected int chipAmount;
    protected String imgName;
    protected ImgExpand chipImage;
    protected StackPane chipSignature = new StackPane();

    public ChipStack(int amount) {
        setChips(amount);
    }

    public void setChips(int amount) {
        chipAmount = amount;
        updateImage();
    }

    public void removeChips(int amount) {
        chipAmount -= amount;
        updateImage();
    }

    public void addChips(int amount) {
        chipAmount += amount;
        updateImage();
    }

    public int getChips() {
        return chipAmount;
    }

    public void updateImage() {
        if (chipAmount < 10) {
            imgName = "1";
        }
        else if (chipAmount < 30) {
            imgName = "10";
        }
        else if (chipAmount < 50) {
            imgName = "30";
        }
        else if (chipAmount < 100) {
            imgName = "50";
        }
        else if (chipAmount < 250) {
            imgName = "100";
        }
        else if (chipAmount < 500) {
            imgName = "250";
        }
        else {
            imgName = "500";
        }
        Font myFont = Font.loadFont(getClass().getResourceAsStream("Super-Mario-64-DS.ttf"), 20);
        chipImage = new ImgExpand(new Image("/gameAssets/chipStacks/" + imgName + ".png"));
        Text chipValue = new Text("" + chipAmount);
        chipValue.setTranslateY(32);
        chipValue.setFont(myFont);
        chipValue.setFill(Color.WHITE);
        chipSignature.getChildren().clear();
        chipSignature.getChildren().addAll(chipImage, chipValue);
    }

    @Override
    public String toString() {
        return "" + chipAmount;
    }
}
