package com.example.partpiker.Parts;

public class RAM extends Part{
    private int size;
    private int speed;
    private String datarate;

    public RAM(String id, String manufacturer, String name, String datarate, int size, int speed) {
        super(id,manufacturer, name);
        this.datarate = datarate;
        this.size = size;
        this.speed = speed;
    }

    public RAM() {
    }

    public String getDatarate() {
        return datarate;
    }

    public void setDatarate(String datarate) {
        this.datarate = datarate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
