package model;

public class Building {

    private int buildingId;
    private String buildingName;

    public Building() {}

    public Building(int buildingId, String buildingName) {
        this.buildingId = buildingId;
        this.buildingName = buildingName;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    
}