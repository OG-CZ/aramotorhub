package com.ogcreate.app.controllers.customer;

import java.io.IOException;

import com.ogcreate.app.SettingsWindowHelper;
import com.ogcreate.app.database.Shops;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ShopsQuickViewController {

    @FXML
    private Label sellerAddress;

    @FXML
    private Label sellerEmail;

    @FXML
    private Label sellerName;

    @FXML
    private Label sellerBarangay;

    public void setShopDetails(Shops shop) {
        sellerName.setText(shop.getShopName() != null ? shop.getShopName() : "N/A");
        sellerEmail.setText(shop.getShopEmail() != null ? shop.getShopEmail() : "N/A");
        sellerAddress.setText(shop.getShopAddress() != null ? shop.getShopAddress() : "N/A");
        sellerBarangay.setText(shop.getShopBarangay() != null ? shop.getShopBarangay() : "N/A");
    }

    @FXML
    void handleCartClick(ActionEvent event) {
        System.out.println("handleCartClick triggered");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/customer/Cart.fxml"));
            Parent newRoot = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(newRoot));
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleFavoriteClick(ActionEvent event) {
        System.out.println("handleFavoriteClick triggered");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/customer/Favorite.fxml"));
            Parent newRoot = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(newRoot));
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleHomeButton(ActionEvent event) {
        System.out.println("handleHomeButton triggered");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/customer/HomeMain.fxml"));
            Parent newRoot = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(newRoot));
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleLogOutButton(ActionEvent event) {
        System.out.println("Logout clicked");
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SettingsWindowHelper.logout(currentStage);
    }

    @FXML
    void handleOpenSettings(ActionEvent event) {
        SettingsWindowHelper.openSettings((Node) event.getSource());
    }

    @FXML
    void handleProductsClick(ActionEvent event) {
        System.out.println("handleProductsClick triggered");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/st0re/Products.fxml"));
            Parent newRoot = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(newRoot));
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleShopsClick(ActionEvent event) {
        System.out.println("handleShopsClick triggered");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/customer/Shops.fxml"));
            Parent newRoot = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(newRoot));
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
