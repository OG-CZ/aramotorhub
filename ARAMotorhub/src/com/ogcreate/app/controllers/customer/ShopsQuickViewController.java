package com.ogcreate.app.controllers.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    private Label labelCategory1;

    @FXML
    private Label labelCategory2;

    @FXML
    private Label labelCategory3;

    @FXML
    private Label labelCategory4;

    @FXML
    private Label labelCategory5;

    @FXML
    private Label labelCategory6;

    @FXML
    private Label labelCategory7;

    @FXML
    private Label labelCategory8;

    @FXML
    private Label labelCategoryItem1;

    @FXML
    private Label labelCategoryItem2;

    @FXML
    private Label labelCategoryItem3;

    @FXML
    private Label labelCategoryItem4;

    @FXML
    private Label labelCategoryItem5;

    @FXML
    private Label labelCategoryItem6;

    @FXML
    private Label labelCategoryItem7;

    @FXML
    private Label labelCategoryItem8;

    @FXML
    private Label sellerAddress;

    @FXML
    private Label sellerEmail;

    @FXML
    private Label sellerName;

    @FXML
    private Label sellerBarangay;

    @FXML
    void handleCategoryBolts(ActionEvent event) {

    }

    @FXML
    void handleCategoryElectrical(ActionEvent event) {

    }

    @FXML
    void handleCategoryEngine(ActionEvent event) {

    }

    @FXML
    void handleCategoryExterior(ActionEvent event) {

    }

    @FXML
    void handleCategoryOil(ActionEvent event) {

    }

    @FXML
    void handleCategorySuspension(ActionEvent event) {

    }

    @FXML
    void handleCategoryTransmission(ActionEvent event) {

    }

    @FXML
    void handleCategoryWheels(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        System.out.println("ShopsQuickViewController loaded!");
    }

    public void setShopDetails(Shops shop) {
        System.out.println("Shop passed to setShopDetails:");
        System.out.println("ID: " + shop.getShopId());
        System.out.println("Name: " + shop.getShopName());
        System.out.println("Email: " + shop.getShopEmail());
        System.out.println("Address: " + shop.getShopAddress());
        System.out.println("Barangay: " + shop.getShopBarangay());

        sellerName.setText(shop.getShopName() != null ? shop.getShopName() : "N/A");
        sellerEmail.setText(shop.getShopEmail() != null ? shop.getShopEmail() : "N/A");
        sellerAddress.setText(shop.getShopAddress() != null ? shop.getShopAddress() : "N/A");
        sellerBarangay.setText(shop.getShopBarangay() != null ? shop.getShopBarangay() : "N/A");

        if (shop.getShopId() > 0) {
            loadCategoryData(shop.getShopId());
        }
    }

    private void loadCategoryData(int sellerId) {
        String query = """
                SELECT c.name AS category_name, COUNT(p.product_id) AS product_count
                FROM category c
                JOIN product p ON c.category_id = p.category_id
                WHERE p.seller_id = ? AND p.status = 'AVAILABLE'
                GROUP BY c.category_id, c.name
                ORDER BY c.category_id ASC
                """;

        try (Connection conn = com.ogcreate.app.database.DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, sellerId);
            ResultSet rs = stmt.executeQuery();

            int index = 1;
            while (rs.next() && index <= 8) {
                String category = rs.getString("category_name");
                int productCount = rs.getInt("product_count");

                switch (index) {
                    case 1 -> {
                        labelCategory1.setText(category);
                        labelCategoryItem1.setText(productCount + " items");
                    }
                    case 2 -> {
                        labelCategory2.setText(category);
                        labelCategoryItem2.setText(productCount + " items");
                    }
                    case 3 -> {
                        labelCategory3.setText(category);
                        labelCategoryItem3.setText(productCount + " items");
                    }
                    case 4 -> {
                        labelCategory4.setText(category);
                        labelCategoryItem4.setText(productCount + " items");
                    }
                    case 5 -> {
                        labelCategory5.setText(category);
                        labelCategoryItem5.setText(productCount + " items");
                    }
                    case 6 -> {
                        labelCategory6.setText(category);
                        labelCategoryItem6.setText(productCount + " items");
                    }
                    case 7 -> {
                        labelCategory7.setText(category);
                        labelCategoryItem7.setText(productCount + " items");
                    }
                    case 8 -> {
                        labelCategory8.setText(category);
                        labelCategoryItem8.setText(productCount + " items");
                    }
                }

                index++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/customer/Products.fxml"));
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
