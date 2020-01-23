package main.java;

import java.util.List;

public class Cluster {

    Centroid centroid;
    List<Point> points;

    Cluster(Centroid centroid){
        this.centroid = centroid;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Centroid getCentroid() {
        return centroid;
    }


}
