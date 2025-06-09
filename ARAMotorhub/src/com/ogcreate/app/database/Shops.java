package com.ogcreate.app.database;

public class Shops {

    private int shopId;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    private String shopDistance;
    private String shopName;
    private String shopEmail;
    private String shopNumber;
    private String shopAddress;
    private String shopBarangay;

    public String getShopBarangay() {
        return shopBarangay;
    }

    public String getShopDistance() {
        return shopDistance;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopEmail() {
        return shopEmail;
    }

    public String getShopNumber() {
        return shopNumber;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    // Setters
    public void setShopBarangay(String shopBarangay) {
        this.shopBarangay = shopBarangay;
    }

    public void setShopDistance(String shopDistance) {
        this.shopDistance = shopDistance;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setShopEmail(String shopEmail) {
        this.shopEmail = shopEmail;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }
}
