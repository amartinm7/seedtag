package org.amm.seedtag.model.message;

import java.awt.geom.Point2D;
import java.util.Comparator;

public class Scan {

    private Coordinates coordinates;
    private Enemies enemies;
    private int allies;

    public Scan(){}
    public Scan(Coordinates coordinates, Enemies enemies, int allies) {
        this.coordinates = coordinates;
        this.enemies = enemies;
        this.allies = allies;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Enemies getEnemies() {
        return enemies;
    }

    public void setEnemies(Enemies enemies) {
        this.enemies = enemies;
    }

    public int getAllies() {
        return allies;
    }

    public void setAllies(int allies) {
        this.allies = allies;
    }

    @Override
    public String toString() {
        return "Scan{" +
                "coordinates=" + coordinates +
                ", enemies=" + enemies +
                ", allies=" + allies +
                '}';
    }

    public static class ScanComparator implements Comparator<Scan> {

        @Override
        public int compare(Scan o1, Scan o2) {
            final Point2D finalP = new Point2D.Double(0, 0);
            final Point2D point1 = new Point2D.Double(o1.coordinates.getX(), o1.coordinates.getY());
            final Point2D point2 = new Point2D.Double(o2.coordinates.getX(), o2.coordinates.getY());
            double ds0 = point1.distanceSq(finalP);
            double ds1 = point2.distanceSq(finalP);
            return Double.compare(ds0, ds1);
        }

    }
}
