package com.ogcreate.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class Settings_EditProfile {

    @FXML
    private Button deleteAccButton;

    @FXML
    private Button editProfileButton;

    @FXML
    private HBox handleDeleteAccount;

    @FXML
    private Button logOutButton;

    @FXML
    void handleSaveButton(ActionEvent  event) {
        System.out.println("#handleSaveButton triggered");
    }

    @FXML
    void handleDeleteAccount(ActionEvent event) {
        loadFXMLScene("/resources/fxml/popout-setting-view/deleteaccount_ara.fxml", event);
    }

    @FXML
    void handleEditProfile(ActionEvent event) {
        loadFXMLScene("/resources/fxml/popout-setting-view/editprofile_ara.fxml", event);
    }

    @FXML
    void handleLogOut(ActionEvent event) {
        loadFXMLScene("/resources/fxml/popout-setting-view/logout_ara.fxml", event);
    }

    private void loadFXMLScene(String fxmlPath, ActionEvent event) {
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
