package com.ogcreate.app.controllers.auth;

public enum UserRole {
    CUSTOMER("Customer"),
    STORE("Seller");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static UserRole fromDisplayName(String displayName) {
        for (UserRole role : UserRole.values()) {
            if (role.displayName.equalsIgnoreCase(displayName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + displayName);
    }
}
