/**
 * Class represents an individual stack of chips
 * Nothing much here right now. It will be more useful when we begin to implement graphics
 * @author Luke Soda
 * @version 1.0
 */

import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

public class ChipStack {
    protected int chipAmount; // Amount of chips in the stack
    protected String imgName; // Name of image depicting stack. used for file pathing
    protected ImgExpand chipImage; // Image View for chip image
    protected StackPane chipSignature = new StackPane(); // Holds chip image and label with number of chips

    /**
     * Initial constructor for chipStack
     * @param amount Amount of chips in stack on creation
     */
    public ChipStack(int amount) {
        setChips(amount);
    }

    /**
     * Chip setter
     * @param amount Amount of chips to be set in stack
     */
    public void setChips(int amount) {
        chipAmount = amount;
        updateImage(); // Update the image based on number of chips in stack
    }

    /**
     * Remove x amount of chips from stack
     * @param amount number of chips to remove
     */
    public void removeChips(int amount) {
        chipAmount -= amount;
        updateImage(); // Update the image based on number of chips in stack
    }

    /**
     * Add x amount of chips from stack
     * @param amount number of chips to add
     */
    public void addChips(int amount) {
        chipAmount += amount;
        updateImage(); // Update the image based on number of chips in stack
    }

    /**
     * Getter for number of chips
     * @return number of chips
     */
    public int getChips() {
        return chipAmount;
    }

    /**
     * Updates the image and label contained in the chipSignature stack pane based on the number of chips in the stack
     */
    public void updateImage() {
        if (chipAmount < 10) { // Change image name based on specified intervals 
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
        Font myFont = Font.loadFont(getClass().getResourceAsStream("Super-Mario-64-DS.ttf"), 20); // Declare font
        chipImage = new ImgExpand(new Image("/gameAssets/chipStacks/" + imgName + ".png")); // Declare image view based on img name path
        Text chipValue = new Text("" + chipAmount); // Add new telling exactly how many chips are in stack
        chipValue.setTranslateY(32); // shift text
        chipValue.setFont(myFont); // set font
        chipValue.setFill(Color.WHITE); // set white
        chipSignature.getChildren().clear(); // Clear any previous images / texts in signature
        chipSignature.getChildren().addAll(chipImage, chipValue); // Add new ones back in
    }

    @Override
    public String toString() {
        return "" + chipAmount;
    }
}
