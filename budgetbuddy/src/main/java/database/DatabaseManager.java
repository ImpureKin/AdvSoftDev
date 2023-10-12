package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {

    private static Connection conn;
    static ArrayList<String> tables = new ArrayList<>(Arrays.asList("Users", "Incomes", "Expenses", "Deductions", "Goals", "Bills", "Tips"));
    static ArrayList<String> views = new ArrayList<>(Arrays.asList("Finances", "TotalUserSavings "));
    
    public boolean resetDatabase() {
        try {
            System.out.println("Now resetting Database...");
            conn = ConnectionManager.getConnection();
            Statement foreignKeyStatement = conn.createStatement(); foreignKeyStatement.executeUpdate("PRAGMA foreign_keys = OFF; ");
            deleteDatabase(conn);
            createDatabase(conn);
            foreignKeyStatement = conn.createStatement(); foreignKeyStatement.executeUpdate("PRAGMA foreign_keys = ON; ");
            closeConnection();
            System.out.println("Successfully reset Database...");
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static void deleteDatabase(Connection connection) {
        try {
            System.out.println("Now deleting Database...");
            deleteTables(connection);
            deleteViews(connection);
            System.out.println("Successfully deleted Database.");
        } catch (Exception e) {
            System.out.println("Failed to delete Database: " + e);
        }
    }

    public static void deleteTables(Connection connection) {
        try {
            System.out.println("Now deleting Database tables...");
            // call DROP TABLE on every table
            for (String table : tables) {
                String dropQuery = "DROP TABLE IF EXISTS " + table;
                PreparedStatement pstmt = connection.prepareStatement(dropQuery);
                pstmt.executeUpdate();
                System.out.println("Deleted Table: " + table);
            }
            System.out.println("Successfully deleted Database tables.");
        } catch (Exception e) {
            System.out.println("Failed to delete Database tables: " + e);
        }
    }

    public static void deleteViews(Connection connection) {
        try {
            System.out.println("Now deleting Database views...");
            // call DROP VIEW on every view
            for (String view : views) {
                String dropQuery = "DROP VIEW IF EXISTS " + view;
                PreparedStatement pstmt = connection.prepareStatement(dropQuery);
                pstmt.executeUpdate();
                System.out.println("Deleted View: " + view);
            }
            System.out.println("Successfully deleted Database views.");
        } catch (Exception e) {
            System.out.println("Failed to delete Database views: " + e);
        }
    }

    public static void createDatabase(Connection connection) {
        try {
            System.out.println("Creating Database...");
            createTables(connection);
            createViews(connection);
            insertTableData(connection);
            System.out.println("Successfully created Database.");
        } catch (Exception e) {
            System.out.println("Failed to create Database: " + e);
        }

    }

    public static void createTables(Connection connection) {
        try {
            System.out.println("Now creating Database tables...");
            Map<String, String> tableQueries = createTableQueries();
            for (Map.Entry<String, String> entry : tableQueries.entrySet()) {
                String tableName = entry.getKey();
                String createQuery = entry.getValue();
                
                Statement stmt = connection.createStatement();
                stmt.execute(createQuery);
                
                System.out.println("Created Table: " + tableName);
            }
            System.out.println("Successfully created Database tables.");
        } catch (Exception e) {
            System.out.println("Failed to create Database tables: " + e);
        }
    }

    public static void createViews(Connection connection) {
        try {
            System.out.println("Now creating Database views...");
            Map<String, String> viewQueries = createViewQueries();
            for (Map.Entry<String, String> entry : viewQueries.entrySet()) {
                String viewName = entry.getKey();
                String createQuery = entry.getValue();

                Statement stmt = connection.createStatement();
                stmt.execute(createQuery);

                System.out.println("Created View: " + viewName);
            }
            System.out.println("Successfully created Database views.");
        } catch (Exception e) {
            System.out.println("Failed to create Database views: " + e);
        }
    }

    public static void insertTableData(Connection connection) {
        try {
            System.out.println("Now inserting Database table data...");
            Map<String, String> tableDataQueries = createTableDataQueries();
            for (Map.Entry<String, String> entry : tableDataQueries.entrySet()) {
                String tableName = entry.getKey();
                String insertQuery = entry.getValue();

                PreparedStatement pstmt = connection.prepareStatement(insertQuery);
                pstmt.executeUpdate();

                System.out.println("Inserted Data into Table: " + tableName);
            }
            System.out.println("Successfully inserted Database table data.");
        } catch (Exception e) {
            System.out.println("Failed to insert Database table data: " + e);
        }
    }

    public static Map<String, String> createTableQueries() {
        Map<String, String> createTableQueries = new HashMap<>();

        createTableQueries.put("Users", "CREATE TABLE Users (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "email TEXT NOT NULL UNIQUE,\n" +
                "password TEXT NOT NULL,\n" +
                "first_name TEXT NOT NULL,\n" +
                "last_name TEXT NOT NULL,\n" +
                "phone INTEGER UNIQUE,\n" +
                "dob TEXT NOT NULL,\n" +
                "gender TEXT NOT NULL\n" +
                ");");

        createTableQueries.put("Incomes", "CREATE TABLE Incomes (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "userId INTEGER NOT NULL,\n" +
                "name TEXT NOT NULL,\n" +
                "amount INTEGER NOT NULL,\n" +
                "category TEXT NOT NULL,\n" +
                "date TEXT NOT NULL,\n" +
                "frequency TEXT NOT NULL,\n" +
                "FOREIGN KEY (userId) REFERENCES Users(id)\n" +
                ");");

        createTableQueries.put("Expenses", "CREATE TABLE Expenses (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "userId INTEGER NOT NULL,\n" +
                "expenseName TEXT NOT NULL,\n" +
                "amount INTEGER NOT NULL,\n" +
                "category TEXT NOT NULL,\n" +
                "date TEXT NOT NULL,\n" +
                "FOREIGN KEY (userId) REFERENCES Users(id)\n" +
                ");");

        createTableQueries.put("Deductions", "CREATE TABLE Deductions (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "userId INTEGER NOT NULL,\n" +
                "name TEXT NOT NULL,\n" +
                "amount INTEGER NOT NULL,\n" +
                "category TEXT NOT NULL,\n" +
                "date TEXT NOT NULL,\n" +
                "frequency TEXT NOT NULL,\n" +
                "invoice_date TEXT,\n" +
                "FOREIGN KEY (userId) REFERENCES Users(id)\n" +
                ");");

        createTableQueries.put("Goals", "CREATE TABLE Goals (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "userId INTEGER NOT NULL,\n" +
                "name TEXT NOT NULL,\n" +
                "goal_amount INTEGER NOT NULL,\n" +
                "saved_amount INTEGER,\n" +
                "category TEXT NOT NULL,\n" +
                "description TEXT,\n" +
                "date TEXT NOT NULL,\n" +
                "FOREIGN KEY (userId) REFERENCES Users(id)\n" +
                ");");

        createTableQueries.put("Bills", "CREATE TABLE Bills (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "userId INTEGER NOT NULL,\n" +
                "name TEXT,\n" +
                "amount INTEGER NOT NULL,\n" +
                "category TEXT NOT NULL,\n" +
                "date TEXT NOT NULL,\n" +
                "frequency TEXT NOT NULL,\n" +
                "FOREIGN KEY (userId) REFERENCES Users(id)\n" +
                ");");

        createTableQueries.put("Tips", "CREATE TABLE Tips (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "name TEXT NOT NULL,\n" +
                "description TEXT NOT NULL\n" +
                ");");

        createTableQueries.put("Payments", "CREATE TABLE Payments (\n" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "name TEXT NOT NULL,\n" +
            "amount TEXT NOT NULL,\n" +
            "dob TEXT NOT NULL\n" +
            ");");

        createTableQueries.put("MoneySavings", "CREATE TABLE MoneySavings (\n" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "name TEXT NOT NULL,\n" +
            "totalAmount TEXT NOT NULL,\n" +
            "interest TEXT NOT NULL,\n" +
            "totalMonth TEXT NOT NULL,\n" +
            "createdDate TEXT NOT NULL,\n" +
            "userId TEXT NOT NULL\n" +
            ");");

        return createTableQueries;
    }

    public static Map<String, String> createViewQueries() {
        Map<String, String> createViewQueries = new HashMap<>();

        createViewQueries.put("Finances", "CREATE VIEW Finances AS\n" +
                "SELECT \n" +
                "    I.userId,\n" +
                "    COALESCE(total_income, 0) AS total_income,\n" +
                "    COALESCE(total_deductions, 0) AS total_deductions,\n" +
                "    COALESCE(total_expenses, 0) AS total_expenses,\n" +
                "    (COALESCE(total_income, 0) - COALESCE(total_deductions, 0) - COALESCE(total_expenses, 0)) AS total_savings\n" +
                "FROM\n" +
                "    (SELECT userId, SUM(amount) AS total_income FROM Incomes GROUP BY userId) AS I\n" +
                "    LEFT JOIN\n" +
                "    (SELECT userId, SUM(amount) AS total_deductions FROM Deductions GROUP BY userId) AS D ON I.userId = D.userId\n" +
                "    LEFT JOIN\n" +
                "    (SELECT userId, SUM(amount) AS total_expenses FROM Expenses GROUP BY userId) AS E ON I.userId = E.userId;");

        createViewQueries.put("TotalUserSavings", "CREATE VIEW TotalUserSavings AS\n" +
                "SELECT\n" +
                "    U.id AS User_ID,\n" +
                "    COALESCE(F.total_savings, 0) AS total_savings,\n" +
                "    COALESCE(G.total_goal_savings, 0) AS total_goal_savings,\n" +
                "    COALESCE(\n" +
                "        CASE\n" +
                "            WHEN F.total_savings - G.total_goal_savings < 0 THEN 0\n" +
                "            ELSE F.total_savings - G.total_goal_savings\n" +
                "        END, 0) AS total_saved\n" +
                "FROM\n" +
                "    Users U\n" +
                "LEFT JOIN\n" +
                "    (SELECT\n" +
                "        userId,\n" +
                "        SUM(total_savings) AS total_savings\n" +
                "    FROM\n" +
                "        Finances\n" +
                "    GROUP BY\n" +
                "        userId) F\n" +
                "ON\n" +
                "    U.id = F.userId\n" +
                "LEFT JOIN\n" +
                "    (SELECT\n" +
                "        userId,\n" +
                "        SUM(saved_amount) AS total_goal_savings\n" +
                "    FROM\n" +
                "        Goals\n" +
                "    GROUP BY\n" +
                "        userId) G\n" +
                "ON\n" +
                "    U.id = G.userId;");

        return createViewQueries;
    }

    public static Map<String, String> createTableDataQueries() {
        Map<String, String> tableDataQueries = new HashMap<>();

        tableDataQueries.put("Users", "INSERT INTO Users (email, password, first_name, last_name, phone, dob, gender) VALUES\n" +
                "('eren.atilgan@student.uts.edu.au', 'password', 'Eren', 'Atilgan', '1234567891', '01/01/2002', 'Male'),\n" +
                "('alyssa.j.rodriguez@student.uts.edu.au', 'password', 'Alyssa', 'Rodriguez', '1234567892', '01/01/2002', 'Female'),\n" +
                "('haigh.minassian@student.uts.edu.au', 'password', 'Haigh', 'Minassian', '1234567893', '01/01/2002', 'Male'),\n" +
                "('tuan.n.le-1@student.uts.edu.au', 'password', 'Tuan', 'Le', '1234567894', '01/01/2002', 'Male'),\n" +
                "('Sejin.Um-1@student.uts.edu.au', 'password', 'Sejin', 'Um', '1234567895', '01/01/2002', 'Female'),\n" +
                "('eren.atilgan2@student.uts.edu.au', 'password', 'Eren', 'Atilgan', '1234567896', '01/01/2002', 'Male');");

        tableDataQueries.put("Incomes", "INSERT INTO Incomes (userId, name, amount, category, date, frequency) VALUES\n" +
                "(1, 'Shoes', 100, 'Cash Sale', '01/01/23', 'one-off'),\n" +
                "(1, 'Shoes', 200, 'Cash Sale', '01/01/23', 'one-off'),\n" +
                "(1, 'Cyber Job', 200000, 'Salary', '01/01/23', 'Yearly'),\n" +
                "(2, 'Data Job', 300000, 'Salary', '01/01/23', 'Yearly'),\n" +
                "(2, 'Shoes', 80, 'Cash Sale', '01/01/23', 'one-off');");

        tableDataQueries.put("Expenses", "INSERT INTO Expenses (userId, expenseName, amount, category, date) VALUES\n" +
                "(1, 'Shoes', 500, 'Other', '01/01/23'),\n" +
                "(1, 'Food', 4500, 'Food', '01/01/23'),\n" +
                "(2, 'Shoes', 808, 'Other', '01/01/23'),\n" +
                "(2, 'Drugs (Legal)', 9000, 'Other', '01/01/23');");

        tableDataQueries.put("Deductions", "INSERT INTO Deductions (userId, name, amount, category, date, frequency) VALUES\n" +
                "(1, 'Work Shoes', 50, 'Work Related Purchase', '01/01/23', 'one-off'),\n" +
                "(1, 'Work Shoes', 80, 'Work Related Purchase', '01/01/23', 'one-off'),\n" +
                "(2, 'Work Shoes', 100, 'Work Related Purchase', '01/01/23', 'one-off'),\n" +
                "(2, 'Work Shoes', 110, 'Work Related Purchase', '01/01/23', 'one-off');");

        tableDataQueries.put("Goals", "INSERT INTO Goals (userId, name, goal_amount, saved_amount, category, description, date) VALUES\n" +
                "(1, 'Hawaii Trip', 20000, 15000, 'Holiday', 'Hawaii Holiday with the family.', '10/10/2023'),\n" +
                "(2, 'Kitchen Remodel', 50000, 20000, 'Home', 'Kitchen Remodelling', '13/11/2023'),\n" +
                "(3, 'Gaming Setup', 3000, 800, 'Other', 'New PC Setup', '25/12/2023'),\n" +
                "(4, '21st Birthday', 1200, 350, 'Partying', 'Finally able to drink', '19/03/2024'),\n" +
                "(1, 'Turkey Trip', 10000, 4200, 'Holiday', 'Turkey Holiday with the family.', '10/10/2023');");

        tableDataQueries.put("Bills", "INSERT INTO Bills (userId, amount, category, date, frequency) VALUES\n" +
                "(1, 250, 'Electricity', '01/01/23', 'Quarterly'),\n" +
                "(2, 60, 'Phone Bill', '01/01/23', 'Monthly'),\n" +
                "(3, 100, 'Internet Bill', '01/01/23', 'Monthly'),\n" +
                "(4, 110, 'Car Registration', '01/01/23', 'Yearly');");

        tableDataQueries.put("Tips", "INSERT INTO Tips (name, description) VALUES\n" +
                "('Stock Diversity', 'You should always try to avoid investing into only one stock option. Instead, opt for investing into multiple stocks to lower risk and to raise gain.'),\n" +
                "('Budgeting Is Important', 'Budgeting is always a great way to manage your finances and to allocate funds to what really matters!');");

        return tableDataQueries;
    }

    public static void closeConnection() {
        try {
            System.out.println("Closing connection...");
            if (conn != null) {
                conn.close();
            }
            System.out.println("Successfully closed connection.");
        } catch (Exception e) {
            System.out.println("Failed to close connection: " + e);
        }
    }
}
