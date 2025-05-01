/**
 * Used to automatically scale javaFX images by a factor of 4
 * @author Luke Soda
 * @version 1.0
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImgExpand extends ImageView{
    public ImgExpand(Image image) {
        super(image);
        setScaleX(4);
        setScaleY(4);
        setSmooth(false);
    }
}
