package com.example.partpiker.Parts;


import java.io.Serializable;

public class Part implements Serializable {
    private String name;
    private String manufacturer;

    public Part(String manufacturer, String name) {
        this.manufacturer = manufacturer;
        this.name = name;
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
