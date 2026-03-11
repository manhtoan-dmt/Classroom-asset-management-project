package models;

public class Room {

    private int roomId;
    private String roomCode;
    private int capacity;
    private int buildingId;
    private int typeId;
    private int statusId;
   

    public Room() {
    }

    public Room(int roomId, String roomCode, int capacity,
                int buildingId, int typeId, int statusId) {
        this.roomId = roomId;
        this.roomCode = roomCode;
        this.capacity = capacity;
        this.buildingId = buildingId;
        this.typeId = typeId;
        this.statusId = statusId;
    }

    public int getRoomId() { return roomId; }

    public void setRoomId(int roomId) { this.roomId = roomId; }

    public String getRoomCode() { return roomCode; }

    public void setRoomCode(String roomCode) { this.roomCode = roomCode; }

    public int getCapacity() { return capacity; }

    public void setCapacity(int capacity) { this.capacity = capacity; }

    public int getBuildingId() { return buildingId; }

    public void setBuildingId(int buildingId) { this.buildingId = buildingId; }

    public int getTypeId() { return typeId; }

    public void setTypeId(int typeId) { this.typeId = typeId; }

    public int getStatusId() { return statusId; }

    public void setStatusId(int statusId) { this.statusId = statusId; }
}