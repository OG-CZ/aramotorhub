package com.ogcreate.app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserService {

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
