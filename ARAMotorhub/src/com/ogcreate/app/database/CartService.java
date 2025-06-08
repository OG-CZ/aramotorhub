package com.ogcreate.app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartService {

    public static boolean addToCart(int userId, int productId, int quantity) {
        try (Connection conn = DatabaseConnection.connect()) {
            int cartId = getOrCreateCartId(conn, userId);

            if (cartId == -1) return false;

            String checkSql = "SELECT cart_item_id, quantity FROM cart_item WHERE cart_id = ? AND product_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, cartId);
            checkStmt.setInt(2, productId);
            ResultSet checkResult = checkStmt.executeQuery();

            if (checkResult.next()) {
                // Product already in cart, update quantity
                int cartItemId = checkResult.getInt("cart_item_id");
                int existingQuantity = checkResult.getInt("quantity");
                int newQuantity = existingQuantity + quantity;

                String updateSql = "UPDATE cart_item SET quantity = ? WHERE cart_item_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, newQuantity);
                updateStmt.setInt(2, cartItemId);
                updateStmt.executeUpdate();
            } else {
                // Product not in cart, insert new row
                String insertSql = "INSERT INTO cart_item (cart_id, product_id, quantity) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setInt(1, cartId);
                insertStmt.setInt(2, productId);
                insertStmt.setInt(3, quantity);
                insertStmt.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static int getOrCreateCartId(Connection conn, int userId) throws SQLException {
        String findCartSql = "SELECT cart_id FROM cart WHERE customer_id = ? ORDER BY created_at DESC LIMIT 1";
        PreparedStatement findCartStmt = conn.prepareStatement(findCartSql);
        findCartStmt.setInt(1, userId);
        ResultSet rs = findCartStmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("cart_id");
        } else {
            String createCartSql = "INSERT INTO cart (customer_id) VALUES (?)";
            PreparedStatement createCartStmt = conn.prepareStatement(createCartSql, PreparedStatement.RETURN_GENERATED_KEYS);
            createCartStmt.setInt(1, userId);
            createCartStmt.executeUpdate();

            ResultSet keys = createCartStmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }
        }

        return -1; // Failed to create or find cart
    }
}
