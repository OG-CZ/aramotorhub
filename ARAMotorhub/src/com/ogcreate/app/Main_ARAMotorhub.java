package com.ogcreate.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class Main_ARAMotorhub extends Application {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ARA Motorhub");

        Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/main_login_ara.fxml"));
        primaryStage.setScene(new Scene(root, 938.32, 609.23));

        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

//            "vmArgs": "--module-path \"C:/Program Files/Java/javafx-sdk-21.0.7/lib\" --add-modules javafx.controls,javafx.fxml"
