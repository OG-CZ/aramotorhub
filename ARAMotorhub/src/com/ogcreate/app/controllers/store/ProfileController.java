package com.ogcreate.app.controllers.store;

import java.io.IOException;

import com.ogcreate.app.SettingsWindowHelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProfileController {

    @FXML
    void handleInventoryClick(ActionEvent event) {
        System.out.println("handleCartClick");

        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/store/Inventory.fxml"));
            Parent newRoot = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene newScene = new Scene(newRoot);
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
        e.printStackTrace();
        }        
    }

    @FXML
    void handleDashboardClick(ActionEvent event) {

        System.out.println("handleDashboardClick");


        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/store/Dashboard.fxml"));
            Parent newRoot = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene newScene = new Scene(newRoot);
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
        e.printStackTrace();
        }        

    }

    @FXML
    void handleHomeButton(ActionEvent event) {
        System.out.println("handleHomeButton");
    }

    @FXML
    void handleLogOutButton(ActionEvent event) {
        System.out.println("handleLogOutButton");
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SettingsWindowHelper.logout(currentStage);
    }

    @FXML
    void handleOpenSettings(ActionEvent event) {
        SettingsWindowHelper.openSettings((Node) event.getSource());
    }

    @FXML
    void handleProductsClick(ActionEvent event) {
        System.out.println("handleProductsClick");

    }

    @FXML
    void handleProfileClick(ActionEvent event) {
        System.out.println("handleProfileClick");
    }

    @FXML
    void handleShopsClick(ActionEvent event) {
        System.out.println("handleShopsClick");
    }

}
