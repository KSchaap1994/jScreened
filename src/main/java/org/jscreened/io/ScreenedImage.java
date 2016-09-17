package org.jscreened.io;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.URLCodec;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by Kevin on 11-11-2015.
 */
public final class ScreenedImage {

    private final BufferedImage image;
    private String data = null;

    public ScreenedImage(final BufferedImage image) {
        this.image = image;
        create();
    }

    private void create() {
        try {

            final ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            ImageIO.write(image, "png", byteArray);
            final byte[] imageByte = byteArray.toByteArray();
            final String dataImage = Base64.encodeBase64String(imageByte);
            final URLCodec codec = new URLCodec("UTF-8");
            data = String.format("%s=%s", codec.encode("image"), codec.encode(dataImage));
        } catch (IOException | EncoderException e) {
            e.printStackTrace();
        }
    }

    public String getData() {
        return data;
    }
}
