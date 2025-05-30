package com.ogcreate.app.controllers.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.ogcreate.app.SettingsWindowHelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import com.ogcreate.app.database.Shops;

public class ShopsController implements Initializable {

    @FXML
    private VBox shopCardLayout;
    private List<Shops> nearestShop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nearestShop = new ArrayList<>(nearestShop());
    
        try {
                for (int i = 0; i < nearestShop().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/resources/fxml/customer/ShopsCardLayout.fxml"));

                HBox cardBox = fxmlLoader.load();
                ShopsCardLayoutController shopCardLayoutController = fxmlLoader.getController();
                shopCardLayoutController.setData(nearestShop.get(i));
                shopCardLayout.getChildren().add(cardBox);
            }
        } catch (IOException e) { 
            e.printStackTrace();
        }
    }
                    
    private List<Shops> nearestShop() {
        List<Shops> ls = new ArrayList<>();

        Shops shop1 = new Shops();
        shop1.setShopName("Store 1");
        shop1.setShopDistance("1 km");
        ls.add(shop1);

        Shops shop2 = new Shops();
        shop2.setShopName("Store 2");
        shop2.setShopDistance("2 km");
        ls.add(shop2);


        Shops shop3 = new Shops();
        shop3.setShopName("Store 3");
        shop3.setShopDistance("3 km");
        ls.add(shop3);

        Shops Shop4 = new Shops();
        Shop4.setShopName("Store 4");
        Shop4.setShopDistance("4 km");
        ls.add(Shop4);

        Shops shop5 = new Shops();
        shop5.setShopName("Store 5");
        shop5.setShopDistance("5 km");
        ls.add(shop5);

        Shops shop6 = new Shops();
        shop6.setShopName("Store 6");
        shop6.setShopDistance("6 km");
        ls.add(shop6);

        return ls;
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
    private void handleOpenSettings(javafx.event.ActionEvent event) {
        SettingsWindowHelper.openSettings((Node) event.getSource());
    }

    @FXML
    private void handleLogOutButton(ActionEvent event) {
        System.out.println("Logout clicked");
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SettingsWindowHelper.logout(currentStage);
    }

    @FXML
    private void handleProductsClick(ActionEvent event) {
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


    @FXML
    private void handleCartClick(ActionEvent event) {
        System.out.println("handleCartClick triggered");
    }
}




