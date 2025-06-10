package com.ogcreate.app.controllers.store;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ogcreate.app.SettingsWindowHelper;
import com.ogcreate.app.database.DatabaseConnection;
import com.ogcreate.app.database.User;
import com.ogcreate.app.database.UserSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProfileController {

    @FXML
    private Label firstLastNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label barangayLabel;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private Label labelCategory1, labelCategory2, labelCategory3, labelCategory4;
    @FXML
    private Label labelCategory5, labelCategory6, labelCategory7, labelCategory8;
    @FXML
    private Label labelCategoryItem1, labelCategoryItem2, labelCategoryItem3, labelCategoryItem4;
    @FXML
    private Label labelCategoryItem5, labelCategoryItem6, labelCategoryItem7, labelCategoryItem8;

    @FXML
    void handleCategoryBolts(ActionEvent event) {
        System.out.println("handleCategoryBolts");
    }

    @FXML
    void handleCategoryElectrical(ActionEvent event) {
        System.out.println("handleCategoryElectrical");
    }

    @FXML
    void handleCategoryExterior(ActionEvent event) {
        System.out.println("handleCategoryExterior");
    }

    @FXML
    void handleCategoryOil(ActionEvent event) {
        System.out.println("handleCategoryOil");
    }

    @FXML
    void handleCategorySuspension(ActionEvent event) {
        System.out.println("handleCategorySuspension");
    }

    @FXML
    void handleCategoryEngine(ActionEvent event) {
        System.out.println("handleCategoryEngine");
    }

    @FXML
    void handleCategoryTransmission(ActionEvent event) {
        System.out.println("handleCategoryTransmission");
    }

    @FXML
    void handleCategoryWheels(ActionEvent event) {
        System.out.println("handleCategoryWheels");
    }

    @FXML
    public void initialize() {
        loadCategoryData();
        setupCategoryComboBox();
        loadUserInfo();
    }

    private void loadUserInfo() {
        User currentUser = UserSession.getCurrentUser();
        if (currentUser == null) {
            System.err.println("No user is logged in.");
            return;
        }

        String fullName = currentUser.getFirstName() + " " + currentUser.getLastName();
        firstLastNameLabel.setText(fullName);
        emailLabel.setText(currentUser.getEmail());
        addressLabel.setText(currentUser.getAddress());
        barangayLabel.setText(currentUser.getBarangay());
    }

    private void setupCategoryComboBox() {
        String sql = "SELECT name FROM category ORDER BY name ASC";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                categoryComboBox.getItems().add(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        categoryComboBox.setOnAction(event -> {
            String selectedCategory = categoryComboBox.getValue();
            if (selectedCategory != null) {
                openCategoryView(selectedCategory);
            }
        });
    }

    private void openCategoryView(String categoryName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/store/Categories.fxml"));
            Parent root = loader.load();

            CategoriesController controller = loader.getController();
            controller.setSelectedCategory(categoryName);

            Stage stage = (Stage) categoryComboBox.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCategoryData() {
        String query = """
                SELECT c.name AS category_name, COUNT(p.product_id) AS product_count
                FROM category c
                JOIN product p ON c.category_id = p.category_id
                WHERE p.seller_id = ? AND p.status = 'AVAILABLE'
                GROUP BY c.category_id, c.name
                ORDER BY c.category_id ASC
                ;
                """;

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            User currentUser = UserSession.getCurrentUser();
            if (currentUser == null) {
                System.err.println("No user is logged in.");
                return;
            }

            int sellerId = currentUser.getUserId();
            stmt.setInt(1, sellerId);
            ResultSet rs = stmt.executeQuery();

            int index = 1;
            while (rs.next() && index <= 8) {
                String category = rs.getString("category_name");
                int productCount = rs.getInt("product_count");

                switch (index) {
                    case 1 -> {
                        labelCategory1.setText(category);
                        labelCategoryItem1.setText(productCount + " items");
                    }
                    case 2 -> {
                        labelCategory2.setText(category);
                        labelCategoryItem2.setText(productCount + " items");
                    }
                    case 3 -> {
                        labelCategory3.setText(category);
                        labelCategoryItem3.setText(productCount + " items");
                    }
                    case 4 -> {
                        labelCategory4.setText(category);
                        labelCategoryItem4.setText(productCount + " items");
                    }
                    case 5 -> {
                        labelCategory5.setText(category);
                        labelCategoryItem5.setText(productCount + " items");
                    }
                    case 6 -> {
                        labelCategory6.setText(category);
                        labelCategoryItem6.setText(productCount + " items");
                    }
                    case 7 -> {
                        labelCategory7.setText(category);
                        labelCategoryItem7.setText(productCount + " items");
                    }
                    case 8 -> {
                        labelCategory8.setText(category);
                        labelCategoryItem8.setText(productCount + " items");
                    }
                }
                index++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleDashboardClick(ActionEvent event) {
        loadScene(event, "/resources/fxml/store/Dashboard.fxml");
    }

    @FXML
    void handleHomeButton(ActionEvent event) {
        System.out.println("handleHomeButton");
        // Implement as needed
    }

    @FXML
    void handleInventoryClick(ActionEvent event) {
        loadScene(event, "/resources/fxml/store/Inventory.fxml");
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
        loadScene(event, "/resources/fxml/store/Products.fxml");
    }

    @FXML
    void handleProfileClick(ActionEvent event) {
        System.out.println("handleProfileClick");
    }

    @FXML
    void handleShopsClick(ActionEvent event) {
        loadScene(event, "/resources/fxml/store/Shops.fxml");
    }

    private void loadScene(ActionEvent event, String fxmlPath) {
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
