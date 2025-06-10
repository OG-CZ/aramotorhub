package com.ogcreate.app.controllers.customer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.ogcreate.app.SettingsWindowHelper;
import com.ogcreate.app.database.DatabaseConnection;
import com.ogcreate.app.database.Products;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductsController implements Initializable {

    @FXML
    private GridPane productsContainer;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private List<Products> showProducts;

    private static final int TOTAL_WIDTH = 715;
    private static final int COLUMN_COUNT = 5;
    private static final int COLUMN_WIDTH = TOTAL_WIDTH / COLUMN_COUNT; // 141px

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoryComboBox.setPromptText("Category");

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement("SELECT name FROM category");
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                categoryComboBox.getItems().add(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        categoryComboBox.setOnAction(event -> {
            String selectedCategory = categoryComboBox.getValue();
            if (selectedCategory != null && !selectedCategory.isEmpty()) {
                openCategoriesPage(selectedCategory);
            }
        });

        showProducts = getAllAvailableProducts();

        productsContainer.getColumnConstraints().clear();
        for (int i = 0; i < COLUMN_COUNT; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPrefWidth(COLUMN_WIDTH);
            column.setMinWidth(COLUMN_WIDTH);
            column.setMaxWidth(COLUMN_WIDTH);
            productsContainer.getColumnConstraints().add(column);
        }

        productsContainer.setMaxWidth(200);
        productsContainer.setMinWidth(200);

        int column = 0;
        int row = 1;

        try {
            for (Products product : showProducts) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/resources/fxml/customer/ProductsContainer.fxml"));
                VBox productBox = fxmlLoader.load();

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

    private void openCategoriesPage(String category) {
        System.out.println("Opening category: " + category);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/customer/Categories.fxml"));
            Parent newRoot = loader.load();

            CategoriesController controller = loader.getController();
            controller.setSelectedCategory(category);

            Stage currentStage = (Stage) categoryComboBox.getScene().getWindow();
            currentStage.setScene(new Scene(newRoot));
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Products> getAllAvailableProducts() {
        List<Products> productsList = new ArrayList<>();
        String sql = "SELECT DISTINCT p.product_id, p.name, p.price, p.seller_id, u.first_name, u.last_name " +
                "FROM product p " +
                "JOIN user u ON p.seller_id = u.user_id " +
                "WHERE p.status = 'AVAILABLE' " +
                "ORDER BY p.name ASC";

        Set<String> productNamesSet = new HashSet<>();

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String productName = rs.getString("name");
                if (!productNamesSet.contains(productName)) {
                    productNamesSet.add(productName);

                    Products p = new Products();
                    p.setProductId(rs.getInt("product_id"));
                    p.setProductName(productName);
                    p.setProductPrice(rs.getString("price"));
                    String sellerName = rs.getString("first_name") + " " + rs.getString("last_name");
                    p.setStoreName(sellerName.trim());
                    p.setSellerId(rs.getInt("seller_id"));
                    productsList.add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productsList;
    }

    @FXML
    void handleShopsClick(ActionEvent event) {
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

    @FXML
    void handleFavoriteClick(ActionEvent event) {
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
    private void handleOpenSettings(ActionEvent event) {
        SettingsWindowHelper.openSettings((Node) event.getSource());
    }

    @FXML
    void handleLogOutButton(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SettingsWindowHelper.logout(currentStage);
    }

    @FXML
    void handleCartClick(ActionEvent event) {
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
    private void handleProfileClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/customer/Profile.fxml"));
            Parent newRoot = loader.load();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(newRoot));
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
