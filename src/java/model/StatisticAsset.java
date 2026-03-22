/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package model;

/**
 *
 * @author THIN15
 */
public class StatisticAsset {

    private int available;
    private int broken;
    private int disposed;
    private int inUse ;
    private int lost;
    private int maintenance;

    public StatisticAsset() {
    }

    public StatisticAsset(int available, int broken, int disposed, int inUse, int lomst, int aintenance) {
        this.available = available;
        this.broken = broken;
        this.disposed = disposed;
        this.inUse = inUse;
        this.lost = lost;
        this.maintenance = aintenance;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getBroken() {
        return broken;
    }

    public void setBroken(int broken) {
        this.broken = broken;
    }

    public int getDisposed() {
        return disposed;
    }

    public void setDisposed(int disposed) {
        this.disposed = disposed;
    }

    public int getInUse() {
        return inUse;
    }

    public void setInUse(int inUse) {
        this.inUse = inUse;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(int maintenance) {
        this.maintenance = maintenance;
    }
    
    
}
