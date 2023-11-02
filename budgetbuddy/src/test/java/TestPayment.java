import controller.PaymentController;
import database.ConnectionManager;
import database.DatabaseManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestPayment {
  static DatabaseManager dm = new DatabaseManager();

  static PaymentController paymentController = new PaymentController();
  static Logger logger = Logger.getLogger(TestPayment.class.getName());

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
  public void paymentValidSaveWithAmountInvalid() {
    try {
      logger.log(Level.INFO, "Beginning paymentValidSaveWithAmountInvalid test.");
      String name = "Join";
      String amount = "";
      String dob = "24/08/2000";

      List<String> messages = paymentController.isValidPayment(name, amount, dob);
      if (messages.isEmpty()) {
        logger.log(Level.INFO, "Successfully performed paymentValidSaveWithAmountInvalid test.");
      } else {
        logger.log(Level.INFO, "Failed paymentValidSaveWithAmountInvalid test.");
      }
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Failed paymentValidSaveWithAmountInvalid test: ", e);
    }
  }

  @Test
  public void paymentValidSaveWithNameInvalid() {
    try {
      logger.log(Level.INFO, "Beginning paymentValidSaveWithNameInvalid test.");
      String name = "";
      String amount = "200";
      String dob = "24/08/2000";

      List<String> messages = paymentController.isValidPayment(name, amount, dob);
      if (messages.isEmpty()) {
        logger.log(Level.INFO, "Successfully performed paymentValidSaveWithNameInvalid test.");
      } else {
        logger.log(Level.INFO, "Failed paymentValidSaveWithNameInvalid test.");
      }
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Failed paymentValidSaveWithNameInvalid test: ", e);
    }
  }

  @Test
  public void amountValid() {
    try {
      logger.log(Level.INFO, "Beginning amountValid test.");
      String amount = "200";
      boolean res = paymentController.isValidAmount(amount);
      if (res) {
        logger.log(Level.SEVERE, "Successfully amountValid test.");
      } else {
        logger.log(Level.SEVERE, "Failed amountValid test.");
      }
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Failed amountValid test: ", e);
    }
  }

  @Test
  public void nameValid() {
    try {
      logger.log(Level.INFO, "Beginning nameValid test.");
      String name = "";
      boolean res = paymentController.isEmpty(name);
      if (!res) {
        logger.log(Level.SEVERE, "Successfully nameValid test.");
      } else {
        logger.log(Level.SEVERE, "Failed nameValid test.");
      }
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Failed nameValid test: ", e);
    }
  }
}