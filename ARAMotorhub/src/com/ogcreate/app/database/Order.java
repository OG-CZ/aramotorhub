package com.ogcreate.app.database;

import java.sql.Timestamp;

public class Order {
    private int orderId;
    private int customerId;
    private String address;
    private String status;
    private double totalPrice;
    private Timestamp createdAt;

    public Order(int orderId, int customerId, String address, String status, double totalPrice, Timestamp createdAt) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.address = address;
        this.status = status;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }

    public int getOrderId() { return orderId; }
    public int getCustomerId() { return customerId; }
    public String getAddress() { return address; }
    public String getStatus() { return status; }
    public double getTotalPrice() { return totalPrice; }
    public Timestamp getCreatedAt() { return createdAt; }
}
