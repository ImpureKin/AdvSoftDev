

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Goals;
import database.*;

// Used to tets the Goals Manager to make sure the queries are working correctly 
class TestGoals {

    Connection connection;
    private int initialSavedAmount;
    static DatabaseManager dm = new DatabaseManager();
    static Logger logger = Logger.getLogger(TestGoals.class.getName());

    @BeforeAll
    public static void initialiseDatabase() {
        try {
            dm.resetDatabase();
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed resetting Database: ", e);
        }
    }

    @BeforeEach
    public void setUp() {
        // Retrives the amount after the databas is made. This is for a specific test
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement initialPstmt = connection.prepareStatement("SELECT saved_amount FROM Goals WHERE id = ?");
            initialPstmt.setInt(1, 1);
            ResultSet initialResult = initialPstmt.executeQuery();
            initialResult.next();
            initialSavedAmount = initialResult.getInt("saved_amount");
            ConnectionManager.closeConnection(connection);
        } catch (SQLException e) {
            ConnectionManager.closeConnection(connection);
            fail("SQL Exception thrown: " + e.getMessage());
        } catch (Exception e) {
            ConnectionManager.closeConnection(connection);
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @AfterEach
    public void resetDatabase() {
        try {
            ConnectionManager.closeConnection(connection);
            dm.resetDatabase();
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed resetting Database: ", e);
        }
    }

    // Tests GetGoalsByUserId function
    @Test
    void testGetGoalsByUserId() {
        try {
            // Creates a connection
            connection = ConnectionManager.getConnection();

            // Tests the method
            List<Goals> goals = GoalsManager.getGoalsByUserId(connection, 1);

            // Verifies the result
            assertNotNull(goals);
            assertEquals(2, goals.size()); 

            // Closes the connection
            ConnectionManager.closeConnection(connection);
        } catch (SQLException e) {
            ConnectionManager.closeConnection(connection);
            fail("SQL Exception thrown: " + e.getMessage());
        } catch (Exception e) {
            ConnectionManager.closeConnection(connection);
            fail("Exception thrown: " + e.getMessage());
        }
    }

    // Tests UpdateSavedAmount function 
    @Test
    void testUpdateSavedAmount() {
        try {
            // Creates a connection
            connection = ConnectionManager.getConnection();

            // Updates the saved amount
            GoalsManager.updateSavedAmount(1, 100);

            // Gets the updated saved amount
            PreparedStatement updatedPstmt = connection.prepareStatement("SELECT * FROM Goals WHERE id = ?");
            updatedPstmt.setInt(1, 1);
            ResultSet updatedResult = updatedPstmt.executeQuery();
            updatedResult.next();
            int updatedSavedAmount = updatedResult.getInt("saved_amount");

            // Prints updated saved amount
            System.out.println("Updated Saved Amount: " + updatedSavedAmount);

            // Verifies the result
            assertEquals(initialSavedAmount + 100, updatedSavedAmount);

            // Closes the connection
            ConnectionManager.closeConnection(connection);
        } catch (SQLException e) {
            ConnectionManager.closeConnection(connection);
            fail("SQL Exception thrown: " + e.getMessage());
        } catch (Exception e) {
            ConnectionManager.closeConnection(connection);
            fail("Exception thrown: " + e.getMessage());
        }
    }

    // Tests the GetGoalById function
    @Test
    void testGetGoalById() {
        try {
            // Tests the method
            Goals goal = GoalsManager.getGoalById(1);

            // Verifies the result
            assertNotNull(goal);
            assertEquals(1, goal.getUserId()); 

        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    // Tests the UpdateGoal function
    @Test
    void testUpdateGoal() {
        try {
            // Tests the method
            GoalsManager.updateGoal(1, "New Goal Name", "New Goal Description", 5000);

            // Verifies the result by retrieving the updated goal
            Goals updatedGoal = GoalsManager.getGoalById(1);

            assertNotNull(updatedGoal);
            assertEquals("New Goal Name", updatedGoal.getName());
            assertEquals("New Goal Description", updatedGoal.getDescription());
            assertEquals(5000, updatedGoal.getGoalAmount());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
