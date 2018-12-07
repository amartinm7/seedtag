package org.amm.seedtag.model.message;

public class Enemies {
    private int number;
    private String type;

    public Enemies(){}

    public Enemies(int number, String type) {
        this.number = number;
        this.type = type;
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
