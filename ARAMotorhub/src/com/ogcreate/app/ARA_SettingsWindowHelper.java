package com.ogcreate.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ARA_SettingsWindowHelper {

    public static void openSettings(Node sourceNode) {

        System.out.println("Universal Settings Clicked!");
        try {
            FXMLLoader loader = new FXMLLoader(ARA_SettingsWindowHelper.class.getResource("/resources/fxml/universal_settings_ara.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Settings");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // Make it modal
            stage.initOwner(sourceNode.getScene().getWindow()); // Tie it to the caller window
            stage.showAndWait(); // Block until it's closed

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
