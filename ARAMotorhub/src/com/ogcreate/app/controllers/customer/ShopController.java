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


import com.ogcreate.app.database.Shop;

public class ShopController implements Initializable {

    @FXML
    private VBox shopCardLayout;
    private List<Shop> nearestShop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nearestShop = new ArrayList<>(nearestShop());
    
        try {
                for (int i = 0; i < nearestShop().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/resources/fxml/customer/ShopCardLayout.fxml"));

                HBox cardBox = fxmlLoader.load();
                ShopCardLayoutController shopCardLayoutController = fxmlLoader.getController();
                shopCardLayoutController.setData(nearestShop.get(i));
                shopCardLayout.getChildren().add(cardBox);
            }
        } catch (IOException e) { 
            e.printStackTrace();
        }
    }
                    
    private List<Shop> nearestShop() {
        List<Shop> ls = new ArrayList<>();

        Shop shop1 = new Shop();
        shop1.setShopName("Store 1");
        shop1.setShopDistance("1 km");
        ls.add(shop1);

        Shop shop2 = new Shop();
        shop2.setShopName("Store 2");
        shop2.setShopDistance("2 km");
        ls.add(shop2);


        Shop shop3 = new Shop();
        shop3.setShopName("Store 3");
        shop3.setShopDistance("3 km");
        ls.add(shop3);

        Shop Shop4 = new Shop();
        Shop4.setShopName("Store 4");
        Shop4.setShopDistance("4 km");
        ls.add(Shop4);

        Shop shop5 = new Shop();
        shop5.setShopName("Store 5");
        shop5.setShopDistance("5 km");
        ls.add(shop5);

        Shop shop6 = new Shop();
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
    void handleLogOutButton(ActionEvent event) {
        System.out.println("Logout clicked");
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SettingsWindowHelper.logout(currentStage);
    }



}
