package com.ogcz.app.controllers.customer;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.ogcz.app.SettingsWindowHelper;
import com.ogcz.app.database.DatabaseConnection;
import com.ogcz.app.database.UserSession;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProfileController implements Initializable {

    @FXML
    private Label addressLabel;

    @FXML
    private Label barangayLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label firstLastNameLabel;

    @FXML
    private ComboBox<String> categoryComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUserProfile();
        loadCategoriesIntoComboBox();
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

    private void loadCategoriesIntoComboBox() {
        categoryComboBox.setPromptText("Category");

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT name FROM category");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                categoryComboBox.getItems().add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        categoryComboBox.setOnAction(event -> {
            String selectedCategory = categoryComboBox.getValue();
            if (selectedCategory != null && !selectedCategory.isEmpty()) {
                openCategoriesPage(selectedCategory);
            }
        });
    }

    private void openCategoriesPage(String category) {
        System.out.println("Opening category: " + category);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/customer/Categories.fxml"));
            Parent newRoot = loader.load();

            CategoriesController controller = loader.getController();
            controller.setSelectedCategory(category);

            Stage currentStage = (Stage) categoryComboBox.getScene().getWindow();
            currentStage.setScene(new Scene(newRoot));
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- Navigation Handlers ---

    @FXML
    void handleCartClick(ActionEvent event) {
        switchScene(event, "/fxml/customer/Cart.fxml");
    }

    @FXML
    void handleHomeButton(ActionEvent event) {
        switchScene(event, "/fxml/customer/HomeMain.fxml");
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
        switchScene(event, "/fxml/customer/Products.fxml");
    }

    @FXML
    void handleShopsClick(ActionEvent event) {
        switchScene(event, "/fxml/customer/Shops.fxml");
    }

    @FXML
    void handleFavoriteClick(ActionEvent event) {
        switchScene(event, "/fxml/customer/Favorite.fxml");
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
