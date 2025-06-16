package com.ogcreate.app.database;

import javafx.beans.property.*;

public class User {

    private IntegerProperty userId;
    private StringProperty email;
    private StringProperty password;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty address;
    private StringProperty district;
    private StringProperty barangay;
    private StringProperty role;

    public User(int userId, String email, String password, String firstName, String lastName, String address,
                String district, String barangay, String role) {
        this.userId = new SimpleIntegerProperty(userId);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.address = new SimpleStringProperty(address);
        this.district = new SimpleStringProperty(district);
        this.barangay = new SimpleStringProperty(barangay);
        this.role = new SimpleStringProperty(role);
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty districtProperty() {
        return district;
    }

    public StringProperty barangayProperty() {
        return barangay;
    }

    public StringProperty roleProperty() {
        return role;
    }

    public int getUserId() {
        return userId.get();
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getDistrict() {
        return district.get();
    }

    public void setDistrict(String district) {
        this.district.set(district);
    }

    public String getBarangay() {
        return barangay.get();
    }

    public void setBarangay(String barangay) {
        this.barangay.set(barangay);
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }
}
