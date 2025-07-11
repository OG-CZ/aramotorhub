package com.ogcreate.app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserService {

public boolean deleteUser(int userId) {
    String deleteCartItemsSQL = "DELETE FROM cart_item WHERE cart_id IN (SELECT cart_id FROM cart WHERE customer_id = ?)";
    String deleteCartsSQL = "DELETE FROM cart WHERE customer_id = ?";
    String deleteOrderItemsSQL = "DELETE FROM order_item WHERE order_id IN (SELECT order_id FROM `order` WHERE customer_id = ?)";
    String deleteOrdersSQL = "DELETE FROM `order` WHERE customer_id = ?";
    String deleteUserSQL = "DELETE FROM user WHERE user_id = ?";

    try (Connection conn = DatabaseConnection.connect()) {
        conn.setAutoCommit(false); 
        try (
            PreparedStatement deleteCartItemsStmt = conn.prepareStatement(deleteCartItemsSQL);
            PreparedStatement deleteCartsStmt = conn.prepareStatement(deleteCartsSQL);
            PreparedStatement deleteOrderItemsStmt = conn.prepareStatement(deleteOrderItemsSQL);
            PreparedStatement deleteOrdersStmt = conn.prepareStatement(deleteOrdersSQL);
            PreparedStatement deleteUserStmt = conn.prepareStatement(deleteUserSQL)
        ) {
            
            deleteCartItemsStmt.setInt(1, userId);
            deleteCartItemsStmt.executeUpdate();

           
            deleteCartsStmt.setInt(1, userId);
            deleteCartsStmt.executeUpdate();

            deleteOrderItemsStmt.setInt(1, userId);
            deleteOrderItemsStmt.executeUpdate();

            deleteOrdersStmt.setInt(1, userId);
            deleteOrdersStmt.executeUpdate();

            deleteUserStmt.setInt(1, userId);
            int rowsDeleted = deleteUserStmt.executeUpdate();

            conn.commit(); 
            return rowsDeleted > 0;
        } catch (Exception e) {
            conn.rollback(); 
            e.printStackTrace();
            return false;
        }

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean updateUser(User user) {
        String sql = "UPDATE user SET email = ?, password = ?, first_name = ?, last_name = ?, "
                + "address = ?, district = ?, barangay = ?, role = ? WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFirstName());
            stmt.setString(4, user.getLastName());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getDistrict());
            stmt.setString(7, user.getBarangay());
            stmt.setString(8, user.getRole());
            stmt.setInt(9, user.getUserId());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
