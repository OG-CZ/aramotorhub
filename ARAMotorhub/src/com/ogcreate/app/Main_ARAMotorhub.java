package com.ogcreate.app;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;


public class Main_ARAMotorhub extends Application {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        launch(args);
    }

        @Override
        public void start(Stage primaryStage) throws Exception {
            primaryStage.setTitle("ARA Motorhub");

            Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/main_login_ara.fxml"));
            Scene scene = new Scene(root, 938.32, 609.23);
            
            Font.loadFont(getClass().getResourceAsStream("/resources/fonts/SF-Pro.ttf"), 12);
            Font.loadFont(getClass().getResourceAsStream("/resources/fonts/SF-Pro-Display-Medium.otf"), 12);

            Font loadedFont = Font.loadFont(getClass().getResourceAsStream("/resources/fonts/SF-Pro.ttf"), 12);
            System.out.println("Loaded font family: " + loadedFont.getFamily());
            Font loadedMediumFont = Font.loadFont(getClass().getResourceAsStream("/resources/fonts/SF-Pro-Display-Medium.otf"), 12);
            System.out.println("Loaded medium font family: " + loadedMediumFont.getFamily());

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }
}

//            "vmArgs": "--module-path \"C:/Program Files/Java/javafx-sdk-21.0.7/lib\" --add-modules javafx.controls,javafx.fxml"
