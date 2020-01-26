package main.java;

import java.io.IOException;
import java.util.List;

public interface IKMeansClustering {

    List<KCentroids> getResultCentroids(int k, int retryLimit, String path) throws IOException;
}
