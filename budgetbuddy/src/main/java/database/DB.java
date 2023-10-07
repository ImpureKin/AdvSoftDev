package database;
import model.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class DB {

    // #################################################################################################### //
    // #################################### UNIVERSAL FUNCTON HERE ######################################## //
    // #################################################################################################### //

    // Establish and return connection to the DB
    public static Connection getConnection() throws Exception {
        try {
            Class.forName("org.sqlite.JDBC");
            // Connection Path - NEED TO CHANGE THIS ACCORDING TO ACTUAL LOCATION. Need to figure out how to do this on WebApp side.
            Connection con = DriverManager.getConnection("jdbc:sqlite:/Users/alyssa/Documents/AdvSoftDev/budgetbuddy/src/main/webapp/BudgetBuddy.db");
            
            System.out.println("Connection Successful");
            return con;
        }
        catch (Exception e) {
            System.out.println("Connection Failed: " + e);
            throw e;
        }
    }

    // #################################################################################################### //
    // #################################### ERENS FUNCTIONS BELOW ######################################### //
    // #################################################################################################### //

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
    public static User getUser(Connection connection, String request_email) {
        try {
            // Query DB
            String query = "SELECT * FROM Users WHERE email = '" + request_email + "'";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            // Assign variables from DB query result
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String phoneNumber = rs.getString("phone_number");
            String address = rs.getString("address");

            // Create User using details from DB
            User user = new User(id, firstName, lastName, email, password, phoneNumber, address);
            return user;
        } catch (SQLException e) {
            System.out.println("Error getting User: " + e);
            return null;
        }
    }
    
    // Insert (Register) a User into the database
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
    
    // Update a user's details
    public static void updateUserDetail(Connection connection, String field, String value, int userID) {
        String query = "UPDATE Users SET " + field + " = '" + value + "' WHERE id = " + userID;
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
    public static void deleteAccount(Connection connection, int userID) {
        String deleteAccountQuery = "DELETE FROM Users WHERE id = " + userID;
        System.out.println(deleteAccountQuery);
        try {
            // Delete user account.
            PreparedStatement pstmt = connection.prepareStatement(deleteAccountQuery);
            pstmt.executeUpdate();
            System.out.println("Deleted user account.");
        }
        catch(Exception e) {
            System.out.println("Error deleting user account: " + e);
        }
    }
    // #################################################################################################### //
    // #################################### OTHER FUNCTIONS BELOW ######################################### //
    // #################################################################################################### //
    
    // #################################################################################################### //
    // #################################### GOAL FUNCTIONS ################################################ //
    // #################################################################################################### //
    public static List<Goals> getGoalsByUserId(Connection connection, int userId) {
        List<Goals> goals = new ArrayList<>();
        try {
            String query = "SELECT id, userId, name, description, goal_amount, saved_amount, category FROM Goals WHERE userId = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
    
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int goalAmount = rs.getInt("goal_amount");
                int savedAmount = rs.getInt("saved_amount");
                String category = rs.getString("category");
    
                Goals goal = new Goals(id, userId, name, goalAmount, savedAmount, category, description, null);
                goals.add(goal);
            }
        } catch (SQLException e) {
            System.out.println("Error getting goals: " + e);
        }
        return goals;
    }

    public static Goals getGoalById(int goalId) {
        Goals goal = null;
        try {
            Connection connection = getConnection();
            String query = "SELECT * FROM Goals WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, goalId);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                int userId = rs.getInt("userId");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int goalAmount = rs.getInt("goal_amount");
                int savedAmount = rs.getInt("saved_amount");
                String category = rs.getString("category");
                Date date = new SimpleDateFormat("dd/MM/yy").parse(rs.getString("date"));
    
                goal = new Goals(goalId, userId, name, goalAmount, savedAmount, category, description, date);
            }
    
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goal;
    }

    public static void updateSavedAmount(int goalId, int savedAmount) {
        try {
            Connection connection = getConnection();
            String query = "UPDATE Goals SET saved_amount = saved_amount + ? WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, savedAmount);
            pstmt.setInt(2, goalId);
            pstmt.executeUpdate();
    
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createGoal(Goals goal) throws Exception {
        try {
            Connection connection = getConnection();
    
            String query = "INSERT INTO Goals (userId, name, goal_amount, saved_amount, category, description, date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, goal.getUserId());
            pstmt.setString(2, goal.getName());
            pstmt.setInt(3, goal.getGoalAmount());
            pstmt.setInt(4, goal.getSavedAmount());
            pstmt.setString(5, goal.getCategory());
            pstmt.setString(6, goal.getDescription());
    
            // Format the date
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(goal.getDate());
    
            pstmt.setString(7, formattedDate);
    
            pstmt.executeUpdate();
    
            connection.close();
    
        } catch (SQLException e) {
            System.out.println("Error inserting goal: " + e);
        }
    }
    



    public static Finances getFinancesByUserId(Connection connection, int userId) {
        Finances finances = null;
        try {
            String query = "SELECT * FROM Finances WHERE userId = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                int totalIncome = rs.getInt("total_income");
                int totalDeductions = rs.getInt("total_deductions");
                int totalExpenses = rs.getInt("total_expenses");
                int totalSavings = rs.getInt("total_savings");
    
                finances = new Finances(userId, totalIncome, totalDeductions, totalExpenses, totalSavings);
            }
        } catch (SQLException e) {
            System.out.println("Error getting finances: " + e);
        }
        return finances;
    }
}