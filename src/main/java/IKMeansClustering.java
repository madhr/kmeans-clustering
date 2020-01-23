package main.java;

import java.io.IOException;
import java.util.List;

public interface IKMeansClustering {

    List<Centroid> getResultCentroids(int k, int retryLimit, String path) throws IOException;
}
