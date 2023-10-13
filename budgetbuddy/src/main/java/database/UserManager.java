// UserManager.java

package database;
import model.*;
import controller.*;
import java.sql.*;

public class UserManager {

    // #################################################################################################### //
    // #################################### ERENS FUNCTIONS BELOW ######################################### //
    // #################################################################################################### //

    public static Connection connection;

    // Authenticate a user's login details and return result
    public static boolean authenticateUser(String email, String password) {
        try {
            connection = ConnectionManager.getConnection();
            String query = "SELECT * FROM Users WHERE email = '" + email + "' AND password = '" + password + "'";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            ConnectionManager.closeConnection(connection);
            return (rs.next()); 
        } catch (Exception e) {
            System.out.println("Authentication error: " + e);
            ConnectionManager.closeConnection(connection);
            return false;
        }
    }
    
    // Get User based on chosen field and field valie (i.e. Email + user email)
    public static User getUser(String field, String field_value) {
        try {
            // Query DB
            String query = "SELECT * FROM Users WHERE " + field + " = '" + field_value + "'";
            connection = ConnectionManager.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            // Assign variables from DB query result
            String id = rs.getString("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String phoneNumber = rs.getString("phone");
            String dob = rs.getString("dob");
            String gender = rs.getString("gender");

            // Create User using details from DB
            UserController uc = new UserController();
            User user = new User(id, firstName, lastName, email, password, phoneNumber, dob, gender, uc);
            ConnectionManager.closeConnection(connection);
            return user;
        } catch (Exception e) {
            System.out.println("Error getting User: " + e);
            ConnectionManager.closeConnection(connection);
            return null;
        }
    }
    
    // Insert (Register) a User into the database
    public static String registerUser(String firstName, String lastName, String email, String password, String phoneNumber, String dob, String gender) {
        try {
            connection = ConnectionManager.getConnection();
            String query = "INSERT INTO Users (first_name, last_name, email, password, phone, dob, gender) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.setString(5, phoneNumber);
            pstmt.setString(6, dob);
            pstmt.setString(7, gender);
            pstmt.executeUpdate();
            System.out.println("Successfully registered user: " + firstName + " " + lastName);
            ConnectionManager.closeConnection(connection);
            return "Success";
        } catch (Exception e) {
            System.out.println("User Registration error: " + e);
            ConnectionManager.closeConnection(connection);
            return "Failed. " + e;
        }
    }
    
    // Update a user's details
    public static String updateUserDetail(String field, String value, String userId) {
        try {
            connection = ConnectionManager.getConnection();
            String query = "UPDATE Users SET " + field + " = '" + value + "' WHERE id = " + userId;
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();
            System.out.println("Updated user details - " + field + ".");
            ConnectionManager.closeConnection(connection);
            return null; 
        }
        catch(Exception e) {
            System.out.println("Error updating user details: " + e);
            ConnectionManager.closeConnection(connection);
            return "Error";
        }
    }
    
    // Delete user account
    public static void deleteAccount(String userID) {
        try {
            // Delete user account.
            connection = ConnectionManager.getConnection();
            String deleteAccountQuery = "DELETE FROM Users WHERE id = " + userID;
            System.out.println(deleteAccountQuery);
            PreparedStatement pstmt = connection.prepareStatement(deleteAccountQuery);
            pstmt.executeUpdate();
            System.out.println("Deleted user account.");
            ConnectionManager.closeConnection(connection);
        }
        catch(Exception e) {
            System.out.println("Error deleting user account: " + e);
            ConnectionManager.closeConnection(connection);
        }
    }
}
