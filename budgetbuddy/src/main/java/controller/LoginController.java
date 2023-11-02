package controller;

import java.sql.Connection;
import database.*;

public class LoginController {
    
    Connection conn;

    public String isValidLogin(Connection connection, String email, String password) throws Exception {
        if (isEmpty(email) || isEmpty(password)) {
            return "Both email and password are required.";
        }
        try {
            if (!UserManager.authenticateUser(connection, email, password)) {
                return "User does not exist.";
            }
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
            throw e;
        }
        return null;
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
