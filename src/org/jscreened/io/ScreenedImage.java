package org.jscreened.io;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by Kevin on 11-11-2015.
 */
public final class ScreenedImage {

    private BufferedImage image = null;
    private File file = null;
    private String data = null;

    public void create() {
        try {
            file = new File("placeholder.jpg");
            image = ImageIO.read(file);

            final ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            ImageIO.write(image, "png", byteArray);
            final byte[] imageByte = byteArray.toByteArray();
            final String dataImage = Base64.encode(imageByte);
            data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(dataImage, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getData() {
        return data;
    }
}
