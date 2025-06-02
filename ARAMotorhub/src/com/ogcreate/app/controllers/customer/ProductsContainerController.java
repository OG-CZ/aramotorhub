package com.ogcreate.app.controllers.customer;

import java.io.IOException;

import com.ogcreate.app.database.Products;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ProductsContainerController {

    private Products product;

    @FXML
    private ImageView productImage;

    @FXML
    private Label productName;

    @FXML
    private Label productPrice;

    @FXML
    private Label storeName;

    @FXML
    public void handleCartClick(ActionEvent event) {
        System.out.println("handleCartClick triggered");
    }

    public void setData(Products product) {
        this.product = product;
        productName.setText(product.getProductName());
        productPrice.setText(product.getProductPrice());
        storeName.setText(product.getStoreName());
    }

    @FXML
    void addToCartHandle(ActionEvent event) {
        System.out.println("Added to cart: " + product.getProductName());
    }

 @FXML
    void quickViewHandle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/customer/ProductsQuickView.fxml"));
            Parent root = loader.load();

            ProductsQuickViewController controller = loader.getController();
            controller.setProductData(product);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/assets/z_favicon.png")));
            stage.setTitle("ARA Motorhub: Quick View");
            stage.setScene(new Scene(root));
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL); // optional, for popup behavior
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
