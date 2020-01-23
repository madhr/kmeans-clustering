package test.java;

import main.java.Centroid;
import main.java.ColorConverter;
import main.java.RGBConverter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RGBColorTest {

    @Test
    public void convertToRGBsArgumentIsNullThrowsIllegalArgmentException() {

        ColorConverter color = new RGBConverter();
        assertThrows(IllegalArgumentException.class, () -> {
            color.convertToColor(null);
        });
    }

    @Test
    public void convertToRGBsArgumentIsEmptyListReturnsEmptyList() {

        ColorConverter color = new RGBConverter();
        ArrayList<Centroid> centroids = new ArrayList<>();
        assertTrue(color.convertToColor(centroids).isEmpty());
    }

    @Test
    public void convertToRGBsListArgumentReturnsRGBRedValue() {

        RGBConverter converter = new RGBConverter();
        ArrayList<Centroid> centroids = new ArrayList<>();
        centroids.add(new Centroid(456424));
        assertEquals(converter.convertToColor(centroids).get(0).getRed(), 6);
    }

    @Test
    public void convertToRGBsListArgumentReturnsRGBGreenValue() {

        RGBConverter converter = new RGBConverter();
        ArrayList<Centroid> centroids = new ArrayList<>();
        centroids.add(new Centroid(456424));
        assertEquals(converter.convertToColor(centroids).get(0).getGreen(),  246);

    }

    @Test
    public void convertToRGBsListArgumentReturnsRGBBlueValue() {

        RGBConverter converter = new RGBConverter();
        ArrayList<Centroid> centroids = new ArrayList<>();
        centroids.add(new Centroid(456424));
        assertEquals(converter.convertToColor(centroids).get(0).getBlue(), 232);

    }

    @Test
    public void convertToRGBsListArgumentReturnsRGBAlphaValue() {

        RGBConverter converter = new RGBConverter();
        ArrayList<Centroid> centroids = new ArrayList<>();
        centroids.add(new Centroid(456424));
        assertEquals(converter.convertToColor(centroids).get(0).getAlpha(), 0);
    }
}
