package main.java;

import java.util.Objects;

public class Centroid implements Comparable<Centroid>{

    private Integer value;


    public Centroid(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Centroid centroid = (Centroid) o;
        return Objects.equals(value, centroid.value);
    }

    @Override
    public int compareTo(Centroid centroid) {
        if (getValue() == null || centroid.getValue() == null) {
            return 0;
        }
        return getValue().compareTo(centroid.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
