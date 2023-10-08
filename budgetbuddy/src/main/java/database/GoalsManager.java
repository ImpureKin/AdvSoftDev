package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Goals;

public class GoalsManager {
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
            Connection connection = ConnectionManager.getConnection();
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
            Connection connection = ConnectionManager.getConnection();
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
            Connection connection = ConnectionManager.getConnection();
    
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
    


    public static void updateGoal(int goalId, String goalName, String goalDescription, int goalAmount) throws Exception {
        try (Connection connection = ConnectionManager.getConnection()) {
            String query = "UPDATE Goals SET name=?, description=?, goal_amount=? WHERE id=?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, goalName);
            pstmt.setString(2, goalDescription);
            pstmt.setInt(3, goalAmount);
            pstmt.setInt(4, goalId);
    
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception, log it, or throw a custom exception if needed
        }
    }
    
}