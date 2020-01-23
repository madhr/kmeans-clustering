package main.java;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class KMeansClusteringImplementation implements IKMeansClustering {

    public List<Centroid> getResultCentroids(int k, int retryLimit, String path) throws IOException {

        ImageParser imageParser = new ImageParser();
        BufferedImage image = imageParser.getBufferedImage(path);

        List<Centroid> initialCentroids = getInitialCentroids(k, image);
        List<Point> allPixels = getBufferedImagePixelsAsPoints(image);

        Map<Centroid,List<Point>> centroidsToListOfPoints = new HashMap<>();

        for(Centroid centroid : initialCentroids){
            centroidsToListOfPoints.put(centroid, new ArrayList<>());
        }

        int retry = 0;

        while(retry < retryLimit) {

            for (Point pixel : allPixels) {
                Centroid nearest = getNearestCentroid(pixel, initialCentroids);
                if (centroidsToListOfPoints.get(nearest) == null) {
                    centroidsToListOfPoints.put(nearest, new ArrayList<>(Arrays.asList(pixel)));
                } else {
                    centroidsToListOfPoints.get(nearest).add(pixel);
                }
            }

            //        compute k new centroids (average of all samples in a cluster)

            Map<Centroid, Centroid> oldAndNewMap = new HashMap<>();

            for (Centroid oldCentroid : centroidsToListOfPoints.keySet()) {
                List<Point> points = centroidsToListOfPoints.get(oldCentroid);
                Integer average = calculateAverage(points);
                Point nearest = getNearestPoint(new Point(average), points);
                Centroid newCentroid = new Centroid(nearest.value);
                oldAndNewMap.put(oldCentroid, newCentroid);
            }

            // check if different and update centers

            for (Centroid oldCentroid : oldAndNewMap.keySet()){
                Centroid newCentroid = oldAndNewMap.get(oldCentroid);
                if(newCentroid.getValue() != oldCentroid.getValue()){
                    List<Point> points = centroidsToListOfPoints.get(oldCentroid);
                    centroidsToListOfPoints.remove(oldCentroid);
                    centroidsToListOfPoints.put(newCentroid, points);
                }
            }

            retry++;
        }

        return centroidsToListOfPoints.keySet().stream().collect(Collectors.toList());
    }


    public Centroid getNearestCentroid(Point record, List<Centroid> centroids) {

        if(record == null){
            throw new IllegalArgumentException("Point cannot be null");
        }
        if(centroids == null || centroids.isEmpty()){
            throw new IllegalArgumentException("List of centroids cannot be null or empty");
        }

        double minimumDistance = Double.MAX_VALUE;
        Centroid nearest = null;

        for (Centroid centroid : centroids) {
            Distance distance = new Distance2D();
            double currentDistance = distance.getDistance(record.getValue(), centroid.getValue());

            if (currentDistance < minimumDistance) {
                minimumDistance = currentDistance;
                nearest = centroid;
            }
        }
        return nearest;
    }

    public Point getNearestPoint(Point record, List<Point> points) {

        if(record == null){
            throw new IllegalArgumentException("Point cannot be null");
        }
        if(points == null || points.isEmpty()){
            throw new IllegalArgumentException("List of points cannot be null or empty");
        }

        double minimumDistance = Double.MAX_VALUE;
        Point nearest = null;

        for (Point point : points) {
            Distance distance = new Distance2D();
            double currentDistance = distance.getDistance(record.getValue(), point.getValue());

            if (currentDistance < minimumDistance) {
                minimumDistance = currentDistance;
                nearest = point;
            }
        }
        return nearest;
    }

    public List<Centroid> getInitialCentroids(int k, BufferedImage image){

        if(image == null){
            throw new IllegalArgumentException("Buffered image cannot be null");
        }

        List<Centroid> initialCentroids = new ArrayList<>();

        int xAxes = image.getWidth();
        int yAxes = image.getHeight();

        for(int i = 0; i < k; i++) {
            int coordX = new Random().nextInt(xAxes);
            int coordY = new Random().nextInt(yAxes);
            Integer rgb = image.getRGB(coordX, coordY);
            initialCentroids.add(new Centroid(rgb));
        }
        return initialCentroids;
    }

    public List<Point> getBufferedImagePixelsAsPoints(BufferedImage image){

        if(image == null){
            throw new IllegalArgumentException("Buffered image cannot be null");
        }

        List<Point> allPixels = new ArrayList<>();

        int xAxes = image.getWidth();
        int yAxes = image.getHeight();

        for(int x = 0; x < xAxes; x++){
            for(int y = 0; y < yAxes; y++){
                Integer rgb = image.getRGB(x,y);
                allPixels.add(new Point(rgb));
            }
        }
        return  allPixels;
    }

    public Integer calculateAverage(List<Point> points){

        if(points == null || points.isEmpty()){
            return 0;
        }

        Integer sum = 0;

        for(Point point : points){
            sum += point.value;
        }

        return sum;
    }

}
