package main.java;

import java.util.List;

public class KCentroids {

    private List<Centroid> centroidList;
    private Double standardDeviation;

    public KCentroids(List<Centroid> centroidList, Double standardDeviation) {
        this.centroidList = centroidList;
        this.standardDeviation = standardDeviation;
    }

    public List<Centroid> getCentroidList() {
        return centroidList;
    }

    public Double getStandardDeviation() {
        return standardDeviation;
    }
}
