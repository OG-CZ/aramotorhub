package com.ogcreate.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.List;

public class SettingsController {

    @FXML
    private Button deleteAccButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button editProfileButton;

    @FXML
    public void initialize() {
        List<Button> buttons = List.of(deleteAccButton, logOutButton, editProfileButton);

        deleteAccButton.getStyleClass().add("selected");

        for (Button btn : buttons) {
            btn.setOnMouseClicked(e -> {
                buttons.forEach(b -> b.getStyleClass().remove("selected"));
                btn.getStyleClass().add("selected");
            });
        }
    }
}
