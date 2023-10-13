package controller;

import java.sql.Connection;
import database.*;

public class LoginController {
    
    Connection conn;

    public String isValidLogin(String email, String password) throws Exception {
        if (isEmpty(email) || isEmpty(password)) {
            return "Both email and password are required.";
        }
        conn = ConnectionManager.getConnection();
        try {
            if (!UserManager.authenticateUser(email, password)) {
                ConnectionManager.closeConnection(conn);
                return "User does not exist.";
            }
        } catch (Exception e) {
            ConnectionManager.closeConnection(conn);
            System.out.println("Connection Failed: " + e);
            throw e;
        }
        ConnectionManager.closeConnection(conn);
        return null;
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
