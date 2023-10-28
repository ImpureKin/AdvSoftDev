package controller;

import model.User;
import database.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;

public class UserController {

    public User getUser(Connection connection, String email) {
        try {
            User user = UserManager.getUser(connection, "email", email);
            if (user != null) {
                return user;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public String editUser(Connection connection, String field, String value, String userId) {
        try {
            if (UserManager.updateUserDetail(connection, field, value, userId) == null) {
                return null;
            } else {
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

    public String deleteUser(Connection connection, String userId) {
        try {
            UserManager.deleteAccount(connection, userId);
            return null;
        } catch (Exception e) {
            return "Error deleting account: " + e;
        }
    }
}
