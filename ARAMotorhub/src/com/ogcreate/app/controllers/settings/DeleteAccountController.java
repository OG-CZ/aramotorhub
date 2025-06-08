package com.ogcreate.app.controllers.settings;

import java.io.IOException;

import com.ogcreate.app.SettingsWindowHelper;
import com.ogcreate.app.database.User;
import com.ogcreate.app.database.UserService;
import com.ogcreate.app.database.UserSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DeleteAccountController {

    @FXML
    private Button deleteAccButton;

    @FXML
    private Button editProfileButton;

    @FXML
    private HBox handleDeleteAccount;

    @FXML
    private Button logOutButton;

    private Alert alert;
    User currentUser = UserSession.getCurrentUser();

    void showAlert(String title, String message) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/resources/assets/z_favicon.png").toString()));
        alert.showAndWait();
    }

    @FXML
    void handleDelAccButton(ActionEvent event) {
        if (currentUser == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("ARA Motorhub");
            errorAlert.setContentText("No logged in user found. Please log in again.");
            errorAlert.showAndWait();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("ARA Motorhub");
        confirmAlert.setContentText("Pressing OK will permanently delete your account and all associated data");
        Stage stage = (Stage) confirmAlert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/resources/assets/z_favicon.png").toString()));

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                UserService userService = new UserService();
                boolean deleted = userService.deleteUser(currentUser.getUserId());

                if (deleted) {

                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("ARA Motorhub");
                    infoAlert.setContentText("Account deleted successfully.");
                    infoAlert.setHeaderText(null);
                    Stage infoStage = (Stage) infoAlert.getDialogPane().getScene().getWindow();
                    infoStage.getIcons()
                            .add(new Image(this.getClass().getResource("/resources/assets/z_favicon.png").toString()));
                    infoAlert.showAndWait();
                    SettingsWindowHelper.logout((Stage) ((Node) event.getSource()).getScene().getWindow());
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("ARA Motorhub");
                    errorAlert.setContentText("Failed to delete account. Please try again.");
                    errorAlert.setHeaderText(null);
                    Stage errorStage = (Stage) errorAlert.getDialogPane().getScene().getWindow();
                    errorStage.getIcons()
                            .add(new Image(this.getClass().getResource("/resources/assets/z_favicon.png").toString()));
                    errorAlert.showAndWait();
                }
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
