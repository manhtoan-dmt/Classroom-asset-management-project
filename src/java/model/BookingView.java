/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package model;

/**
 *
 * @author THIN15
 */
public class BookingView {

    private String startTime;
    private String endTime;
    private String fullName;
    private String purpose;
    private String statusName;

    public BookingView() {
    }

    public BookingView(String startTime, String endTime, String fullName, String purpose, String statusName) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.fullName = fullName;
        this.purpose = purpose;
        this.statusName = statusName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    
    
}
