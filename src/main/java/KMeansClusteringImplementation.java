package main.java;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class KMeansClusteringImplementation implements IKMeansClustering {

    public List<KCentroids> getListOfKCentroids(int k, int retryLimit, String path) throws IOException {

        ImageParser imageParser = new ImageParser();
        BufferedImage image = imageParser.getBufferedImage(path);

        List<Point> points = getBufferedImagePixelsAsPoints(image);
        List<Centroid> initialCentroids = initializeCentroids(k, image);
        Map<Centroid,List<Point>> centroidsToListOfPoints = initializeCentroidsToListOfPoints(initialCentroids);

        LinkedList<KCentroids> kCentroidsList = new LinkedList();

        int retry = 0;

        while(retry < retryLimit) {

            centroidsToListOfPoints = reassignPointsToNearestCentroids(points, centroidsToListOfPoints);
            Map<Centroid, Centroid> oldAndNewMap = computeKNewCentroids(centroidsToListOfPoints);
            centroidsToListOfPoints = updateCentroids(centroidsToListOfPoints, oldAndNewMap);

            KCentroids kCentroids = convertToKCentroids(centroidsToListOfPoints);
            kCentroidsList.add(kCentroids);

            retry++;
        }

        return kCentroidsList;
    }

    public Map<Centroid,List<Point>> initializeCentroidsToListOfPoints(List<Centroid> initialCentroids){
        Map<Centroid,List<Point>> centroidsToListOfPoints = new HashMap<>();
        for(Centroid centroid : initialCentroids){
            centroidsToListOfPoints.put(centroid, new ArrayList<>());
        }
        return centroidsToListOfPoints;
    }

    public KCentroids convertToKCentroids(Map<Centroid,List<Point>> centroidsToListOfPoints){
        List<Centroid> centroidList = centroidsToListOfPoints.keySet().stream().collect(Collectors.toList());
        Collections.sort(centroidList);
        Double standardDeviation = StandardDeviation.calculate(centroidList);
        return new KCentroids(centroidList, standardDeviation);
    }

    public Map<Centroid,List<Point>> reassignPointsToNearestCentroids(List<Point> points, Map<Centroid,List<Point>> centroidsToListOfPoints){

        List<Centroid> currentCentroids = new ArrayList<Centroid>(centroidsToListOfPoints.keySet());

        for (Point point : points) {
            Centroid nearest = getNearestCentroid(point, currentCentroids);
            if (centroidsToListOfPoints.get(nearest) == null) {
                centroidsToListOfPoints.put(nearest, new ArrayList<>(Arrays.asList(point)));
            } else {
                centroidsToListOfPoints.get(nearest).add(point);
            }
        }

        return centroidsToListOfPoints;
    }

    public Map<Centroid, Centroid> computeKNewCentroids(Map<Centroid,List<Point>> centroidsToListOfPoints){

        Map<Centroid, Centroid> oldAndNewMap = new HashMap<>();
        for (Centroid oldCentroid : centroidsToListOfPoints.keySet()) {
            List<Point> points = centroidsToListOfPoints.get(oldCentroid);
            Integer average = Mean.calculateForPoints(points);
            Point nearest = getNearestPoint(new Point(average), points);
            Centroid newCentroid = new Centroid(nearest.getValue());
            oldAndNewMap.put(oldCentroid, newCentroid);
        }
        return oldAndNewMap;
    }

    public Map<Centroid,List<Point>> updateCentroids(Map<Centroid,List<Point>> centroidsToListOfPoints, Map<Centroid, Centroid> oldAndNewMap){
        for (Centroid oldCentroid : oldAndNewMap.keySet()){
            Centroid newCentroid = oldAndNewMap.get(oldCentroid);
            if(newCentroid.getValue() != oldCentroid.getValue()){
                List<Point> points = centroidsToListOfPoints.remove(oldCentroid);
                centroidsToListOfPoints.put(newCentroid, points);
            }
        }

        return centroidsToListOfPoints;
    }

    public Centroid getNearestCentroid(Point point, List<Centroid> centroids) {

        if(point == null){
            throw new IllegalArgumentException("Point cannot be null");
        }
        if(centroids == null || centroids.isEmpty()){
            throw new IllegalArgumentException("List of centroids cannot be null or empty");
        }

        double minimumDistance = Double.MAX_VALUE;
        Centroid nearest = null;

        for (Centroid centroid : centroids) {
            Distance distance = new Distance2D();
            double currentDistance = distance.getDistance(point.getValue(), centroid.getValue());

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

    public List<Centroid> initializeCentroids(int k, BufferedImage image){

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



}
