package model;

public class RoomWithName {

    private int roomId;
    private String roomCode;
    private int capacity;
    private String buildingName;
    private String typeName;
    private String statusName;

    public RoomWithName() {
    }

    public RoomWithName(int roomId, String roomCode, int capacity, String buildingName, String typeName, String statusName) {
        this.roomId = roomId;
        this.roomCode = roomCode;
        this.capacity = capacity;
        this.buildingName = buildingName;
        this.typeName = typeName;
        this.statusName = statusName;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    
    

}
