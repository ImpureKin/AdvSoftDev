package controller;

import java.sql.Connection;
import database.DB;

public class LoginController {

    public String isValidLogin(String email, String password) throws Exception {
        if (isEmpty(email) || isEmpty(password)) {
            return "Both email and password are required.";
        }

        try {
            Connection conn = DB.getConnection();
            if (!DB.authenticateUser(conn, email, password)) {
                return "User does not exist.";
            }
        }   
        catch (Exception e) {
            System.out.println("Connection Failed: " + e);
            throw e;
        }
        return null;
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
