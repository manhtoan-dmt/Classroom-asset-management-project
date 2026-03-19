package model;

public class RoomStatistics {

    private int totalRooms;
    private int availableRooms;
    private int reservedRooms;
    private int roomsInUse;
    private int maintenanceRooms;

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public int getReservedRooms() {
        return reservedRooms;
    }

    public void setReservedRooms(int reservedRooms) {
        this.reservedRooms = reservedRooms;
    }

    public int getRoomsInUse() {
        return roomsInUse;
    }

    public void setRoomsInUse(int roomsInUse) {
        this.roomsInUse = roomsInUse;
    }

    public int getMaintenanceRooms() {
        return maintenanceRooms;
    }

    public void setMaintenanceRooms(int maintenanceRooms) {
        this.maintenanceRooms = maintenanceRooms;
    }
}