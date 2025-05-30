package com.ogcreate.app.controllers.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.ogcreate.app.database.Shops;

public class ShopsCardLayoutController {

    @FXML
    private Label shopDistance;

    @FXML
    private Label shopName;

    public void setData(Shops shop) {
        shopName.setText(shop.getShopName());
        shopDistance.setText(shop.getShopDistance());
    }
}
