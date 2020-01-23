package main.java;

import java.util.ArrayList;
import java.util.List;

public class HexColorConverter extends ColorConverter {

    @Override
    public List<HexColor> convertToColor(List<Centroid> centroids) {

        if(centroids == null){
            throw new IllegalArgumentException("Centroids list cannot be null");
        }

        List<HexColor> hexColors = new ArrayList<>();

        for(Centroid centroid : centroids){
            HexColor hexColor = new HexColor(String.format("#%06X", (0xFFFFFF & centroid.getValue())));
            hexColors.add(hexColor);
        }

        return hexColors;
    }
}
