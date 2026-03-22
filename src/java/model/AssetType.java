/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package model;

/**
 *
 * @author THIN15
 */
public class AssetType {

    private int assetTypeId;
    private String assetTypeName;

    public AssetType() {
    }

    public AssetType(int assetTypeId, String assetTypeName) {
        this.assetTypeId = assetTypeId;
        this.assetTypeName = assetTypeName;
    }

    public int getAssetTypeId() {
        return assetTypeId;
    }

    public void setAssetTypeId(int assetTypeId) {
        this.assetTypeId = assetTypeId;
    }

    public String getAssetTypeName() {
        return assetTypeName;
    }

    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName;
    }
    
    
    
}
