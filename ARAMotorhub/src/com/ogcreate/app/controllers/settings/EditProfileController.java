package com.ogcreate.app.controllers.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class EditProfileController {

    @FXML
    private SplitMenuButton barangayField;

    @FXML
    private Button deleteAccButton;

    @FXML
    private SplitMenuButton districtField;

    @FXML
    private Button editProfileButton;

    @FXML
    private Button logOutButton;

    @FXML
    private BorderPane rootPane;


    @FXML
    void handleSaveButton(ActionEvent  event) {
        System.out.println("#handleSaveButton triggered");
    }



    // switching scene dont touch
    @FXML
    void switchToDeleteAccount(ActionEvent event) {
        loadFXMLScene("/resources/fxml/settings/DeleteAccount.fxml", event);
    }

    @FXML
    void switchToEditProfile(ActionEvent event) {
        loadFXMLScene("/resources/fxml/settings/EditProfile.fxml", event);
    }

    @FXML
    void switchToLogOut(ActionEvent event) {
        loadFXMLScene("/resources/fxml/settings/LogOut.fxml", event);
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
