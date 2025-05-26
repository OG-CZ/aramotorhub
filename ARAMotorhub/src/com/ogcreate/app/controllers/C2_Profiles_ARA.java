package com.ogcreate.app.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.ogcreate.app.ARA_SettingsWindowHelper;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class C2_Profiles_ARA implements Initializable {

    @FXML
    private AnchorPane anchorPaneFooter;

    @FXML
    private ImageView araWhiteLogo;

    @FXML
    private ComboBox<String> categoryComboBox2;

     @Override
    public void initialize(URL location, ResourceBundle resources) {

        categoryComboBox2.setPromptText("Category");
        categoryComboBox2.getItems().addAll(
            "Engine Parts", "Suspension", "Wheels", "Oils", "Bolts", "Exterior"
        );
    }

    @FXML
    private void handleOpenSettings(javafx.event.ActionEvent event) {
        ARA_SettingsWindowHelper.openSettings((Node) event.getSource());
    }
}
