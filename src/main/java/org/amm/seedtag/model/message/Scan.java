package org.amm.seedtag.model.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scan {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scan.class);

    private Coordinates coordinates;
    private Enemies enemies;
    private int allies;

    public Scan(){
        LOGGER.debug("new Scan...");
    }
    public Scan(Coordinates coordinates, Enemies enemies, int allies) {
        this.coordinates = coordinates;
        this.enemies = enemies;
        this.allies = allies;
        LOGGER.debug("new Scan... %s", toString());
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
}
