package com.example.partpiker.Parts;


import java.io.Serializable;

public class Part implements Serializable {
    private String id;
    private String name;
    private String manufacturer;

    public Part(String id, String manufacturer, String name) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.name = name;
    }

    public Part() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
