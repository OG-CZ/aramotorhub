package com.ogcreate.app.controllers.auth;

import java.io.IOException;

import com.ogcreate.app.database.AuthService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegisterStep2Controller {

    @FXML
    private TextField addressField;

    @FXML
    private SplitMenuButton districtField;
    private String selectedDistrict;

    @FXML
    private SplitMenuButton barangayField;
    private String selectedBarangay;

    @FXML
    private Button submitButton;

    private Alert alert;
    private AuthService authService;

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    private void showAlert(String title, String message) {
        alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setContentText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/resources/assets/z_favicon.png").toString()));
        alert.showAndWait();
    }

    @FXML
    void handleSubmitButtonClick(MouseEvent event) {
        System.out.println("submit clicked");

        if (addressField.getText().matches("^.{10,}$")) {
            showAlert("ARA Motorhub", "Invalid address");
            return;
        }


        showAlert("ARA Motorhub", "You may proceed to log in.");

        if (!alert.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/auth/Login.fxml"));
                Parent newRoot = loader.load();

                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Scene newScene = new Scene(newRoot);
                currentStage.setScene(newScene);
                currentStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return;

    }

    @FXML
    private void handleBarangaySelect(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        barangayField.setText(selectedItem.getText());
    }

    public String getSelectedDistrict() {
        return selectedDistrict;
    }

    public String getSelectedBarangay() {
        return selectedBarangay;
    }

    @FXML
    void handleSignInButtonClick(MouseEvent event) {
        System.out.println("Sign in go back");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/auth/Login.fxml"));
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
