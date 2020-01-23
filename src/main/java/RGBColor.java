package main.java;

import java.util.Objects;

public class RGBColor {

    private int alpha;
    private int red;
    private int green;
    private int blue;

    public RGBColor(int alpha, int red, int green, int blue) {
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RGBColor rgb = (RGBColor) o;
        return alpha == rgb.alpha &&
                red == rgb.red &&
                green == rgb.green &&
                blue == rgb.blue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(alpha, red, green, blue);
    }

    public int getAlpha() {
        return alpha;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    @Override
    public String toString() {
        return "(" + red +
                "," + green +
                "," + blue +
                ")";
    }

}
