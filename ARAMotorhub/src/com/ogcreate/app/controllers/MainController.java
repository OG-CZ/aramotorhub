package com.ogcreate.app.controllers;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController {

    @FXML
    private ImageView ara_login_a;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    private boolean isFirstImage = true;
    private boolean isFirstButton = true;

    @FXML
    private ImageView pictureButton;

    @FXML
    private Pane dummyFocus;

    @FXML
    private Pane backgroundPane2;    
    @FXML
    private Pane backgroundPane3;
    @FXML
    private Pane backgroundPane4;

    @FXML
    private Button signupButton;

    @FXML
    private Button siginButton;

    // this is the signup button below 
    @FXML
    private void handleSignUpClick(MouseEvent event) {
        System.out.println("signup pressed");

        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/b1_signup_ara.fxml"));
        Parent newRoot = loader.load();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
        Scene newScene = new Scene(newRoot);
        currentStage.setScene(newScene);
        currentStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    // this is the sign in button
    @FXML
    private void handleSignInClick(MouseEvent event) {
        System.out.println("signin pressed");


        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/c1_profiles_ara.fxml"));
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
    private void handleBackgroundClick(MouseEvent event) {
        dummyFocus.requestFocus(); // This removes focus from email/password fields
    }

    // switch pictures
    @FXML
    private void handleImageClick(MouseEvent event) {

        {
            String imagePath = isFirstButton
                ? "resources/assets/ara_login_button_a.png"
                : "resources/assets/ara_login_button_b.png";

            Image image = new Image(getClass().getClassLoader().getResourceAsStream(imagePath));

            if (image.isError()) {
                System.out.println("Image failed to load: " + imagePath);
            } else {
                pictureButton.setImage(image);
            }

            isFirstButton = !isFirstButton;
        }

        System.out.println("Login Window: Picture Changed");
        FadeTransition fadeOut = new FadeTransition(Duration.millis(400), ara_login_a);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        fadeOut.setOnFinished(e -> {
            String imagePath = isFirstImage ? "resources/assets/ara_login_a.png" : "resources/assets/ara_login_b.png";
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(imagePath));

            if (image.isError()) {
                System.out.println("Image failed to load: " + imagePath);
            }

            ara_login_a.setImage(image);
            isFirstImage = !isFirstImage;

            FadeTransition fadeIn = new FadeTransition(Duration.millis(400), ara_login_a);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });

        fadeOut.play();
    }

        @FXML
        public void initialize() {

        Glow glow = new Glow(0.8);  
    
        signupButton.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> signupButton.setEffect(glow));
        signupButton.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> signupButton.setEffect(null));    

        dummyFocus.setFocusTraversable(true);
        backgroundPane2.setFocusTraversable(true); 
        backgroundPane3.setFocusTraversable(true); 
        backgroundPane4.setFocusTraversable(true); 
        Platform.runLater(dummyFocus::requestFocus);



            emailField.textProperty().addListener((obs, oldText, newText) -> {
                if (newText.isEmpty()) {
                    emailField.setStyle("-fx-border-color: gray; -fx-border-radius: 5;"); // default color when empty
                } else {
                    emailField.setStyle("-fx-border-color: #B7B710; -fx-border-radius: 5;"); // color when typing
                }
            });

            passwordField.textProperty().addListener((obs, oldText, newText) -> {
                if (newText.isEmpty()) {
                    passwordField.setStyle("-fx-border-color: gray; -fx-border-radius: 5;");
                } else {
                    passwordField.setStyle("-fx-border-color: #B7B710; -fx-border-radius: 5;");
            }
        });
    }
}
