package com.ogcreate.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ogcreate.app.ARA_SettingsWindowHelper;


public class C1_Profiles_ARA implements Initializable {

    @FXML
    private Button boltsButton;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private Button exteriorBolts;

    @FXML
    private Button oilsButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button suspensionBolts;

    @FXML
    private Button wheelsButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        categoryComboBox.setPromptText("Category");
        categoryComboBox.getItems().addAll(
            "Engine Parts", "Suspension", "Wheels", "Oils", "Bolts", "Exterior"
        );
    }

    @FXML
    void handleImageClick(MouseEvent event) {
        System.out.println("Next home page clicked!");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/c2_profiles_ara.fxml"));
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
    private void handleOpenSettings(javafx.event.ActionEvent event) {
        ARA_SettingsWindowHelper.openSettings((Node) event.getSource());
    }

}
