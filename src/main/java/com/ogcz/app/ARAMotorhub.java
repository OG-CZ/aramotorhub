package com.ogcz.app;

import java.sql.Connection;

import com.ogcz.app.database.DatabaseConnection;
import com.ogcz.app.database.UserSession;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
// import javafx.scene.text.Font;


public class ARAMotorhub extends Application {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.connect();
        
        System.out.println("Hello, World!");
        launch(args);
        System.out.println("conn: " + conn);
    }

        @Override
        public void start(Stage primaryStage) throws Exception {

            UserSession.setMainStage(primaryStage);
            primaryStage.setTitle("ARA Motorhub");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/z_favicon.png")));

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/auth/Login.fxml"));
            Scene scene = new Scene(root, 938.32, 609.23);
            
            scene.getRoot().setStyle("-fx-font-smoothing-type: lcd;");

            //Font.loadFont(getClass().getResourceAsStream("/fonts/SF-Pro.ttf"), 12);
            //Font.loadFont(getClass().getResourceAsStream("/fonts/SF-Pro-Display-Medium.otf"), 12);

            /* 
            Font loadedFont = Font.loadFont(getClass().getResourceAsStream("/fonts/SF-Pro.ttf"), 12);
            System.out.println("Loaded font family: " + loadedFont.getFamily());
            Font loadedMediumFont = Font.loadFont(getClass().getResourceAsStream("/fonts/SF-Pro-Display-Medium.otf"), 12);
            System.out.println("Loaded medium font family: " + loadedMediumFont.getFamily());
            */
            
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }
}

//            "vmArgs": "--module-path \"C:/Program Files/Java/javafx-sdk-21.0.7/lib\" --add-modules javafx.controls,javafx.fxml"
