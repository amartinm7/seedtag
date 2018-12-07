package org.amm.seedtag.model.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Coordinates {

    private static final Logger LOGGER = LoggerFactory.getLogger(Coordinates.class);

    private int x;
    private int y;

    public Coordinates(){
        LOGGER.debug("new Coordinates...");
    }

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
        LOGGER.debug("new Coordinates... %s", toString());
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

}
