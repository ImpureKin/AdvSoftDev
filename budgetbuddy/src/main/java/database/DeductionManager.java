package database;

import model.Deductions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class DeductionManager {

    // Initialize the deductions table
    public static void initializeDatabase(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Deductions ("
                              + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                              + "userId INTEGER NOT NULL,"
                              + "name TEXT NOT NULL,"
                              + "amount REAL NOT NULL,"
                              + "category TEXT NOT NULL,"
                              + "date DATE NOT NULL,"
                              + "frequency TEXT,"             
                              + "invoice_date TEXT"           
                              + ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    // Add a new deduction
    public static void addDeduction(Connection connection, Deductions deduction, int userId) throws SQLException {
        String sql = "INSERT INTO Deductions (name, userId, amount, category, date, frequency, invoice_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, deduction.getname());
            pstmt.setInt(2, userId);
            pstmt.setDouble(3, deduction.getAmount());
            pstmt.setString(4, deduction.getCategory());
            pstmt.setDate(5, new java.sql.Date(deduction.getDate().getTime()));
            pstmt.setString(6, deduction.getFrequency());      // Make sure to add getFrequency in Deductions model.
            pstmt.setString(7, deduction.getInvoiceDate());   // Make sure to add getInvoiceDate in Deductions model.
            
            pstmt.executeUpdate();
        }
    }

    // Retrieve all deductions
    public static List<Deductions> getAllDeductions(Connection connection) throws SQLException {
        List<Deductions> deductionsList = new ArrayList<>();
        String sql = "SELECT * FROM Deductions";
    
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
    
            // Create a SimpleDateFormat object for date parsing
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
            while (rs.next()) {
                Deductions deduction = new Deductions();
                deduction.setId(rs.getInt("id"));
                deduction.setname(rs.getString("name"));
                deduction.setAmount(rs.getDouble("amount"));
                deduction.setCategory(rs.getString("category"));
                deduction.setFrequency(rs.getString("frequency"));   
                deduction.setInvoiceDate(rs.getString("invoice_date"));
    
                String dateString = rs.getString("date");
                try {
                    Date date;
                    if (dateString.matches("^\\d+$")) { // if it's a long integer (timestamp)
                        date = new Date(Long.parseLong(dateString)); // convert it to a date
                    } else {
                        date = dateFormat.parse(dateString); // else, use SimpleDateFormat to parse it
                    }
                    deduction.setDate(date);
                    
                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                }
    
                deductionsList.add(deduction);
            }
        }
    
        return deductionsList;
    }

    // Remove a specific deduction
    public static void removeDeduction(Connection connection, int deductionId) throws SQLException {
        String sql = "DELETE FROM Deductions WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, deductionId);
            pstmt.executeUpdate();
        }
    }

    // ... You can add more CRUD operations like update, get by ID, etc. ...
}