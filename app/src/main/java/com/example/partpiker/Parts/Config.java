package com.example.partpiker.Parts;

import java.io.Serializable;

public class Config implements Serializable {
    MOBO mobo = null;
    CPU cpu = null;
    GPU gpu = null;
    RAM ram = null;

    public Config() {
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public GPU getGpu() {
        return gpu;
    }

    public void setGpu(GPU gpu) {
        this.gpu = gpu;
    }

    public MOBO getMobo() {
        return mobo;
    }

    public void setMobo(MOBO mobo) {
        this.mobo = mobo;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }
}
