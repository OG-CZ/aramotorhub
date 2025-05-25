package com.ogcreate.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

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

        categoryComboBox.setPromptText("Select category");


        categoryComboBox.getItems().addAll(
            "Engine Parts",
            "Suspension",
            "Wheels",
            "Oils",
            "Bolts",
            "Exterior"
        );
    }
}
