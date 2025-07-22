package com.ogcz.app.controllers.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ogcz.app.database.DatabaseConnection;
import com.ogcz.app.database.Products;
import com.ogcz.app.database.Shops;
import com.ogcz.app.database.UserSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProductsQuickViewController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private BorderPane rootPane;

    @FXML
    private Spinner<Integer> spinnerQuantity;

    @FXML
    private Label storeLabel;

    private Products selectedProduct;

    @FXML
    void handleAddToCartClick(ActionEvent event) {
        if (selectedProduct == null)
            return;

        int productId = selectedProduct.getProductId();
        int userId = UserSession.getCurrentUser().getUserId();
        int quantity = spinnerQuantity.getValue();

        try (Connection conn = DatabaseConnection.connect()) {
            // Step 1: Get or create cart for user
            String findCartSql = "SELECT cart_id FROM cart WHERE customer_id = ? ORDER BY created_at DESC LIMIT 1";
            PreparedStatement findCartStmt = conn.prepareStatement(findCartSql);
            findCartStmt.setInt(1, userId);
            ResultSet rs = findCartStmt.executeQuery();

            int cartId;
            if (rs.next()) {
                cartId = rs.getInt("cart_id");
            } else {
                String createCartSql = "INSERT INTO cart (customer_id) VALUES (?)";
                PreparedStatement createCartStmt = conn.prepareStatement(createCartSql,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                createCartStmt.setInt(1, userId);
                createCartStmt.executeUpdate();
                ResultSet keys = createCartStmt.getGeneratedKeys();
                if (keys.next()) {
                    cartId = keys.getInt(1);
                } else {
                    throw new SQLException("❌ Failed to create new cart.");
                }
            }

            // Step 2: Add or update cart item
            String checkSql = "SELECT cart_item_id, quantity FROM cart_item WHERE cart_id = ? AND product_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, cartId);
            checkStmt.setInt(2, productId);
            ResultSet checkResult = checkStmt.executeQuery();

            if (checkResult.next()) {
                int existingItemId = checkResult.getInt("cart_item_id");
                int updatedQuantity = checkResult.getInt("quantity") + quantity;

                String updateSql = "UPDATE cart_item SET quantity = ? WHERE cart_item_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedQuantity);
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

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ARA Motorhub");
            alert.setContentText("Product added in the cart successfully!");
            alert.setHeaderText(null);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/assets/z_favicon.png").toString()));
            alert.showAndWait();

            System.out.println("Product added/updated in cart successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding to cart.");
        }
    }

 @FXML
void handleViewShop(ActionEvent event) {
    if (selectedProduct == null)
        return;

    int sellerId = selectedProduct.getSellerId();

    try (Connection conn = DatabaseConnection.connect()) {
        String sql = "SELECT first_name, last_name, email, address, barangay FROM user WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, sellerId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Shops shop = new Shops();
              shop.setShopId(sellerId);
            shop.setShopName(rs.getString("first_name") + " " + rs.getString("last_name"));
            shop.setShopEmail(rs.getString("email"));
            shop.setShopAddress(rs.getString("address"));
            shop.setShopBarangay(rs.getString("barangay"));
            Stage popupStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            popupStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/customer/ShopsQuickView.fxml"));
            Parent root = loader.load();

          ShopsQuickViewController controller = loader.getController();
controller.setShopDetails(shop);  

            Stage mainStage = UserSession.getMainStage();
            mainStage.setScene(new Scene(root));
            mainStage.show();

        } else {
            System.out.println("Seller not found with ID: " + sellerId);
        }

    } catch (IOException | SQLException e) {
        e.printStackTrace();
    }
}




    public void setProductData(Products product) {
        this.selectedProduct = product;
        nameLabel.setText(product.getProductName());
        priceLabel.setText(product.getProductPrice());
        storeLabel.setText(product.getStoreName());

        spinnerQuantity
                .setValueFactory(new javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1));
    }
}
