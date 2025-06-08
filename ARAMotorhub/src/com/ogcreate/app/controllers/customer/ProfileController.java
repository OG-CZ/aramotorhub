package com.ogcreate.app.controllers.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ogcreate.app.SettingsWindowHelper;
import com.ogcreate.app.database.DatabaseConnection;
import com.ogcreate.app.database.UserSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProfileController {

    @FXML
    private Label addressLabel;

    @FXML
    private Label barangayLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label firstLastNameLabel;

    @FXML
    public void initialize() {
        loadUserProfile();
    }

    private void loadUserProfile() {
        int userId = UserSession.getCurrentUser().getUserId();

        String sql = "SELECT first_name, last_name, email, address, barangay FROM user WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String barangay = rs.getString("barangay");

                firstLastNameLabel.setText(fullName);
                emailLabel.setText(email);
                addressLabel.setText(address);
                barangayLabel.setText(barangay);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- NAVIGATION HANDLERS BELOW ---

    @FXML
    void handleCartClick(ActionEvent event) {
        switchScene(event, "/resources/fxml/customer/Cart.fxml");
    }

    @FXML
    void handleHomeButton(ActionEvent event) {
        switchScene(event, "/resources/fxml/customer/HomeMain.fxml");
    }

    @FXML
    void handleLogOutButton(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SettingsWindowHelper.logout(currentStage);
    }

    @FXML
    void handleOpenSettings(ActionEvent event) {
        SettingsWindowHelper.openSettings((Node) event.getSource());
    }

    @FXML
    void handleProductsClick(ActionEvent event) {
        switchScene(event, "/resources/fxml/customer/Products.fxml");
    }

    @FXML
    void handleShopsClick(ActionEvent event) {
        switchScene(event, "/resources/fxml/customer/Shops.fxml");
    }

    @FXML
    void handleFavoriteClick(ActionEvent event) {
        switchScene(event, "/resources/fxml/customer/Favorite.fxml");
    }

    @FXML
    private void handleProfileClick(ActionEvent event) {
        System.out.println("handleProfileClick triggered");
    }

    private void switchScene(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent newRoot = loader.load();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(newRoot));
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
