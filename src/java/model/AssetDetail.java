package model;

import java.time.LocalDate;
public class AssetDetail {

    private int assetId;
    private String assetCode;
    private String assetName;
    private String roomName;
    private String categoryName;
    private String statusName;

    private LocalDate purchaseDate;
    private String supplier;
    private Integer price;

    public AssetDetail() {
    }

    public AssetDetail(int assetId, String assetCode, String assetName, String roomName, String categoryName, String statusName, LocalDate purchaseDate, String supplier, Integer price) {
        this.assetId = assetId;
        this.assetCode = assetCode;
        this.assetName = assetName;
        this.roomName = roomName;
        this.categoryName = categoryName;
        this.statusName = statusName;
        this.purchaseDate = purchaseDate;
        this.supplier = supplier;
        this.price = price;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    
    
    
    

}