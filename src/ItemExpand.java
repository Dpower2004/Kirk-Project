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
