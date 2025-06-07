package com.ogcreate.app.controllers.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

import com.ogcreate.app.database.User;
import com.ogcreate.app.database.UserSession;

public class EditProfileController {

    @FXML
    private TextField firstNameField, LastNameField, addressField, emailField, passwordField;

    @FXML
    private Button deleteAccButton;

    @FXML
    private SplitMenuButton barangayFieldMenu, roleFieldMenu;

    @FXML
    private Button editProfileButton;

    @FXML
    private Button logOutButton;

    private Alert alert;
    User currentUser = UserSession.getCurrentUser();

    @FXML
    public void initialize() {
        if (currentUser != null) {
            System.out.println("Welcome, " + currentUser.getFirstName());
        }

        firstNameField.setText(currentUser.getFirstName());
        LastNameField.setText(currentUser.getLastName());
        addressField.setText(currentUser.getAddress());
        emailField.setText(currentUser.getAddress());
        passwordField.setText(currentUser.getPassword());

       // roleFieldMenu.setText(currentUser.getRole());
       // barangayFieldMenu.setText(currentUser.getBarangay());

    }

    private void showAlert(String title, String message) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle(title);
        alert.setContentText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/resources/assets/z_favicon.png").toString()));
        alert.showAndWait();
    }

    @FXML
    void handleSaveButton(ActionEvent event) {
        System.out.println("#handleSaveButton triggered");

        showAlert("ARA Motorhub", "Do you want to save the changes to your profile information?");

        if (alert.getResult().equals(ButtonType.OK)) {
            System.out.println("confirmed to edit account triggered");
            return;
        }

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
