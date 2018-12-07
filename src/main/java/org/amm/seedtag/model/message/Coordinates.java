package org.amm.seedtag.model.message;

import java.awt.geom.Point2D;
import java.util.Comparator;
import java.util.Objects;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(){}

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return getX() == that.getX() &&
                getY() == that.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    public static class CoordinatesComparator implements Comparator<Coordinates> {

        @Override
        public int compare(Coordinates o1, Coordinates o2) {
            final Point2D finalP = new Point2D.Double(0, 0);
            final Point2D point1 = new Point2D.Double(o1.getX(), o1.getY());
            final Point2D point2 = new Point2D.Double(o2.getX(), o2.getY());
            double ds0 = point1.distanceSq(finalP);
            double ds1 = point2.distanceSq(finalP);
            return Double.compare(ds0, ds1);
        }

    }
}
