package com.ogcz.app.controllers.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ogcz.app.database.DatabaseConnection;
import com.ogcz.app.database.Products;
import com.ogcz.app.database.UserSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    public void setData(Products product) {
        this.product = product;
        productName.setText(product.getProductName());
        productPrice.setText(product.getProductPrice());
        storeName.setText(product.getStoreName());
        
    }

    @FXML
    void addToCartHandle(ActionEvent event) {
        System.out.println("🛒 Added to cart: " + product.getProductName());

        int productId = product.getProductId();
        int quantity = 1;

        int currentUserId = UserSession.getCurrentUser().getUserId();

        try (Connection conn = DatabaseConnection.connect()) {
            String findCartSql = "SELECT cart_id FROM cart WHERE customer_id = ? ORDER BY created_at DESC LIMIT 1";
            PreparedStatement findCartStmt = conn.prepareStatement(findCartSql);
            findCartStmt.setInt(1, currentUserId);
            ResultSet rs = findCartStmt.executeQuery();

            int cartId;
            if (rs.next()) {
                cartId = rs.getInt("cart_id");
            } else {
                String createCartSql = "INSERT INTO cart (customer_id) VALUES (?)";
                PreparedStatement createCartStmt = conn.prepareStatement(createCartSql, PreparedStatement.RETURN_GENERATED_KEYS);
                createCartStmt.setInt(1, currentUserId);
                createCartStmt.executeUpdate();
                ResultSet keys = createCartStmt.getGeneratedKeys();
                if (keys.next()) {
                    cartId = keys.getInt(1);
                } else {
                    throw new SQLException("Failed to create new cart.");
                }
            }

            String checkSql = "SELECT cart_item_id, quantity FROM cart_item WHERE cart_id = ? AND product_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, cartId);
            checkStmt.setInt(2, productId);
            ResultSet checkResult = checkStmt.executeQuery();

            if (checkResult.next()) {
                int existingItemId = checkResult.getInt("cart_item_id");
                int newQuantity = checkResult.getInt("quantity") + 1;
                String updateSql = "UPDATE cart_item SET quantity = ? WHERE cart_item_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, newQuantity);
                updateStmt.setInt(2, existingItemId);
                updateStmt.executeUpdate();
            } else {
                String insertSql = "INSERT INTO cart_item (cart_id, product_id, quantity) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setInt(1, cartId);
                insertStmt.setInt(2, productId);
                insertStmt.setInt(3, quantity);
                insertStmt.executeUpdate();
            }

            System.out.println("Product added/updated in cart successfully!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ARA Motorhub");
            alert.setContentText("Product added in the cart successfully!");
            alert.setHeaderText(null);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/assets/z_favicon.png").toString()));
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add product to cart.");
        }
    }

    @FXML
    void quickViewHandle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/customer/ProductsQuickView.fxml"));
            Parent root = loader.load();

            ProductsQuickViewController controller = loader.getController();
            controller.setProductData(product);

            Stage stage = new Stage();
            
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/z_favicon.png")));
            stage.setTitle("ARA Motorhub: Quick View");
            stage.setScene(new Scene(root));
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCartClick(ActionEvent event) {
        System.out.println("🛒 handleCartClick triggered");
    }
}
