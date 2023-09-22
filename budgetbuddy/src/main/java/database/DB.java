package database;
import model.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {

    // Establish and return connection to the DB
    public static Connection getConnection() throws Exception {
        try {
            Class.forName("org.sqlite.JDBC");
            // Connection Path
            Connection con = DriverManager.getConnection("jdbc:sqlite:BudgetBuddy.db");
            
            System.out.println("Connection Successful");
            return con;
        }
        catch (Exception e) {
            System.out.println("Connection Failed: " + e);
            throw e;
        }
    }

    // Authenticate a user's login details and return result
    public static boolean authenticateUser(Connection connection, String email, String password) {
        try {
            String query = "SELECT * FROM Users WHERE email = '" + email + "' AND password = '" + password + "'";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            return (rs.next());
            
        } catch (SQLException e) {
            System.out.println("Authentication error: " + e);
            return false;
        }
    }
    
    // Get User based on email and userType
    public static User getUser(Connection connection, String userType, String email) {
        try {
            String query = "SELECT * FROM Users WHERE email = '" + email + "'";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String password = rs.getString("password");
            String phoneNumber = rs.getString("phone_number");
            String address = rs.getString("address");
            
            User user = new User(id, firstName, lastName, email, password, phoneNumber, address);
            return user;
            
        } catch (SQLException e) {
            System.out.println("Error getting User: " + e);
            return null;
        }
    }
    
    // Insert (Register) a customer into the database
    public static String registerCustomer(Connection connection, String firstName, String lastName, String email, String password, String phoneNumber, String address) {
        try {
            String query = "INSERT INTO Customer (first_name, last_name, email, password, phone_number, address) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.setString(5, phoneNumber);
            pstmt.setString(6, address);
            pstmt.executeUpdate();
            System.out.println("Successfully registered customer: " + firstName + " " + lastName);
            return "Success";
        } catch (SQLException e) {
            System.out.println("Customer Registration error: " + e);
            return "Failed. " + e;
        }
    }
    
    // Insert (Register) a staff into the database
    public static String registerStaff(Connection connection, String firstName, String lastName, String email, String password, String role, String phoneNumber, String address) {
        try {
            String query = "INSERT INTO Staff (first_name, last_name, email, password, role, phone_number, address) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.setString(5, role);
            pstmt.setString(6, phoneNumber);
            pstmt.setString(7, address);
            pstmt.executeUpdate();
            System.out.println("Successfully registered staff: " + firstName + " " + lastName);
            return "Success";
        } catch (SQLException e) {
            System.out.println("Staff Registration error: " + e);
            return "Failed. " + e;
        }
    }
    
    // Update a user's details
    public static void updateUserDetail(Connection connection, String userType, String field, String value, int userID) {
        String tableName = (userType.equalsIgnoreCase("customer") ? "customer" : "staff");
        String query = "UPDATE " + tableName + " SET " + field + " = '" + value + "' WHERE id = " + userID;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();
            System.out.println("Updated user details - " + field + ".");
        }
        catch(Exception e) {
            System.out.println("Error updating user details: " + e);
        }
    }
    
    // Delete user account
    public static void deleteAccount(Connection connection, String userType, int userID) {
        String tableName = (userType.equalsIgnoreCase("customer") ? "customer" : "staff");
        String deleteAccountQuery = "DELETE FROM " + tableName + " WHERE id = " + userID;
        String deleteOrdersQuery = "UPDATE 'order' SET status = 'Cancelled' WHERE customer_id = " + userID + " AND status = 'unfulfilled'";
        System.out.println(deleteAccountQuery);
        System.out.println(deleteOrdersQuery);
        try {
            // Delete user account.
            PreparedStatement pstmt = connection.prepareStatement(deleteAccountQuery);
            pstmt.executeUpdate();
            // Cancel orders related to customer.
            pstmt = connection.prepareStatement(deleteOrdersQuery);
            pstmt.executeUpdate();
            System.out.println("Deleted user account and cancelled orders.");
        }
        catch(Exception e) {
            System.out.println("Error deleting user account and/or cancelling orders: " + e);
        }
    }
}
