package controller;
import model.User;
import database.DB;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserController {
    public User getUser(String email) throws Exception {
        Connection conn = DB.getConnection();
        User user = DB.getUser(conn, email);
        if (user != null) {
            conn.close();
            return user;
        }
        conn.close();
        return null;
    }

    public String editUser(String field, String value, int userId) throws Exception {
        Connection conn = DB.getConnection();
        if (DB.updateUserDetail(conn, field, value, userId) != null) {
            return null;
        }
        else {
            return "Error updating user details.";
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
}
