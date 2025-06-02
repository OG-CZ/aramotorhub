package com.ogcreate.app.controllers.customer;

import com.ogcreate.app.database.Products;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ProductsQuickViewController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private BorderPane rootPane;

    @FXML
    private Label storeLabel;

    @FXML
    void handleAddToCartClick(ActionEvent event) {

    }

    @FXML
    void handleViewShop(ActionEvent event) {

    }

    public void setProductData(Products product) {
        nameLabel.setText(product.getProductName());
        priceLabel.setText(product.getProductPrice());
        storeLabel.setText(product.getStoreName());
    }

}
