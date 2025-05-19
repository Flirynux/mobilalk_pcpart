package com.example.partpiker.Parts;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

public class Config implements Serializable {
    String mobo = null;
    String cpu = null;
    String gpu = null;
    String ram = null;

    String uID = null;

    public Config() {
    }

    public Config(String uID) {
        this.uID = uID;
    }

    public Config(String cpu, String gpu, String mobo, String ram, String uID) {
        this.cpu = cpu;
        this.gpu = gpu;
        this.mobo = mobo;
        this.ram = ram;
        this.uID = uID;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getMobo() {
        return mobo;
    }

    public void setMobo(String mobo) {
        this.mobo = mobo;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }
}
