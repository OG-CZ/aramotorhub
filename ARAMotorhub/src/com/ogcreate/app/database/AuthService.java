package com.ogcreate.app.database;

public class AuthService {
    
    public boolean Customer(String emailField, String password) {
        return emailField.equals("customer") && password.equals("1234");
    }

    public boolean Store(String emailField, String password) {
        return emailField.equals("store") && password.equals("1234");
    }

    public boolean Admin(String emailField, String password) {
        return emailField.equals("admin") && password.equals("1234");
    }

    public boolean Submit(boolean value) {

        return value == true;
    }

}
