package database;
import model.Finances;
import model.TotalUserSavings;
import java.sql.*;

public class FinancesManager {

    // Retrives all financial data for the user logged in
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

    //Retrives all financial data relating to savings for the user logged in
    public static TotalUserSavings getTotalUserSavings(Connection connection, int userId) throws SQLException {
        TotalUserSavings totalUserSavings = null;
        String query = "SELECT * FROM TotalUserSavings WHERE User_ID = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int totalSavings = rs.getInt("total_savings");
                    int totalGoalSavings = rs.getInt("total_goal_savings");
                    int totalSaved = rs.getInt("total_saved");
                    totalUserSavings = new TotalUserSavings(userId, totalSavings, totalGoalSavings, totalSaved);
                }
            }
        }
        
        return totalUserSavings;
    }
    
    
}
