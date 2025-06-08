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
import com.ogcreate.app.database.UserService;
import com.ogcreate.app.database.UserSession;

public class EditProfileController {

    @FXML
    private TextField firstNameField, lastNameField, addressField, emailField, passwordField;

    @FXML
    private Button deleteAccButton;

    @FXML
    private SplitMenuButton barangayFieldMenu, roleFieldMenu;

    @FXML
    private Button editProfileButton;

    @FXML
    private Button logOutButton;

    private String selectedBarangay;


    private Alert alert;
    User currentUser = UserSession.getCurrentUser();

    @FXML
    public void initialize() {
        if (currentUser != null) {
            System.out.println("Welcome, " + currentUser.getFirstName());
        }

        firstNameField.setText(currentUser.getFirstName());
        lastNameField.setText(currentUser.getLastName());
        addressField.setText(currentUser.getAddress());
        emailField.setText(currentUser.getEmail());
        passwordField.setText(currentUser.getPassword());

        roleFieldMenu.setText(currentUser.getRole());
        barangayFieldMenu.setText(currentUser.getBarangay());

        // System.out.println(currentUser.getRole() + " " + currentUser.getBarangay() +
        // " " + currentUser.getDistrict());

        barangayFieldMenu.getItems().forEach(item -> {
        item.setOnAction(e -> {
            selectedBarangay = item.getText();
            barangayFieldMenu.setText(selectedBarangay);
        });
    });
        selectedBarangay = barangayFieldMenu.getText();


    }

    @FXML
    void handleSaveButton(ActionEvent event) {
        System.out.println("#handleSaveButton triggered");

        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ARA Motorhub");
       // alert.setHeaderText(null);
        alert.setContentText("Do you want to save the changes to your profile information?");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/resources/assets/z_favicon.png").toString()));

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.out.println("Confirmed to edit account triggered");

                currentUser.setFirstName(firstNameField.getText());
                currentUser.setLastName(lastNameField.getText());
                currentUser.setAddress(addressField.getText());
                currentUser.setEmail(emailField.getText());
                currentUser.setPassword(passwordField.getText());
                currentUser.setRole(roleFieldMenu.getText());
                currentUser.setBarangay(barangayFieldMenu.getText());

                UserService userService = new UserService();
                boolean success = userService.updateUser(currentUser);

                if (success) {
                    UserSession.setCurrentUser(currentUser);

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Your profile has been updated successfully!");
                    successAlert.showAndWait();
                } else {
                    Alert failAlert = new Alert(Alert.AlertType.ERROR);
                    failAlert.setTitle("Error");
                    failAlert.setHeaderText(null);
                    failAlert.setContentText("Failed to update profile. Please try again.");
                    failAlert.showAndWait();
                }
            } else {
                System.out.println("Edit cancelled.");
            }
        });
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
