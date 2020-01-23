package main.java;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageParser {

    public BufferedImage getBufferedImage(String path) throws IOException {

        if(path == null || path.isEmpty()){
            throw new IllegalArgumentException("Image path cannot be empty");
        }

        File file = new File(path);
        BufferedImage image = ImageIO.read(file);

        return image;
    }
}
