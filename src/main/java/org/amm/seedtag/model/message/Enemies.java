package org.amm.seedtag.model.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Enemies {

    private static final Logger LOGGER = LoggerFactory.getLogger(Enemies.class);

    private int number;
    private String type;

    public Enemies(){
        LOGGER.debug("new Enemies...");
    }

    public Enemies(int number, String type) {
        this.number = number;
        this.type = type;
        LOGGER.debug("new Enemies... %s", toString());
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Enemies{" +
                "number=" + number +
                ", type='" + type + '\'' +
                '}';
    }
}
