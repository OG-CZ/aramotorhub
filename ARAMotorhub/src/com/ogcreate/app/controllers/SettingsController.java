package com.ogcreate.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.List;

public class SettingsController {

    @FXML
    private Button deleteAccButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button editProfileButton;

    @FXML
    private StackPane contentPane; // This is where views will be swapped

    @FXML
    public void initialize() {

        
        List<Button> buttons = List.of(deleteAccButton, logOutButton, editProfileButton);

        for (Button btn : buttons) {
            btn.setOnMouseClicked(e -> {
                buttons.forEach(b -> b.getStyleClass().remove("selected"));
                btn.getStyleClass().add("selected");
            });
        }

        // Select editProfileButton style by default
        editProfileButton.getStyleClass().add("selected");

        // Load the default page after UI setup
        javafx.application.Platform.runLater(() -> {
            loadPage("/fxml/popout-setting-view/editprofile_ara.fxml");
        });
    }


    @FXML
    private void handleEditProfile(ActionEvent event) {
        loadPage("/resources/fxml/popout-setting-view/editprofile_ara.fxml");
    }

    @FXML
    private void handleDeleteAccount(ActionEvent event) {
        loadPage("/resources/fxml/popout-setting-view/deleteaccount_ara.fxml");
    }

    @FXML
    private void handleLogOut(ActionEvent event) {
        loadPage("/resources/fxml/popout-setting-view/logout_ara.fxml");
    }


    private void loadPage(String fxmlPath) {
        try {
            var resourceUrl = getClass().getResource(fxmlPath);
            if (resourceUrl == null) {
                System.err.println("FXML file NOT found at path: " + fxmlPath);
                return;
            }
            FXMLLoader loader = new FXMLLoader(resourceUrl);
            Parent view = loader.load();
            contentPane.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
}

}
