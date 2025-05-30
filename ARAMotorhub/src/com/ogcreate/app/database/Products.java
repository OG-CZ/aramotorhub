package com.ogcreate.app.database;

public class Products {

    private String productPrice;
    private String productName;
    private String storeName;

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }
    
    public String getStoreName() {
        return storeName;
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



}
