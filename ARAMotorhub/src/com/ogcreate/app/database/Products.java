package com.ogcreate.app.database;

public class Products {

    private int productId;
    private String productPrice;
    private String productName;
    private String storeName;
    private int sellerId;

    // --- Getters ---
    public int getProductId() {
        return productId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getStoreName() {
        return storeName;
    }

    // --- Setters ---
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
}
