package com.ogcreate.app.controllers.store;

import com.ogcreate.app.SettingsWindowHelper;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InventorySelectController {

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private VBox inventoryContainer;

    private String selectedCategory;

    public void setCategory(String category) {
        this.selectedCategory = category;
        categoryComboBox.setValue(category); // Optional: show in ComboBox
        loadInventoryByCategory();
    }

    private void loadInventoryByCategory() {
        System.out.println("Loading inventory for: " + selectedCategory.toString());
    }
 
    @FXML
    void handleDashboardClick(ActionEvent event) {
        loadScene(event, "/resources/fxml/store/Dashboard.fxml");
    }

    @FXML
    void handleHomeButton(ActionEvent event) {
        loadScene(event, "/resources/fxml/store/Profile.fxml");
    }

    @FXML
    void handleProductsClick(ActionEvent event) {
        loadScene(event, "/resources/fxml/store/Products.fxml");
    }

    @FXML
    void handleProfileClick(ActionEvent event) {
        loadScene(event, "/resources/fxml/store/Profile.fxml");
    }

    @FXML
    void handleShopsClick(ActionEvent event) {
        loadScene(event, "/resources/fxml/store/Shops.fxml");
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



    private void loadScene(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
