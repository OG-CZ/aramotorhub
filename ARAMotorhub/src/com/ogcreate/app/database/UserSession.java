package com.ogcreate.app.database;

public class UserSession {
    private static User currentUser;
    private static Integer activeCartId;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void clearSession() {
        currentUser = null;
        activeCartId = null;
    }

    public static void setActiveCartId(Integer cartId) {
        activeCartId = cartId;
    }

    public static Integer getActiveCartId() {
        return activeCartId;
    }
}
