/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package model;

/**
 *
 * @author THIN15
 */
public class Issue {

    private int issueId;
    private String roomCode;
    private String assetName;
    private String reportedBy;
    private String status;
    private String date;
    private String description;
    private String technician;
    private String issueStatus;

    public Issue() {
    }

    public Issue(int issueId, String roomCode, String assetName, String reportedBy, String status, String date, String description, String technician, String issueStatus) {
        this.issueId = issueId;
        this.roomCode = roomCode;
        this.assetName = assetName;
        this.reportedBy = reportedBy;
        this.status = status;
        this.date = date;
        this.description = description;
        this.technician = technician;
        this.issueStatus = issueStatus;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }
    
    
}