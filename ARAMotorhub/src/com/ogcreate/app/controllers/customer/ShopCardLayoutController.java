package com.ogcreate.app.controllers.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.ogcreate.app.database.Shop;

public class ShopCardLayoutController {

    @FXML
    private Label shopDistance;

    @FXML
    private Label shopName;

    public void setData(Shop shop) {
        shopName.setText(shop.getShopName());
        shopDistance.setText(shop.getShopDistance());
    }
}
