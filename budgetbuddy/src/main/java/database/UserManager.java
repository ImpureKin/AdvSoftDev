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
    public static boolean authenticateUser(Connection connection, String email, String password) {
        try {
            String query = "SELECT * FROM Users WHERE email = '" + email + "' AND password = '" + password + "'";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            return (rs.next()); 
        } catch (Exception e) {
            System.out.println("Authentication error: " + e);
            return false;
        }
    }
    
    // Get User based on chosen field and field valie (i.e. Email + user email)
    public static User getUser(Connection connection, String field, String field_value) {
        try {
            // Query DB
            String query = "SELECT * FROM Users WHERE " + field + " = '" + field_value + "'";
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
            String mfa = rs.getString("mfa");

            // Create User using details from DB
            UserController uc = new UserController();
            User user = new User(id, firstName, lastName, email, password, phoneNumber, dob, gender, mfa, uc);
            return user;
        } catch (Exception e) {
            System.out.println("Error getting User: " + e);
            return null;
        }
    }
    
    // Insert (Register) a User into the database
    public static String registerUser(Connection connection, String firstName, String lastName, String email, String password, String phoneNumber, String dob, String gender) {
        try {
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
            return "Success";
        } catch (Exception e) {
            System.out.println("User Registration error: " + e);
            return "Failed. " + e;
        }
    }
    
    // Update a user's details
    public static String updateUserDetail(Connection connection, String field, String value, String userId) {
        try {
            String query = "UPDATE Users SET " + field + " = '" + value + "' WHERE id = " + userId;
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();
            System.out.println("Updated user details - " + field + ".");
            return null; 
        }
        catch(Exception e) {
            System.out.println("Error updating user details: " + e);
            return "Error";
        }
    }
    
    // Delete user account
    public static void deleteAccount(Connection connection, String userID) {
        try {
            // Delete user account.
            String deleteAccountQuery = "DELETE FROM Users WHERE id = " + userID;
            System.out.println(deleteAccountQuery);
            PreparedStatement pstmt = connection.prepareStatement(deleteAccountQuery);
            pstmt.executeUpdate();
            System.out.println("Deleted user account.");
        }
        catch(Exception e) {
            System.out.println("Error deleting user account: " + e);
        }
    }
}
