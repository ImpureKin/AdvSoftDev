import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Expenses;
import controller.ExpensesController;
import database.ExpenseManager;
import database.ConnectionManager;
import database.DatabaseManager;

public class TestExpenses {

    static ExpenseManager ExpenseManager = new ExpenseManager();
    static ExpensesController expensesController = new ExpensesController();
    static DatabaseManager dm = new DatabaseManager();

    static Logger logger = Logger.getLogger(TestExpenses.class.getName());

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

    @Test
    public void addExpenseTest() {
        try {
            logger.log(Level.INFO, "Beginning Test: addExpenseTest.");
            
            Expenses newExpense = new Expenses();
            newExpense.setUserId(1); // This is for testing, assuming there's a user with ID=1
            newExpense.setExpenseName("Test Expense");
            newExpense.setAmount(100.0);
            newExpense.setCategory("other");
            java.util.Date date = new java.util.Date();
            newExpense.setDate(date);

            database.ExpenseManager.addExpense(connection, newExpense, 1);
            
            // Check if the expense was added
            List<Expenses> expensesList = database.ExpenseManager.getAllExpenses(connection);
            boolean foundExpense = expensesList.stream().anyMatch(exp -> exp.getExpenseName().equals("Test Expense"));
            
            assertTrue(foundExpense);
            
            logger.log(Level.INFO, "Successfully Performed Test: addExpenseTest.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed Test: addExpenseTest", e);
        }
    }

    @Test
    public void retrieveAllExpensesTest() {
        try {
            logger.log(Level.INFO, "Beginning Test: retrieveAllExpensesTest.");

            List<Expenses> expensesList = database.ExpenseManager.getAllExpenses(connection);
            
            assertNotNull(expensesList);
            assertTrue(!expensesList.isEmpty());
            
            logger.log(Level.INFO, "Successfully Performed Test: retrieveAllExpensesTest."); 
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed Test: retrieveAllExpensesTest", e);
        }
    }

    // @Test
    // public void deleteExpenseTest() {
    //     try {
    //         logger.log(Level.INFO, "Beginning Test: deleteExpenseTest.");

    //         // Assuming an expense with ID 1 exists for deletion
    //         database.ExpenseManager.deleteExpense(conn, 1);

    //         // Check if the expense was deleted
    //         Expenses deletedExpense = ExpenseManager.getExpenseById(conn, 1);
    //         assertTrue(deletedExpense == null);

    //         logger.log(Level.INFO, "Successfully Performed Test: deleteExpenseTest.");
    //     } catch (Exception e) {
    //         logger.log(Level.SEVERE, "Failed Test: deleteExpenseTest", e);
    //     }
    // }
}