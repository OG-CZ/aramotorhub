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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegisterStep1Controller {

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button signInButton;

    @FXML
    private PasswordField passwordField;



   
    @FXML
    void handleNextButtonClick(ActionEvent event) {
        System.out.println("Next Clicked");

        if (emailField.getText().isEmpty() || firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            showAlert("ARA Motorhub", "Please fill in all blanks");
            return;
        }

        if (!firstNameField.getText().matches("^[a-zA-Z0-9-]{2,}\\b$")) {
            showAlert("ARA Motorhub", "Invalid first name");
            return;
        }

        if (!lastNameField.getText().matches("^[a-zA-Z0-9-]{2,}\\b$")) {
            showAlert("ARA Motorhub", "Invalid last name");
            return;
        }
    
      
        if (!(emailField.getText().contains("@") && emailField.getText().endsWith(".com"))) {
            showAlert("ARA Motorhub", "Email must contain '@' and end with '.com'");
            return;
        }

        if (!passwordField.getText().matches("^.{5,}$")) {
            showAlert("ARA Motorhub", "Password must be at least 5 characters.");
            return;
        }

        
        AuthService authService = new AuthService();
        authService.setFirstName(firstNameField.getText());
        authService.setLastName(lastNameField.getText());
        authService.setEmail(emailField.getText());
        authService.setPassword(passwordField.getText());

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/auth/RegisterStep2.fxml"));
            Parent newRoot = loader.load();

                    // Get Step 2 controller instance
            RegisterStep2Controller step2Controller = loader.getController();

            // Pass the AuthService object to Step 2 controller
            step2Controller.setAuthService(authService);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene newScene = new Scene(newRoot);
            currentStage.setScene(newScene);
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void showAlert(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle(title);
            alert.setContentText(message);

            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/resources/assets/z_favicon.png").toString()));
            alert.showAndWait();
        }

}
