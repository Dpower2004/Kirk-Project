
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RetroImageView extends ImageView {

    public RetroImageView(Image image) {
        super(image);
        setSmooth(true); // Disable smoothing by default
        setScaleX(4);
        setScaleY(4);
    }
    
    // Optional: add constructors if needed
    public RetroImageView(String url) {
        super(new Image(url));
        setSmooth(false);
    }
}