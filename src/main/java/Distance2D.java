package main.java;

public class Distance2D extends Distance {

    @Override
    public Double getDistance(Double a, Double b) {
        return Math.abs(a - b);
    }

    @Override
    public Double getDistance(Integer a, Integer b) {
        return Double.valueOf(Math.abs(a - b));
    }
}
