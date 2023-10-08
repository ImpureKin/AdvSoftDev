package database;

import model.Expenses;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ExpenseManager {

    public static void initializeDatabase(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Expenses ("
                              + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                              + "expenseName TEXT NOT NULL,"
                              + "amount REAL NOT NULL,"
                              + "category TEXT NOT NULL,"
                              + "date DATE NOT NULL"
                              + ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }


    public static void addExpense(Connection connection, Expenses expense) throws SQLException {
        String sql = "INSERT INTO Expenses (expenseName, amount, category, date) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, expense.getExpenseName());
            pstmt.setDouble(2, expense.getAmount());
            pstmt.setString(3, expense.getCategory());
            pstmt.setDate(4, new java.sql.Date(expense.getDate().getTime()));
            
            pstmt.executeUpdate();
        }
    }

    public static List<Expenses> getAllExpenses(Connection connection) throws SQLException, ParseException {
        List<Expenses> expensesList = new ArrayList<>();
        String sql = "SELECT * FROM Expenses";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Expenses expense = new Expenses();
                expense.setId(rs.getInt("id"));
                expense.setExpenseName(rs.getString("expenseName"));
                expense.setAmount(rs.getDouble("amount"));
                expense.setCategory(rs.getString("category"));
                long timestamp = rs.getLong("date");
                Date date = new Date(timestamp);
                expense.setDate(date);
                
                expensesList.add(expense);
            }
        }

        //Logging statement for point 3
        System.out.println("Total records fetched from database: " + expensesList.size());


        return expensesList;
    }

    // ... [other CRUD operations like delete, update, etc.] ...
}