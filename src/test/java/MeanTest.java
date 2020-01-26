package test.java;

import main.java.Centroid;
import main.java.Mean;
import main.java.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeanTest {

    @Test
    public void calculateForPointsArgumentIsEmptyList() {

        ArrayList<Point> points = new ArrayList<>();
        assertEquals(Mean.calculateForPoints(points), new Integer(0));
    }

    @Test
    public void calculateForPointsReturnsAverageOfTwoNumbers() {

        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(new Integer(10)));
        points.add(new Point(new Integer(12)));
        assertEquals(Mean.calculateForPoints(points), new Integer(11));
    }

    @Test
    public void calculateForCentroidsArgumentIsEmptyList() {

        ArrayList<Centroid> centroids = new ArrayList<>();
        assertEquals(Mean.calculateForCentroids(centroids), new Integer(0));
    }

    @Test
    public void calculateForCentroidsReturnsAverageOfTwoNumbers() {

        ArrayList<Centroid> centroids = new ArrayList<>();
        centroids.add(new Centroid(new Integer(10)));
        centroids.add(new Centroid(new Integer(12)));
        assertEquals(Mean.calculateForCentroids(centroids), new Integer(11));
    }
}
