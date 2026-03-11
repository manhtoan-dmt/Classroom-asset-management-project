package models;

import java.time.LocalDateTime;

public class MaintenanceRequest {

    private int requestId;
    private int assetId;
    private int reportedBy;
    private Integer technicianId;
    private String description;
    private int statusId;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    public MaintenanceRequest() {
    }

    public MaintenanceRequest(int requestId, int assetId, int reportedBy,
            Integer technicianId, String description,
            int statusId, LocalDateTime createdAt,
            LocalDateTime completedAt) {

        this.requestId = requestId;
        this.assetId = assetId;
        this.reportedBy = reportedBy;
        this.technicianId = technicianId;
        this.description = description;
        this.statusId = statusId;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public int getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(int reportedBy) {
        this.reportedBy = reportedBy;
    }

    public Integer getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(Integer technicianId) {
        this.technicianId = technicianId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
