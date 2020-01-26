package main.java;

import java.util.List;

public class KCentroids {

    private List<Centroid> centroidList;

    public KCentroids(List<Centroid> centroidList) {
        this.centroidList = centroidList;
    }

    public List<Centroid> getCentroidList() {
        return centroidList;
    }
}
