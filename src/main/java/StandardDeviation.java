package main.java;

import java.util.List;

public class StandardDeviation {

    public static Double calculate(List<Centroid> centroids) {

        if(centroids == null || centroids.isEmpty()){
            throw new IllegalArgumentException("Centroids list cannot be empty");
        }

        Integer mean = Mean.calculateForCentroids(centroids);

        double squareOfDifference = 0.0;

        for(Centroid point : centroids){
            squareOfDifference += Math.pow((point.getValue() - mean), 2);
        }

        double variance = squareOfDifference/centroids.size();

        return Math.sqrt(variance);
    }
}
