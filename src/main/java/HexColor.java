package main.java;

public class HexColor {

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    String value;

    public HexColor(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
