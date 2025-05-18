package com.example.partpiker.Parts;

public class CPU extends Part{

    private String socket;
    private int cores;
    private int threads;
    private float base_speed;
    private float boost_speed;
    private int max_ram_GB;
    private float max_pcie;

    public CPU(String manufacturer, String name, float base_speed, float boost_speed, int cores, float max_pcie, int max_ram_GB, String socket, int threads) {
        super(manufacturer, name);
        this.base_speed = base_speed;
        this.boost_speed = boost_speed;
        this.cores = cores;
        this.max_pcie = max_pcie;
        this.max_ram_GB = max_ram_GB;
        this.socket = socket;
        this.threads = threads;
    }

    public float getBase_speed() {
        return base_speed;
    }

    public void setBase_speed(float base_speed) {
        this.base_speed = base_speed;
    }

    public float getBoost_speed() {
        return boost_speed;
    }

    public void setBoost_speed(float boost_speed) {
        this.boost_speed = boost_speed;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public float getMax_pcie() {
        return max_pcie;
    }

    public void setMax_pcie(float max_pcie) {
        this.max_pcie = max_pcie;
    }

    public int getMax_ram_GB() {
        return max_ram_GB;
    }

    public void setMax_ram_GB(int max_ram_GB) {
        this.max_ram_GB = max_ram_GB;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }
}
