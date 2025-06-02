package com.ogcreate.app.controllers.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;

public class CartProductContainerController {

    @FXML
    private Label labelPrice;

    @FXML
    private Label labelProductInitials;

    @FXML
    private Label labelProductName;

    @FXML
    private Label labelStoreAdress;

    @FXML
    private Label labelStoreName;

    @FXML
    private Spinner<Integer> spinnerProductAmount;

    @FXML
    private HBox rootHBox;  

    public void setProductDetails(String name, String initials, String price, String storeName, String storeAddress) {
        labelProductName.setText(name);
        labelProductInitials.setText(initials);
        labelPrice.setText(price);
        labelStoreName.setText(storeName);
        labelStoreAdress.setText(storeAddress);
    }

    @FXML
    private void handleDeleteButton() {
        ((javafx.scene.layout.Pane) rootHBox.getParent()).getChildren().remove(rootHBox);
    }
}
