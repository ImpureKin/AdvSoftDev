package controller;

import model.User;
import database.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;

public class UserController {
    public User getUser(String email) {
        try {
            Connection conn = ConnectionManager.getConnection();
            User user = UserManager.getUser(conn, email);
            if (user != null) {
                conn.close();
                return user;
            }
            conn.close();
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public String editUser(String field, String value, String userId) {
        try {
            Connection conn = ConnectionManager.getConnection();
            if (UserManager.updateUserDetail(conn, field, value, userId) == null) {
                conn.close();
                return null;
            } else {
                conn.close();
                return "Error updating user details.";
            }
        } catch (Exception e) {
            return "Error updating user details: " + e;
        }
    }

    public String getValue(User user, String value) {
        try {
            // Capitalize the first letter of the 'value' to match the getter method name
            String methodName = "get" + value.substring(0, 1).toUpperCase() + value.substring(1);

            // Use reflection to invoke the getter method
            Method getter = User.class.getMethod(methodName);
            Object result = getter.invoke(user);

            if (result != null) {
                return result.toString();
            } else {
                return "Value not found"; // Handle null values as needed
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return "Error accessing value";
        }
    }

    public String deleteUser(String userId) {
        try {
            Connection conn = ConnectionManager.getConnection();
            UserManager.deleteAccount(conn, userId);
            conn.close();
            return null;
        } catch (Exception e) {
            return "Error deleting account: " + e;
        }
    }
}
