package main.java;

import java.util.ArrayList;
import java.util.List;

public class RGBConverter extends ColorConverter {

    @Override
    public List<RGBColor> convertToColor(List<Centroid> centroids) {

        if(centroids == null){
            throw new IllegalArgumentException("Centroids list cannot be null");
        }

        List<RGBColor> rgbs = new ArrayList<>();

        for(Centroid c : centroids){
            int pixel = c.getValue();
            int alpha = (pixel >> 24) & 0xff;
            int red = (pixel >> 16) & 0xff;
            int green = (pixel >> 8) & 0xff;
            int blue = (pixel) & 0xff;
            RGBColor rgb = new RGBColor(alpha, red, green, blue);
            rgbs.add(rgb);
        }
        return rgbs;
    }
}
