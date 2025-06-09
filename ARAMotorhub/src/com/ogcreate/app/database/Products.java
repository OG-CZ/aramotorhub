package com.ogcreate.app.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Products> getProductsByCategory(String categoryName) {
    List<Products> productsList = new ArrayList<>();
    String sql = "SELECT DISTINCT p.product_id, p.name, p.price, p.seller_id, u.first_name, u.last_name " +
                 "FROM product p " +
                 "JOIN category c ON p.category_id = c.category_id " +
                 "JOIN user u ON p.seller_id = u.user_id " +
                 "WHERE c.name = ? AND p.status = 'AVAILABLE'";

    try (Connection conn = DatabaseConnection.connect();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, categoryName);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Products p = new Products();
            p.setProductId(rs.getInt("product_id"));
            p.setProductName(rs.getString("name"));
            p.setProductPrice(rs.getString("price"));
            String sellerName = rs.getString("first_name") + " " + rs.getString("last_name");
            p.setStoreName(sellerName.trim());
            p.setSellerId(rs.getInt("seller_id"));
            productsList.add(p);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return productsList;
}

public static List<Products> getProductsByCategory(String category, int limit) {
    List<Products> list = new ArrayList<>();

    String sql = """
        SELECT
            p.product_id,
            p.name AS product_name,
            p.price,
            u.first_name,
            u.last_name
        FROM product p
        JOIN user u ON p.seller_id = u.user_id
        JOIN category c ON p.category_id = c.category_id
        WHERE c.name = ?
        ORDER BY p.name ASC
        LIMIT ?
    """;

    try (Connection conn = DatabaseConnection.connect();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, category);
        stmt.setInt(2, limit);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Products p = new Products();
            p.setProductId(rs.getInt("product_id"));
            p.setProductName(rs.getString("product_name"));
            p.setProductPrice(rs.getString("price"));

            String storeName = rs.getString("first_name") + " " + rs.getString("last_name");
            p.setStoreName(storeName);

            list.add(p);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}


}
