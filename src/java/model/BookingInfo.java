package model;

public class BookingInfo {

    private String roomCode;
    private String date;
    private String time;
    private String status;

    public BookingInfo() {
    }

    public BookingInfo(String roomCode, String date, String time, String status) {
        this.roomCode = roomCode;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}