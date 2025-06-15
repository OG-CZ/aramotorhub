package com.ogcreate.app.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Products {

    // --- Fields ---
    private int productId;
    private String productName;
    private String productPrice;
    private int stock;
    private int categoryId;
    private String categoryName;
    private int sellerId;
    private String storeName;
    private String status;
    private Timestamp createdAt;
    private String description;

    private String storeEmail;
    private String storeAddress;
    private String storeBarangay;

    // --- Getters ---
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public int getStock() {
        return stock;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getSellerId() {
        return sellerId;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getStoreEmail() {
        return storeEmail;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public String getStoreBarangay() {
        return storeBarangay;
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

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setStoreEmail(String storeEmail) {
        this.storeEmail = storeEmail;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public void setStoreBarangay(String storeBarangay) {
        this.storeBarangay = storeBarangay;
    }

    // --- Static Data Access Methods ---

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

    public static List<Products> getAllAvailableProducts() {
        List<Products> productList = new ArrayList<>();
        String sql = """
                    SELECT
                        p.product_id,
                        p.name AS product_name,
                        p.price,
                        p.seller_id,
                        p.category_id,
                        p.stock,
                        p.status,
                        p.created_at,
                        u.first_name,
                        u.last_name
                    FROM product p
                    JOIN user u ON p.seller_id = u.user_id
                    WHERE p.status = 'AVAILABLE'
                    ORDER BY p.name ASC
                """;

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Products p = new Products();
                p.setProductId(rs.getInt("product_id"));
                p.setProductName(rs.getString("product_name"));
                p.setProductPrice(rs.getString("price"));
                p.setSellerId(rs.getInt("seller_id"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setStock(rs.getInt("stock"));
                p.setStatus(rs.getString("status"));
                p.setCreatedAt(rs.getTimestamp("created_at"));

                String sellerName = rs.getString("first_name") + " " + rs.getString("last_name");
                p.setStoreName(sellerName.trim());

                productList.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
