package com.ogcreate.app.controllers.admin;

import com.ogcreate.app.SettingsWindowHelper;
import com.ogcreate.app.database.DatabaseConnection;
import com.ogcreate.app.database.User;
import com.ogcreate.app.database.Products;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.sql.*;

public class AdminController {

    @FXML private TableView<User> usersTable;
    @FXML private TableColumn<User, Integer> user_id;
    @FXML private TableColumn<User, String> emailColumn;
    @FXML private TableColumn<User, String> firstNameColumn;
    @FXML private TableColumn<User, String> lastNameColumn;
    @FXML private TableColumn<User, String> passwordColumn;
    @FXML private TableColumn<User, String> addressColumm;
    @FXML private TableColumn<User, String> districtColumn;
    @FXML private TableColumn<User, String> barangayColumn;
    @FXML private TableColumn<User, String> roleColumn;

    @FXML private TableView<Products> productsTable;
    @FXML private TableColumn<Products, Integer> productIdColumn;
    @FXML private TableColumn<Products, Integer> sellerIdColumn;
    @FXML private TableColumn<Products, Integer> categoryIdProductsColumn;
    @FXML private TableColumn<Products, String> productNameColumn;
    @FXML private TableColumn<Products, String> descriptionColumn;
    @FXML private TableColumn<Products, String> priceColumn;
    @FXML private TableColumn<Products, Integer> stockColumn;
    @FXML private TableColumn<Products, String> statusColumn;
    @FXML private TableColumn<Products, Timestamp> createdAtColumn;

    @FXML private TableView<Object[]> orderTable;
    @FXML private TableColumn<Object[], Integer> orderIdColumn;
    @FXML private TableColumn<Object[], Integer> customerIdColumn;
    @FXML private TableColumn<Object[], String> orderAddressColumn;
    @FXML private TableColumn<Object[], String> orderStatusColumn;
    @FXML private TableColumn<Object[], Double> totalPriceColumn;
    @FXML private TableColumn<Object[], Timestamp> orderCreatedAtColumn;

    @FXML private TableView<Object[]> categoryTable;
    @FXML private TableColumn<Object[], Integer> categoryIdColumn;
    @FXML private TableColumn<Object[], String> categoryNameColumn;

    private final ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        usersTable.setEditable(true);

        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addressColumm.setCellFactory(TextFieldTableCell.forTableColumn());
        districtColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        barangayColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        emailColumn.setOnEditCommit(event -> event.getRowValue().setEmail(event.getNewValue()));
        passwordColumn.setOnEditCommit(event -> event.getRowValue().setPassword(event.getNewValue()));
        firstNameColumn.setOnEditCommit(event -> event.getRowValue().setFirstName(event.getNewValue()));
        lastNameColumn.setOnEditCommit(event -> event.getRowValue().setLastName(event.getNewValue()));
        addressColumm.setOnEditCommit(event -> event.getRowValue().setAddress(event.getNewValue()));
        districtColumn.setOnEditCommit(event -> event.getRowValue().setDistrict(event.getNewValue()));
        barangayColumn.setOnEditCommit(event -> event.getRowValue().setBarangay(event.getNewValue()));
        roleColumn.setOnEditCommit(event -> event.getRowValue().setRole(event.getNewValue()));

        loadUsers();
        loadProducts();
        loadOrders();
        loadCategories();
    }

    private void loadUsers() {
        userList.clear();
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT * FROM user";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User(
                    rs.getInt("user_id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("address"),
                    rs.getString("district"),
                    rs.getString("barangay"),
                    rs.getString("role")
                );
                userList.add(user);
            }

            user_id.setCellValueFactory(new PropertyValueFactory<>("userId"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            addressColumm.setCellValueFactory(new PropertyValueFactory<>("address"));
            districtColumn.setCellValueFactory(new PropertyValueFactory<>("district"));
            barangayColumn.setCellValueFactory(new PropertyValueFactory<>("barangay"));
            roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

            usersTable.setItems(userList);
        } catch (SQLException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    private void loadProducts() {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT * FROM product";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ObservableList<Products> productData = FXCollections.observableArrayList();

            while (rs.next()) {
                Products p = new Products();
                p.setProductId(rs.getInt("product_id"));
                p.setSellerId(rs.getInt("seller_id"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setProductName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setProductPrice(String.valueOf(rs.getDouble("price")));
                p.setStock(rs.getInt("stock"));
                p.setStatus(rs.getString("status"));
                p.setCreatedAt(rs.getTimestamp("created_at"));

                productData.add(p);
            }

            productIdColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getProductId()));
            sellerIdColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getSellerId()));
            categoryIdProductsColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getCategoryId()));
            productNameColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getProductName()));
            descriptionColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getDescription()));
            priceColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getProductPrice()));
            stockColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getStock()));
            statusColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getStatus()));
            createdAtColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getCreatedAt()));

            productsTable.setItems(productData);
        } catch (SQLException e) {
            System.out.println("Error loading products: " + e.getMessage());
        }
    }

    private void loadOrders() {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT * FROM `order`";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ObservableList<Object[]> orderData = FXCollections.observableArrayList();

            while (rs.next()) {
                orderData.add(new Object[] {
                    rs.getInt("order_id"),
                    rs.getInt("customer_id"),
                    rs.getString("address"),
                    rs.getString("status"),
                    rs.getDouble("total_price"),
                    rs.getTimestamp("created_at")
                });
            }

            orderIdColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>((Integer) cell.getValue()[0]));
            customerIdColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>((Integer) cell.getValue()[1]));
            orderAddressColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>((String) cell.getValue()[2]));
            orderStatusColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>((String) cell.getValue()[3]));
            totalPriceColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>((Double) cell.getValue()[4]));
            orderCreatedAtColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>((Timestamp) cell.getValue()[5]));

            orderTable.setItems(orderData);
        } catch (SQLException e) {
            System.out.println("Error loading orders: " + e.getMessage());
        }
    }

    @FXML
    private void handleCreate(ActionEvent event) {
        User newUser = new User(0, "", "", "", "", "", "", "", "CUSTOMER");
        userList.add(newUser);
        usersTable.getSelectionModel().select(newUser);
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        for (User user : usersTable.getItems()) {
            try (Connection conn = DatabaseConnection.connect()) {
                if (user.getUserId() == 0) {
                    String insert = "INSERT INTO user (email, password, first_name, last_name, address, district, barangay, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(insert);
                    stmt.setString(1, user.getEmail());
                    stmt.setString(2, user.getPassword());
                    stmt.setString(3, user.getFirstName());
                    stmt.setString(4, user.getLastName());
                    stmt.setString(5, user.getAddress());
                    stmt.setString(6, user.getDistrict());
                    stmt.setString(7, user.getBarangay());
                    stmt.setString(8, user.getRole());
                    stmt.executeUpdate();
                } else {
                    String update = "UPDATE user SET email = ?, password = ?, first_name = ?, last_name = ?, address = ?, district = ?, barangay = ?, role = ? WHERE user_id = ?";
                    PreparedStatement stmt = conn.prepareStatement(update);
                    stmt.setString(1, user.getEmail());
                    stmt.setString(2, user.getPassword());
                    stmt.setString(3, user.getFirstName());
                    stmt.setString(4, user.getLastName());
                    stmt.setString(5, user.getAddress());
                    stmt.setString(6, user.getDistrict());
                    stmt.setString(7, user.getBarangay());
                    stmt.setString(8, user.getRole());
                    stmt.setInt(9, user.getUserId());
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                System.out.println("Error saving user: " + e.getMessage());
            }
        }
        loadUsers();
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        User selected = usersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try (Connection conn = DatabaseConnection.connect()) {
                String delete = "DELETE FROM user WHERE user_id = ?";
                PreparedStatement stmt = conn.prepareStatement(delete);
                stmt.setInt(1, selected.getUserId());
                stmt.executeUpdate();
                userList.remove(selected);
            } catch (SQLException e) {
                System.out.println("Error deleting user: " + e.getMessage());
            }
        }
    }

    private void loadCategories() {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT * FROM category";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ObservableList<Object[]> categoryData = FXCollections.observableArrayList();

            while (rs.next()) {
                categoryData.add(new Object[] {
                    rs.getInt("category_id"),
                    rs.getString("name")
                });
            }

            categoryIdColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>((Integer) cell.getValue()[0]));
            categoryNameColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>((String) cell.getValue()[1]));

            categoryTable.setItems(categoryData);
        } catch (SQLException e) {
            System.out.println("Error loading categories: " + e.getMessage());
        }
    }

    @FXML
    void handleLogOutButton(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SettingsWindowHelper.logout(currentStage);
    }
}
