package com.ogcreate.app.controllers.store;

import com.ogcreate.app.database.Products;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ProductsContainerController {

    private boolean isShopMode = false;

    @FXML
    private ImageView productImage;

    @FXML
    private Label productName;

    @FXML
    private Label productPrice;

    @FXML
    private Label storeName;
    public void setData(Products product) {
        productName.setText(product.getProductName());
        productPrice.setText(product.getProductPrice());
        storeName.setText(product.getStoreName()); // full name of seller
    }

    public void setShopMode(boolean isShopMode) {
        this.isShopMode = isShopMode;
    }

    @FXML
    void addToCartHandle(ActionEvent event) {
        if (isShopMode) {
            showAlert("You can't add your own products to the cart.");
            return;
        }

    }

    @FXML
    void quickViewHandle(ActionEvent event) {
        if (isShopMode) {
            showAlert("Quick View is disabled in seller mode.");
            return;
        }

    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notice");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
