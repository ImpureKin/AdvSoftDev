package controller;

import java.sql.Connection;
import database.DB;

public class LoginController {

    public String isValidLogin(String email, String password) throws Exception {
        if (isEmpty(email) || isEmpty(password)) {
            return "Both email and password are required.";
        }
        Connection conn = DB.getConnection();
        try {
            if (!DB.authenticateUser(conn, email, password)) {
                conn.close();
                return "User does not exist.";
            }
        }   
        catch (Exception e) {
            conn.close();
            System.out.println("Connection Failed: " + e);
            throw e;
        }
        conn.close();
        return null;
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
