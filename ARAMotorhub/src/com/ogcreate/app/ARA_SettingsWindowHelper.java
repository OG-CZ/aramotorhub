package com.ogcreate.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Node;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ARA_SettingsWindowHelper {

    public static void openSettings(Node sourceNode) {

        System.out.println("Universal Settings Clicked!");
        try {
            FXMLLoader loader = new FXMLLoader(ARA_SettingsWindowHelper.class.getResource("/resources/fxml/popout-setting-view/editprofile_ara.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.getIcons().add(new Image(ARA_SettingsWindowHelper.class.getResourceAsStream("/resources/assets/z_favicon.png")));

            stage.setResizable(false);
            stage.setTitle("ARA Motorhub: Settings @_ogcreate!");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // Make it modal
            stage.initOwner(sourceNode.getScene().getWindow()); // Tie it to the caller window
            stage.showAndWait(); // Block until it's closed

        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }

    public static void logout(Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(ARA_SettingsWindowHelper.class.getResource("/resources/fxml/main_login_ara.fxml"));
            Parent root = loader.load();

            Stage primaryStage = currentStage.getOwner() instanceof Stage ? (Stage) currentStage.getOwner() : currentStage;

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

            if (currentStage != primaryStage) {
                currentStage.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
