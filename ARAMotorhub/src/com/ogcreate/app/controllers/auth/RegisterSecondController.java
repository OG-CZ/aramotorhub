package com.ogcreate.app.controllers.auth;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegisterSecondController {

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

    @FXML
    void handleSubmitButtonClick(MouseEvent event) {
        System.out.println("submit clicked");
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
}


