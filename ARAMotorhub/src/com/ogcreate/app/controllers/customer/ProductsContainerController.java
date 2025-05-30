package com.ogcreate.app.controllers.customer;

import com.ogcreate.app.database.Products;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ProductsContainerController {

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
        storeName.setText(product.getStoreName());
    }

    @FXML
    void addToCartHandle(ActionEvent event) {

    }

    @FXML
    void quickViewHandle(ActionEvent event) {

    }

}
