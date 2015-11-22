package resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA
 * User: Kevin
 * Date: 22-11-2015
 * Time: 16:50
 * To change this template use File | Settings | File Templates.
 */
public class ResourceHelper {

    private static final HashMap<String, BufferedImage> imageCache;

    static {
        imageCache = new HashMap<>();
    }

    public static BufferedImage loadImage(final String name) {
        final BufferedImage image = imageCache.get(name);
        if (image == null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(ResourceHelper.class.getResource(name));
                imageCache.put(name, bufferedImage);
                return bufferedImage;
            } catch (Exception e) {
                return null;
            }
        }
        return image;
    }
}
