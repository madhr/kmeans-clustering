package test.java;

import main.java.Centroid;
import main.java.KMeansClusteringImplementation;
import main.java.Point;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class KMeansClusteringImplementationTest {

    private final static int BUFFERED_IMAGE_WIDTH = 20;
    private final static int BUFFERED_IMAGE_HEIGHT = 15;
    private final static int NUMBER_OF_CENTROIDS = 5;
    private final static int NUMBER_OF_RETRIES = 5;

    @Test
    public void getResultCentroids() throws IOException {

        KMeansClusteringImplementation kMeans = new KMeansClusteringImplementation();

        String path = "src/main/resources/55790034.JPG";

        assertTrue(kMeans.getResultCentroids(NUMBER_OF_CENTROIDS, NUMBER_OF_RETRIES, path).size() > 0);
    }

    @Test
    public void getNearestCentroidArgumentsNull() {

        KMeansClusteringImplementation kMeans = new KMeansClusteringImplementation();

        assertThrows(IllegalArgumentException.class, () -> {
            kMeans.getNearestCentroid(null, null);
        });
    }

    @Test
    public void getNearestCentroid() {

        KMeansClusteringImplementation kMeans = new KMeansClusteringImplementation();

        Point record = new Point(new Integer(1000));
        ArrayList<Centroid> centroids = new ArrayList<>();
        centroids.add(new Centroid(new Integer(2000)));
        centroids.add(new Centroid(new Integer(3000)));

        assertEquals(kMeans.getNearestCentroid(record, centroids).getValue(), new Integer(2000));
    }

    @Test
    public void getNearestPointArgumentsNull() {

        KMeansClusteringImplementation kMeans = new KMeansClusteringImplementation();

        assertThrows(IllegalArgumentException.class, () -> {
            kMeans.getNearestPoint(null, null);
        });
    }

    @Test
    public void getNearestPoint() {

        KMeansClusteringImplementation kMeans = new KMeansClusteringImplementation();

        Point record = new Point(new Integer(1000));
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(new Integer(2000)));
        points.add(new Point(new Integer(3000)));

        assertEquals(kMeans.getNearestPoint(record, points).getValue(), new Integer(2000));
    }

    @Test
    public void calculateAverageArgumentIsNull() {

        KMeansClusteringImplementation kMeans = new KMeansClusteringImplementation();

        assertEquals(kMeans.calculateAverage(null), new Integer(0));
    }

    @Test
    public void calculateAverageArgumentIsEmptyList() {

        KMeansClusteringImplementation kMeans = new KMeansClusteringImplementation();

        ArrayList<Point> points = new ArrayList<>();
        assertEquals(kMeans.calculateAverage(points), new Integer(0));
    }

    @Test
    public void calculateAverageReturnsAverageOfTwoNumbers() {

        KMeansClusteringImplementation kMeans = new KMeansClusteringImplementation();

        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(new Integer(10)));
        points.add(new Point(new Integer(12)));
        assertEquals(kMeans.calculateAverage(points), new Integer(11));
    }

    @Test
    public void getInitialCentroidsArgumentIsNullThrowsIllegalArgumentException() {

        KMeansClusteringImplementation kMeans = new KMeansClusteringImplementation();

        assertThrows(IllegalArgumentException.class, () -> {
            kMeans.getInitialCentroids(NUMBER_OF_CENTROIDS,null);
        });
    }

    @Test
    public void getInitialCentroidsReturnsListOfCentroidsWithSize() {

        KMeansClusteringImplementation kMeans = new KMeansClusteringImplementation();
        BufferedImage bufferedImage = new BufferedImage(BUFFERED_IMAGE_WIDTH, BUFFERED_IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

        assertTrue(kMeans.getInitialCentroids(NUMBER_OF_CENTROIDS, bufferedImage).size() == NUMBER_OF_CENTROIDS);
    }

    @Test
    public void getAllPixelsArgumentIsNullThrowsIllegalArgumentException() {

        KMeansClusteringImplementation kMeans = new KMeansClusteringImplementation();

        assertThrows(IllegalArgumentException.class, () -> {
            kMeans.getBufferedImagePixelsAsPoints(null);
        });
    }

    @Test
    public void getAllPixelsReturnsListOfPointsWithSize() {

        KMeansClusteringImplementation kMeans = new KMeansClusteringImplementation();
        BufferedImage bufferedImage = new BufferedImage(BUFFERED_IMAGE_WIDTH, BUFFERED_IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

        assertTrue(kMeans.getBufferedImagePixelsAsPoints(bufferedImage).size() == BUFFERED_IMAGE_WIDTH * BUFFERED_IMAGE_HEIGHT);
    }

}
