package com.ogcreate.app.controllers.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import com.ogcreate.app.database.Shops;

public class ShopsCardLayoutController {

    @FXML
    private Label shopDistance;

    @FXML
    private Label shopName;

    @FXML
    private Button quickViewButton;

    private Shops currentShop; 

    public void setData(Shops shop) {
        this.currentShop = shop; 
        shopName.setText(shop.getShopName());
        shopDistance.setText(shop.getShopDistance());
    }

    @FXML
    public void initialize() {
        System.out.println("ShopsCardLayoutController initialized");
        if (quickViewButton == null) {
            System.out.println("quickViewButton is null!");
        } else {
            System.out.println("quickViewButton is NOT null!");
            quickViewButton.setOnAction(event -> {
                handleQuickView(event);
            });
        }
    }

    @FXML
    void handleQuickView(ActionEvent event) {
        System.out.println("handleQuickView triggered");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/customer/ShopsQuickView.fxml"));
            Parent root = loader.load();

            ShopsQuickViewController controller = loader.getController();
            controller.setShopDetails(currentShop);

            Stage stage = (Stage) quickViewButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Shop Quick View");

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
