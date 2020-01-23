package test.java;

import main.java.ImageParser;
import org.junit.jupiter.api.Test;

import javax.imageio.IIOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImageParserTest {

    @Test
    public void getBufferedImagePathIsInvalid() {

        ImageParser imageParser = new ImageParser();

        assertThrows(IIOException.class, () -> {
            imageParser.getBufferedImage("dummy");
        });
    }

    @Test
    public void getBufferedImagePathIsEmpty() {

        ImageParser imageParser = new ImageParser();

        assertThrows(IllegalArgumentException.class, () -> {
            imageParser.getBufferedImage(null);
        });
    }
}
