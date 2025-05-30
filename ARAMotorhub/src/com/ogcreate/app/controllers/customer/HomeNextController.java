package com.ogcreate.app.controllers.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ogcreate.app.SettingsWindowHelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeNextController implements Initializable {

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
        SettingsWindowHelper.openSettings((Node) event.getSource());
    }

    @FXML
    private void handleGoBackButton(ActionEvent event) {
        System.out.println("handleGoBackButton triggered");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/customer/HomeMain.fxml"));
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
    public void handleShopClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/customer/Shops.fxml"));
            Parent newRoot = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene newScene = new Scene(newRoot);
            currentStage.setScene(newScene);
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
