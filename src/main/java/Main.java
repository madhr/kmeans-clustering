package main.java;

import java.io.IOException;
import java.util.List;


public class Main {


    public static void main(String[] args) throws IOException {
        KMeansClusteringImplementation kMeans = new KMeansClusteringImplementation();
        String path = "src/main/resources/55790034.JPG";

        List<Centroid> centroids = kMeans.getResultCentroids(10, 10, path);

        HexColorConverter hexConverter = new HexColorConverter();
        List<HexColor> hexColors = hexConverter.convertToColor(centroids);

        System.out.println(hexColors);

        RGBConverter rgbConverter = new RGBConverter();
        List<RGBColor> rgbColors = rgbConverter.convertToColor(centroids);

        System.out.println(rgbColors);

    }
}
