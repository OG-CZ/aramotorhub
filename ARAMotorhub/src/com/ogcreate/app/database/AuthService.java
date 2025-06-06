package com.ogcreate.app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AuthService {

    private String firstName, lastName, password, email;


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String address, district, barangay, role;




 



    public String login(String email, String password) {
        String role = null;

        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "SELECT user_id FROM user WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String userId = rs.getString("user_id");

                if (userId.endsWith("CST")) {
                    role = "customer";
                } else if (userId.endsWith("SLR")) {
                    role = "store";
                } else if (userId.endsWith("ADM")) {
                    role = "admin";
                }
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return role;
    }

    public boolean Submit(boolean value) {

        return value == true;
    }

}
