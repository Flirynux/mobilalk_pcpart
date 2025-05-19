package com.example.partpiker.Parts;

public class MOBO extends Part{
    private String cpu_socket;
    private String chipset;
    private String form_factor;
    private String ram_ver;
    private int max_ram_GB;
    private int max_ram_speed_MHz;
    private int ram_slots;
    private int pcie_ver;
    private int sata_slots;
    private int nvme_slots;
    private int pcie_x16_slots;
    private int pcie_x4_slots;
    private int pcie_x1_slots;
    private int fan_connectors;

    public MOBO(String id, String manufacturer, String name, String chipset, String cpu_socket,
                int fan_connectors, String form_factor, int max_ram_GB, int max_ram_speed_MHz,
                int nvme_slots, int pcie_ver, int pcie_x16_slots, int pcie_x1_slots,
                int pcie_x4_slots, int ram_slots, String ram_ver, int sata_slots) {
        super(id,manufacturer, name);
        this.chipset = chipset;
        this.cpu_socket = cpu_socket;
        this.fan_connectors = fan_connectors;
        this.form_factor = form_factor;
        this.max_ram_GB = max_ram_GB;
        this.max_ram_speed_MHz = max_ram_speed_MHz;
        this.nvme_slots = nvme_slots;
        this.pcie_ver = pcie_ver;
        this.pcie_x16_slots = pcie_x16_slots;
        this.pcie_x1_slots = pcie_x1_slots;
        this.pcie_x4_slots = pcie_x4_slots;
        this.ram_slots = ram_slots;
        this.ram_ver = ram_ver;
        this.sata_slots = sata_slots;
    }

    public MOBO() {
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getCpu_socket() {
        return cpu_socket;
    }

    public void setCpu_socket(String cpu_socket) {
        this.cpu_socket = cpu_socket;
    }

    public int getFan_connectors() {
        return fan_connectors;
    }

    public void setFan_connectors(int fan_connectors) {
        this.fan_connectors = fan_connectors;
    }

    public String getForm_factor() {
        return form_factor;
    }

    public void setForm_factor(String form_factor) {
        this.form_factor = form_factor;
    }

    public int getMax_ram_GB() {
        return max_ram_GB;
    }

    public void setMax_ram_GB(int max_ram_GB) {
        this.max_ram_GB = max_ram_GB;
    }

    public int getMax_ram_speed_MHz() {
        return max_ram_speed_MHz;
    }

    public void setMax_ram_speed_MHz(int max_ram_speed_MHz) {
        this.max_ram_speed_MHz = max_ram_speed_MHz;
    }

    public int getNvme_slots() {
        return nvme_slots;
    }

    public void setNvme_slots(int nvme_slots) {
        this.nvme_slots = nvme_slots;
    }

    public int getPcie_ver() {
        return pcie_ver;
    }

    public void setPcie_ver(int pcie_ver) {
        this.pcie_ver = pcie_ver;
    }

    public int getPcie_x16_slots() {
        return pcie_x16_slots;
    }

    public void setPcie_x16_slots(int pcie_x16_slots) {
        this.pcie_x16_slots = pcie_x16_slots;
    }

    public int getPcie_x1_slots() {
        return pcie_x1_slots;
    }

    public void setPcie_x1_slots(int pcie_x1_slots) {
        this.pcie_x1_slots = pcie_x1_slots;
    }

    public int getPcie_x4_slots() {
        return pcie_x4_slots;
    }

    public void setPcie_x4_slots(int pcie_x4_slots) {
        this.pcie_x4_slots = pcie_x4_slots;
    }

    public int getRam_slots() {
        return ram_slots;
    }

    public void setRam_slots(int ram_slots) {
        this.ram_slots = ram_slots;
    }

    public String getRam_ver() {
        return ram_ver;
    }

    public void setRam_ver(String ram_ver) {
        this.ram_ver = ram_ver;
    }

    public int getSata_slots() {
        return sata_slots;
    }

    public void setSata_slots(int sata_slots) {
        this.sata_slots = sata_slots;
    }
}
