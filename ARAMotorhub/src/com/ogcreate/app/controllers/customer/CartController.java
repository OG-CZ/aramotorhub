package com.ogcreate.app.controllers.customer;

import java.io.IOException;

import com.ogcreate.app.SettingsWindowHelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CartController {

    @FXML
    private VBox vboxCartProducts;


    @FXML
    void handleCheckoutClick(ActionEvent event) {
        System.out.println("handleCheckoutClick");
    }

    @FXML
    void handleHomeButton(ActionEvent event) {

        System.out.println("handleHomeButton triggered");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/customer/HomeMain.fxml"));
            Parent newRoot = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene newScene = new Scene(newRoot);
            currentStage.setScene(newScene);
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
    void handleShopsClick (ActionEvent event) {
     System.out.println("handleShopsClick triggered");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/customer/Shops.fxml"));
            Parent newRoot = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene newScene = new Scene(newRoot);
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleProductsClick(ActionEvent event) {
    System.out.println("handleProductsClick triggered");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/customer/Products.fxml"));
                Parent newRoot = loader.load();

                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene newScene = new Scene(newRoot);
                currentStage.setScene(newScene);
                currentStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    

    @FXML
    private void handleProfileClick(ActionEvent event) {
        System.out.println("handleProfileClick triggered");

            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/customer/Profile.fxml"));
            Parent newRoot = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene newScene = new Scene(newRoot);
            currentStage.setScene(newScene);
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
            Scene newScene = new Scene(newRoot);
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void initialize() {
        // Mock data for demonstration
        for (int i = 0; i < 5; i++) {
            addCartItem("Product " + (i + 1), "P" + (i + 1), "" + (100 + i * 50), "Store " + (i + 1), "Address " + (i + 1));
        }
    }

    private void addCartItem(String productName, String initials, String price, String storeName, String storeAddress) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/customer/CartProductContainer.fxml"));
            Node node = loader.load();

            CartProductContainerController controller = loader.getController();
            controller.setProductDetails(productName, initials, price, storeName, storeAddress);

            vboxCartProducts.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
