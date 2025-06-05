package com.ogcreate.app.controllers.store;

import java.io.IOException;

import com.ogcreate.app.SettingsWindowHelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ProductsController {

    @FXML
    private GridPane productsContainer;

    @FXML
    private ScrollPane scrollPane;

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

    }

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
    void handleProfileClick(ActionEvent event) {

        System.out.println("handleProfileClick");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/store/Profile.fxml"));
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
    void handleShopsClick(ActionEvent event) {
        System.out.println("handleShopsClick");

         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/store/Shops.fxml"));
            Parent newRoot = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene newScene = new Scene(newRoot);
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
