/**
 * Used to automatically scale javaFX images by a factor of 2
 * @author Luke Soda
 * @version 1.0
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemExpand extends ImageView{
    public ItemExpand(Image image) {
        super(image);
        setSmooth(false);
        setScaleX(2);
        setScaleY(2);
    }
}
