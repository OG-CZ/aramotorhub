package com.ogcreate.app.controllers.store;

import com.ogcreate.app.database.Products;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class InventoryRowController {

    @FXML
    private CheckBox handleCheckBox;

    @FXML
    private Label labelCategory;

    @FXML
    private Label labelPrice;

    @FXML
    private Label labelProductName;

    @FXML
    private Label labelStatus;

    @FXML
    private Label labelStock;

    @FXML
    private StackPane rootStackPane;

    public void setProduct(Products product) {
        labelProductName.setText(product.getProductName());
        labelPrice.setText("₱" + product.getProductPrice());
        labelStock.setText("Stock: " + product.getStock());
        labelStatus.setText(product.getStatus());
        labelCategory.setText("Category ID: " + product.getCategoryId());
    }

    @FXML
    void handleDeleteProduct(ActionEvent event) {
        String productName = labelProductName.getText();

        try (var conn = com.ogcreate.app.database.DatabaseConnection.connect();
                var stmt = conn.prepareStatement("DELETE FROM product WHERE name = ?")) {

            stmt.setString(1, productName);
            int affected = stmt.executeUpdate();

            if (affected > 0) {
                javafx.scene.Parent parent = rootStackPane.getParent();

                if (parent instanceof javafx.scene.layout.Pane pane) {
                    pane.getChildren().remove(rootStackPane);
                    showAlert("Success", "Product \"" + productName + "\" deleted.", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Warning", "Could not remove row from parent. Parent is not a Pane.",
                            Alert.AlertType.WARNING);
                }

            } else {
                showAlert("Failed", "No product deleted.", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while deleting product.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void handleSetAvailable(ActionEvent event) {
        updateStatus("AVAILABLE");
    }

    @FXML
    void handleSetOutOfStock(ActionEvent event) {
        updateStatus("OUT_OF_STOCK");
    }

    private void updateStatus(String newStatus) {
        String productName = labelProductName.getText();

        try (var conn = com.ogcreate.app.database.DatabaseConnection.connect();
                var stmt = conn.prepareStatement("UPDATE product SET status = ? WHERE name = ?")) {

            stmt.setString(1, newStatus);
            stmt.setString(2, productName);
            int affected = stmt.executeUpdate();

            if (affected > 0) {
                labelStatus.setText(newStatus);
                showAlert("Success", "Product \"" + productName + "\" marked as " + newStatus + ".",
                        Alert.AlertType.INFORMATION);
            } else {
                showAlert("Failed", "No product updated.", Alert.AlertType.WARNING);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while updating status.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String x, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("ARA Motorhub");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
