package main.java;

import java.util.List;

public class Mean {

    public static Integer calculateForPoints(List<Point> points){

        if(points == null || points.isEmpty()){
            return 0;
        }

        Integer sumOfPointsValues = points.stream().mapToInt(p -> p.getValue()).sum();

        return sumOfPointsValues / points.size();
    }

    public static Integer calculateForCentroids(List<Centroid> centroids){

        if(centroids == null || centroids.isEmpty()){
            return 0;
        }

        Integer sumOfPointsValues = centroids.stream().mapToInt(p -> p.getValue()).sum();

        return sumOfPointsValues / centroids.size();
    }
}
