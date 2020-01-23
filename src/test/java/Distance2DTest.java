package test.java;

import main.java.Distance;
import main.java.Distance2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Distance2DTest {

    @Test
    void testGetDistance() {
        Distance distance = new Distance2D();
        assertEquals(distance.getDistance(12.5, 13.5), 1.0);
    }

    @Test
    void testGetDistanceArgumentsEqual() {
        Distance distance = new Distance2D();
        assertEquals(distance.getDistance(0.0, 0.0), 0.0);
    }

}
