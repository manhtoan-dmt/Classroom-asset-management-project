package model;

import java.time.LocalDateTime;

public class AssetStatusHistory {

    private int historyId;
    private int assetId;
    private String oldStatus;
    private String newStatus;
    private int changedBy;
    private String reason;
    private LocalDateTime changedAt;

    public AssetStatusHistory() {
    }

    public AssetStatusHistory(int historyId, int assetId,
                              String oldStatus, String newStatus,
                              int changedBy, String reason,
                              LocalDateTime changedAt) {

        this.historyId = historyId;
        this.assetId = assetId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.changedBy = changedBy;
        this.reason = reason;
        this.changedAt = changedAt;
    }

    public int getHistoryId() { return historyId; }

    public void setHistoryId(int historyId) { this.historyId = historyId; }

    public int getAssetId() { return assetId; }

    public void setAssetId(int assetId) { this.assetId = assetId; }

    public String getOldStatus() { return oldStatus; }

    public void setOldStatus(String oldStatus) { this.oldStatus = oldStatus; }

    public String getNewStatus() { return newStatus; }

    public void setNewStatus(String newStatus) { this.newStatus = newStatus; }

    public int getChangedBy() { return changedBy; }

    public void setChangedBy(int changedBy) { this.changedBy = changedBy; }

    public String getReason() { return reason; }

    public void setReason(String reason) { this.reason = reason; }

    public LocalDateTime getChangedAt() { return changedAt; }

    public void setChangedAt(LocalDateTime changedAt) { this.changedAt = changedAt; }
}