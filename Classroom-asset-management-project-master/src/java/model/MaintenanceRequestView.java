package model;

public class MaintenanceRequestView {
    private int roomId;
    private int requestId;
    private String assetName;
    private String roomName;
    private String reportedBy;
    private String description;
    private String status;
    private String createdAt;
    private String completedAt;

    public MaintenanceRequestView() {
    }

    public MaintenanceRequestView(int requestId, String assetName, String roomName,
            String reportedBy, String description, String status,
            String createdAt, String completedAt) {

        this.requestId = requestId;
        this.assetName = assetName;
        this.roomName = roomName;
        this.reportedBy = reportedBy;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getAssetName() {
        return assetName;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }


}
