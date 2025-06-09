package com.ogcreate.app.controllers.store;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

import com.ogcreate.app.SettingsWindowHelper;
import com.ogcreate.app.database.DatabaseConnection;
import com.ogcreate.app.database.Shops;
import com.ogcreate.app.database.UserSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShopsController implements Initializable {

    @FXML
    private VBox shopCardLayout;

    @FXML
    private ComboBox<String> categoryComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Shops> shops = fetchShopsFromDB();

        try {
            for (Shops shop : shops) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/store/ShopsCardLayout.fxml"));
                HBox cardBox = fxmlLoader.load();

                ShopsCardLayoutController controller = fxmlLoader.getController();
                controller.setData(shop);

                shopCardLayout.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        setupCategoryComboBox();
    }

    private void setupCategoryComboBox() {
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/store/Categories.fxml"));
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

    private List<Shops> fetchShopsFromDB() {
        List<Shops> shopsList = new ArrayList<>();
        int currentUserId = UserSession.getCurrentUser().getUserId();

        String sql = "SELECT user_id, first_name, last_name, email, address, barangay " +
                     "FROM user WHERE role = 'SELLER' AND user_id != ? " +
                     "AND first_name IS NOT NULL AND last_name IS NOT NULL " +
                     "AND first_name <> '' AND last_name <> ''";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, currentUserId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Shops shop = new Shops();
                shop.setShopName(rs.getString("first_name") + " " + rs.getString("last_name"));
                shop.setShopEmail(rs.getString("email"));
                shop.setShopAddress(rs.getString("address"));
                shop.setShopBarangay(rs.getString("barangay"));
                shop.setShopDistance("N/A");
                shopsList.add(shop);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shopsList;
    }

    // NAVIGATION

    @FXML
    void handleHomeButton(ActionEvent event) {
        loadScene("/resources/fxml/store/Profile.fxml", event);
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
        loadScene("/resources/fxml/store/Products.fxml", event);
    }

    @FXML
    void handleProfileClick(ActionEvent event) {
        loadScene("/resources/fxml/store/Profile.fxml", event);
    }

    @FXML
    void handleDashboardClick(ActionEvent event) {
        loadScene("/resources/fxml/store/Dashboard.fxml", event);
    }

    @FXML
    void handleInventoryClick(ActionEvent event) {
        loadScene("/resources/fxml/store/Inventory.fxml", event);
    }

    private void loadScene(String fxmlPath, ActionEvent event) {
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
