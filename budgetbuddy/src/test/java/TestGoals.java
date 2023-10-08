

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.Goals;
import database.*;

class TestGoals {

    Connection connection;

    @BeforeEach
    public void setUp() {
            // Reset the database before each test
            DatabaseManager.resetDatabase();
        }

    @AfterEach
    public void cleanup() throws SQLException {
        //Reset the database before each test
        if (connection != null) {
            connection.close();
        }
        DatabaseManager.resetDatabase();
    }

    @Test
    void testGetGoalsByUserId() {
        try {
            // Create a connection
            connection = ConnectionManager.getConnection();

            // Test the method
            List<Goals> goals = GoalsManager.getGoalsByUserId(connection, 1);

            // Verify the result
            assertNotNull(goals);
            assertEquals(2, goals.size()); // Assuming there are two goals for user 1 in your test data

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            fail("SQL Exception thrown: " + e.getMessage());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    void testGetGoalById() {
        try {
            // Test the method
            Goals goal = GoalsManager.getGoalById(1);

            // Verify the result
            assertNotNull(goal);
            assertEquals(1, goal.getUserId()); // Assuming goal with id 1 exists in your test data

        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }


    @Test
    void testUpdateGoal() {
        try {
            // Test the method
            GoalsManager.updateGoal(1, "New Goal Name", "New Goal Description", 5000);

            // Verify the result by retrieving the updated goal
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

