
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

    static DatabaseManager dm = new DatabaseManager();
    static Logger logger = Logger.getLogger(TestFinances.class.getName());

    static Connection connection;

    @BeforeAll
    public static void initialiseDatabase() {
        try {
            connection = ConnectionManager.resetTestConnection(connection);
            dm.resetDatabase(connection);
            connection = ConnectionManager.resetTestConnection(connection);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed resetting Database: ", e);
        }
    }

    @AfterEach
    public void resetDatabase() {
        try {
            connection = ConnectionManager.resetTestConnection(connection);
            dm.resetDatabase(connection);
            connection = ConnectionManager.resetTestConnection(connection);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed resetting Database: ", e);
        }
    }

    // Tests GetFinancesByUserId function
    @Test
    public void testGetFinancesByUserId() {
        try {
            // Tests the method
            Finances finances = FinancesManager.getFinancesByUserId(connection, 1);

            // Verifies the result
            assertNotNull(finances);
            assertEquals(200300, finances.getTotalIncome());
            assertEquals(130, finances.getTotalDeductions());
            assertEquals(5000, finances.getTotalExpenses());
            assertEquals(195170, finances.getTotalSavings());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    // Tests GetTotalUserSaving function 
    @Test
    public void testGetTotalUserSavings() {
        try {
            // Tests the method
            TotalUserSavings totalUserSavings = FinancesManager.getTotalUserSavings(connection, 1);

            // Verifies the result
            assertNotNull(totalUserSavings);
            assertEquals(195170, totalUserSavings.getTotalSavings());
            assertEquals(19200, totalUserSavings.getTotalGoalSavings());
            assertEquals(175970, totalUserSavings.getTotalSaved());
        } catch (SQLException e) {
            fail("SQL Exception thrown: " + e.getMessage());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}