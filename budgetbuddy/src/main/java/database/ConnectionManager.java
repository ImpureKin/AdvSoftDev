package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

    // Establish and return the TEST connection to the DB
    // Only for testing purposes
    public static Connection getTestConnection() throws Exception {
        try {
            Class.forName("org.sqlite.JDBC");
            // Connection Path - NEED TO CHANGE THIS ACCORDING TO ACTUAL LOCATION. Need to figure out how to do this on WebApp side.
            Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Big Pops\\Desktop\\AdvSoftDev\\AdvSoftDev\\budgetbuddy\\src\\main\\webapp\\BudgetBuddy.db");
            // Webapp Path: /home/site/wwwroot/webapps/app/BudgetBuddy.db
            
            System.out.println("Connection Successful");
            return con;
        }
        catch (Exception e) {
            System.out.println("Connection Failed: " + e);
            throw e;
        }
    }


    // Establish and return connection to the DB
    // For webapp purposes 
    public static Connection getConnection() throws Exception {
        try {
            Class.forName("org.sqlite.JDBC");
            // Connection Path - NEED TO CHANGE THIS ACCORDING TO ACTUAL LOCATION. Need to figure out how to do this on WebApp side.
            Connection con = DriverManager.getConnection("jdbc:sqlite:/home/site/wwwroot/webapps/app/BudgetBuddy.db");
            // Webapp Path: /home/site/wwwroot/webapps/app/BudgetBuddy.db
            
            System.out.println("Connection Successful");
            return con;
        }
        catch (Exception e) {
            System.out.println("Connection Failed: " + e);
            throw e;
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        }
        catch (Exception e) {
            System.out.println("Failed to close connection: " + e);
        }
    }

    public static Connection resetTestConnection(Connection connection) {
        try {
            closeConnection(connection);
            return getTestConnection();
        }
        catch (Exception e) {
            System.out.println("Failed to close connection: " + e);
            return null;
        }
    }
}
