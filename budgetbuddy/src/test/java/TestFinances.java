
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.*;
import database.*;



class TestFinances {

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
    public void testGetFinancesByUserId() {
        try {
            // Establish connection
            connection = ConnectionManager.getConnection();

            // Test the method
            Finances finances = FinancesManager.getFinancesByUserId(connection, 1);

            // Verify the result
            assertNotNull(finances);
            assertEquals(200300, finances.getTotalIncome());
            assertEquals(130, finances.getTotalDeductions());
            assertEquals(5000, finances.getTotalExpenses());
            assertEquals(195170, finances.getTotalSavings());
            
            // Close connection
            connection.close();
        } catch (SQLException e) {
            fail("SQL Exception thrown: " + e.getMessage());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    

    @Test
    public void testGetTotalUserSavings() {
        try {
            // Establish connection
            connection = ConnectionManager.getConnection();

            // Test the method
            TotalUserSavings totalUserSavings = FinancesManager.getTotalUserSavings(connection, 1);

            // Verify the result
            assertNotNull(totalUserSavings);
            assertEquals(195170, totalUserSavings.getTotalSavings());
            assertEquals(19200, totalUserSavings.getTotalGoalSavings());
            assertEquals(175970, totalUserSavings.getTotalSaved());
            
            // Close connection
            connection.close();
        } catch (SQLException e) {
            fail("SQL Exception thrown: " + e.getMessage());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}