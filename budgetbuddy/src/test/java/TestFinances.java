
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import model.*;
import database.*;


// Used to test the Finances Manager to make sure the queries are working as expected 
class TestFinances {

    Connection connection;
    static DatabaseManager dm = new DatabaseManager();
    static Logger logger = Logger.getLogger(TestFinances.class.getName());

    @BeforeAll
    public static void initialiseDatabase() {
        try {
            dm.resetDatabase();
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed resetting Database: ", e);
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

    // Tests GetFinancesByUserId function
    @Test
    public void testGetFinancesByUserId() {
        try {
            // Establishing connection
            connection = ConnectionManager.getConnection();

            // Tests the method
            Finances finances = FinancesManager.getFinancesByUserId(connection, 1);

            // Verifies the result
            assertNotNull(finances);
            assertEquals(200300, finances.getTotalIncome());
            assertEquals(130, finances.getTotalDeductions());
            assertEquals(5000, finances.getTotalExpenses());
            assertEquals(195170, finances.getTotalSavings());
            
            // Closes connection
            ConnectionManager.closeConnection(connection);
        } catch (SQLException e) {
            ConnectionManager.closeConnection(connection);
            fail("SQL Exception thrown: " + e.getMessage());
        } catch (Exception e) {
            ConnectionManager.closeConnection(connection);
            fail("Exception thrown: " + e.getMessage());
        }
    }

    // Tests GetTotalUserSaving function 
    @Test
    public void testGetTotalUserSavings() {
        try {
            // Establishing connection
            connection = ConnectionManager.getConnection();

            // Tests the method
            TotalUserSavings totalUserSavings = FinancesManager.getTotalUserSavings(connection, 1);

            // Verifies the result
            assertNotNull(totalUserSavings);
            assertEquals(195170, totalUserSavings.getTotalSavings());
            assertEquals(19200, totalUserSavings.getTotalGoalSavings());
            assertEquals(175970, totalUserSavings.getTotalSaved());
            
            // Closes connection
            ConnectionManager.closeConnection(connection);
        } catch (SQLException e) {
            ConnectionManager.closeConnection(connection);
            fail("SQL Exception thrown: " + e.getMessage());
        } catch (Exception e) {
            ConnectionManager.closeConnection(connection);
            fail("Exception thrown: " + e.getMessage());
        }
    }
}