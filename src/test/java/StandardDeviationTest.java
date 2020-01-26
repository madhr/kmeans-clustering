package test.java;

import main.java.Centroid;
import main.java.Mean;
import main.java.StandardDeviation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StandardDeviationTest {

    @Test
    void calculateArgumentIsEmptyList(){

        ArrayList<Centroid> centroids = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            StandardDeviation.calculate(centroids);
        });
    }

    @Test
    void calculateArgumentIsOneElementList(){

        ArrayList<Centroid> centroids = new ArrayList<>();
        centroids.add(new Centroid(new Integer(10)));
        assertEquals(StandardDeviation.calculate(centroids), new Double(0.0));
    }

    @Test
    void calculateArgumentIsMoreThanOneElementList(){

        ArrayList<Centroid> centroids = new ArrayList<>();
        centroids.add(new Centroid(new Integer(10)));
        centroids.add(new Centroid(new Integer(20)));
        assertEquals(StandardDeviation.calculate(centroids), new Double(5.0));
    }
}
