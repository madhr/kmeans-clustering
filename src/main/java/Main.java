package main.java;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        KMeansClusteringImplementation kMeans = new KMeansClusteringImplementation();
        String path = "src/main/resources/expo.JPG";

        int k = 8;
        int retryLimit = 10;

        List<KCentroids> centroids = kMeans.getResultCentroids(k, retryLimit, path);

        HexColorConverter hexConverter = new HexColorConverter();
        for(KCentroids centroidsList : centroids){
            List<HexColor> hexColors = hexConverter.convertToColor(centroidsList.getCentroidList());
            System.out.println(hexColors);
        }
    }
}
