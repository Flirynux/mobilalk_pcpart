package com.example.partpiker.Parts;

public class GPU extends Part{
    private int VRAM_MB;
    private String VRAM_ver;
    private float PCIE_version;
    private float vram_speed_gbps;
    private int bus_width_bits;

    public GPU(String manufacturer, String name, int bus_width_bits, float PCIE_version, float vram_speed_gbps, int VRAM_MB, String VRAM_ver) {
        super(manufacturer, name);
        this.bus_width_bits = bus_width_bits;
        this.PCIE_version = PCIE_version;
        this.vram_speed_gbps = vram_speed_gbps;
        this.VRAM_MB = VRAM_MB;
        this.VRAM_ver = VRAM_ver;
    }

    public int getBus_width_bits() {
        return bus_width_bits;
    }

    public void setBus_width_bits(int bus_width_bits) {
        this.bus_width_bits = bus_width_bits;
    }

    public float getPCIE_version() {
        return PCIE_version;
    }

    public void setPCIE_version(float PCIE_version) {
        this.PCIE_version = PCIE_version;
    }

    public int getVRAM_MB() {
        return VRAM_MB;
    }

    public void setVRAM_MB(int VRAM_MB) {
        this.VRAM_MB = VRAM_MB;
    }

    public float getVram_speed_gbps() {
        return vram_speed_gbps;
    }

    public void setVram_speed_gbps(float vram_speed_gbps) {
        this.vram_speed_gbps = vram_speed_gbps;
    }

    public String getVRAM_ver() {
        return VRAM_ver;
    }

    public void setVRAM_ver(String VRAM_ver) {
        this.VRAM_ver = VRAM_ver;
    }
}
