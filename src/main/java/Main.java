package main.java;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        KMeansClusteringImplementation kMeans = new KMeansClusteringImplementation();
        String path = "src/main/resources/kmc-expo.JPG";

        int k = 5;
        int retryLimit = 3;

        List<KCentroids> centroids = kMeans.getListOfKCentroids(k, retryLimit, path);

        HexColorConverter hexConverter = new HexColorConverter();
        for(KCentroids kCentroids : centroids){
            List<HexColor> hexColors = hexConverter.convertToColor(kCentroids.getCentroidList());
            System.out.println(hexColors+" standard deviation: "+kCentroids.getStandardDeviation());
        }
    }
}
