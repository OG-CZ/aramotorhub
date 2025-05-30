package com.ogcreate.app.controllers.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.ogcreate.app.SettingsWindowHelper;
import com.ogcreate.app.database.Products;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductsController implements Initializable {

    @FXML
    private GridPane productsContainer;

    @FXML
    private List<Products> showProducts;

    private static final int TOTAL_WIDTH = 705;
    private static final int COLUMN_COUNT = 5;
    private static final int COLUMN_WIDTH = TOTAL_WIDTH / COLUMN_COUNT; // 141px

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        showProducts = new ArrayList<>(products());

        // Set column constraints to fix width
        productsContainer.getColumnConstraints().clear();
        for (int i = 0; i < COLUMN_COUNT; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPrefWidth(COLUMN_WIDTH);
            column.setMinWidth(COLUMN_WIDTH);
            column.setMaxWidth(COLUMN_WIDTH);
            productsContainer.getColumnConstraints().add(column);
        }

        productsContainer.setHgap(0);
        productsContainer.setVgap(0);

        int column = 0;
        int row = 1;

        try {
            for (Products product : showProducts) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/resources/fxml/customer/ProductsContainer.fxml"));
                VBox productBox = fxmlLoader.load();

                // Force the product container to have fixed width
                productBox.setPrefWidth(COLUMN_WIDTH);
                productBox.setMaxWidth(COLUMN_WIDTH);
                productBox.setMinWidth(COLUMN_WIDTH);

                ProductsContainerController productsContainerController = fxmlLoader.getController();
                productsContainerController.setData(product);

                if (column == COLUMN_COUNT) {
                    column = 0;
                    ++row;
                }

                productsContainer.add(productBox, column++, row);
                GridPane.setMargin(productBox, new Insets(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Products> products() {
        List<Products> ls = new ArrayList<>();

        for (int i = 0; i <= 20; i++) {
            Products product = new Products();
            product.setStoreName("Store " + ((i % 5) + 1));
            product.setProductPrice(String.valueOf(100 + (i * 10)));
            product.setProductName("Product " + i);
            ls.add(product);
        }

        return ls;
    }

    @FXML
    void handleShopsClick(ActionEvent event) {
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
    private void handleOpenSettings(ActionEvent event) {
        SettingsWindowHelper.openSettings((Node) event.getSource());
    }

    @FXML
    void handleLogOutButton(ActionEvent event) {
        System.out.println("Logout clicked");
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SettingsWindowHelper.logout(currentStage);
    }

    @FXML 
    void handleCartClick(ActionEvent event) {
        System.out.println("handleCartClick triggered");
    }
}
