import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.DeductionsController;
import database.ConnectionManager;
import database.DatabaseManager;
import database.DeductionManager;
import model.Deductions;

public class TestDeductions {

    static DeductionManager deductionManager = new DeductionManager();
    static DeductionsController deductionsController = new DeductionsController();
    static DatabaseManager dm = new DatabaseManager();

    static Logger logger = Logger.getLogger(TestDeductions.class.getName());


    Connection conn;

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
            ConnectionManager.closeConnection(conn);
            dm.resetDatabase();
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed resetting Database: ", e);
        }
    }

    @Test
    public void addDeductionTest() {
        try {
            logger.log(Level.INFO, "Beginning Test: addDeductionTest.");
            conn = ConnectionManager.getConnection();
            
            Deductions newDeduction = new Deductions();
            newDeduction.setUserId(1); // This is for testing, assuming there's a user with ID=1
            newDeduction.setname("Food");
            newDeduction.setAmount(50.0);
            newDeduction.setCategory("Other");
            newDeduction.setFrequency("Monthly");
            java.util.Date date = new java.util.Date();
            newDeduction.setDate(date);
            newDeduction.setInvoiceDate("10-10-2023"); // Assuming a random date here

            DeductionManager.addDeduction(conn, newDeduction, 1);
            
            // Check if the deduction was added
            List<Deductions> deductionsList = DeductionManager.getAllDeductions(conn);
            boolean foundDeduction = deductionsList.stream().anyMatch(ded -> ded.getname().equals("Food"));
            
            assertTrue(foundDeduction);
            
            logger.log(Level.INFO, "Successfully Performed Test: addDeductionTest.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed Test: addDeductionTest", e);
        }
    }

    // @Test
    // public void retrieveAllDeductionsTest() {
    //     try {
    //         logger.log(Level.INFO, "Beginning Test: retrieveAllDeductionsTest.");

    //         List<Deductions> deductionsList = DeductionManager.getAllDeductions(conn);
            
    //         assertNotNull(deductionsList);
    //         assertTrue(!deductionsList.isEmpty());
            
    //         logger.log(Level.INFO, "Successfully Performed Test: retrieveAllDeductionsTest."); 
    //     } catch (Exception e) {
    //         logger.log(Level.SEVERE, "Failed Test: retrieveAllDeductionsTest", e);
    //     }
    // }

    // @Test
    // public void removeDeductionTest() {
    //     try {
    //         logger.log(Level.INFO, "Beginning Test: removeDeductionTest.");

    //         // Assuming an deduction with ID 1 exists for removal
    //         DeductionManager.removeDeduction(conn, 1);

    //         // Check if the deduction was removed
    //         Deductions removedDeduction = DeductionManager.getDeductionById(conn, 1); // Assuming you have such a method
    //         assertTrue(removedDeduction == null);

    //         logger.log(Level.INFO, "Successfully Performed Test: removeDeductionTest.");
    //     } catch (Exception e) {
    //         logger.log(Level.SEVERE, "Failed Test: removeDeductionTest", e);
    //     }
    // }
}