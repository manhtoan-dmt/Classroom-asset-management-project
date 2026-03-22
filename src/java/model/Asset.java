package model;

import java.time.LocalDate;

public class Asset {

    private int assetId;
    private String assetCode;
    private String assetName;
    private int categoryId;
    private Integer roomId;
    private String serialNumber;
    private LocalDate purchaseDate;
    private int statusId;
    private String statusName;
    

    public Asset() {
    }
    public Asset(int assetId, String assetCode, String assetName,
                 int categoryId, Integer roomId,
                 String serialNumber, LocalDate purchaseDate, int statusId) {
        this.assetId = assetId;
        this.assetCode = assetCode;
        this.assetName = assetName;
        this.categoryId = categoryId;
        this.roomId = roomId;
        this.serialNumber = serialNumber;
        this.purchaseDate = purchaseDate;
        this.statusId = statusId;
    }
    public Asset(int assetId, String assetCode, String assetName, int categoryId, Integer roomId, String serialNumber, LocalDate purchaseDate, int statusId, String statusName) {
        this.assetId = assetId;
        this.assetCode = assetCode;
        this.assetName = assetName;
        this.categoryId = categoryId;
        this.roomId = roomId;
        this.serialNumber = serialNumber;
        this.purchaseDate = purchaseDate;
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
