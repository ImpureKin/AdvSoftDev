import controller.MoneySavingController;
import controller.PaymentController;
import database.ConnectionManager;
import database.DatabaseManager;
import database.MoneySavingManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestMoneySavings {
  static DatabaseManager dm = new DatabaseManager();

  static MoneySavingController moneySavingController = new MoneySavingController();
  static Logger logger = Logger.getLogger(TestMoneySavings.class.getName());

  Connection conn;

  @BeforeAll
  public static void initialiseDatabase() {
    try {
      dm.resetDatabase();
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Failed resetting Database: ", e);
    }
  }

  @AfterEach
  public void resetDatabase() {
    try {
      ConnectionManager.closeConnection(conn);
      dm.resetDatabase();
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Failed resetting Database: ", e);
    }
  }

  @Test
  public void paymentValidSaveWithTotalAmountInvalid() {
    try {
      logger.log(Level.INFO, "Beginning paymentValidSaveWithTotalAmountInvalid test.");
      String name = "Money total 1";
      String totalAmount = "invalid amount demo";
      String interest = "1.5";
      String totalMonth = "3";

      List<String> messages = moneySavingController.validMoneySaving(name, totalAmount, interest, totalMonth);
      if (messages.isEmpty()) {
        logger.log(Level.INFO, "Successfully performed paymentValidSaveWithTotalAmountInvalid test.");
      } else {
        logger.log(Level.INFO, "Failed paymentValidSaveWithTotalAmountInvalid test.");
      }
      ConnectionManager.closeConnection(conn);
    } catch (Exception e) {
      ConnectionManager.closeConnection(conn);
      logger.log(Level.SEVERE, "Failed paymentValidSaveWithTotalAmountInvalid test: ", e);
    }
  }

  @Test
  public void paymentValidSaveWithTotalMonthInvalid() {
    try {
      logger.log(Level.INFO, "Beginning paymentValidSaveWithTotalMonthInvalid test.");
      String name = "Money total 2";
      String totalAmount = "200";
      String interest = "3.0";
      String totalMonth = "valid total month";

      List<String> messages = moneySavingController.validMoneySaving(name, totalAmount, interest, totalMonth);
      if (messages.isEmpty()) {
        logger.log(Level.INFO, "Successfully performed paymentValidSaveWithTotalMonthInvalid test.");
      } else {
        logger.log(Level.INFO, "Failed paymentValidSaveWithTotalMonthInvalid test.");
      }
      ConnectionManager.closeConnection(conn);
    } catch (Exception e) {
      ConnectionManager.closeConnection(conn);
      logger.log(Level.SEVERE, "Failed paymentValidSaveWithTotalMonthInvalid test: ", e);
    }
  }

  @Test
  public void paymentValidSaveWithNameInvalid() {
    try {
      logger.log(Level.INFO, "Beginning paymentValidSaveWithNameInvalid test.");
      String name = "";
      String totalAmount = "200";
      String interest = "3.0";
      String totalMonth = "3";

      List<String> messages = moneySavingController.validMoneySaving(name, totalAmount, interest, totalMonth);
      if (messages.isEmpty()) {
        logger.log(Level.INFO, "Successfully performed paymentValidSaveWithNameInvalid test.");
      } else {
        logger.log(Level.INFO, "Failed paymentValidSaveWithNameInvalid test.");
      }
      ConnectionManager.closeConnection(conn);
    } catch (Exception e) {
      ConnectionManager.closeConnection(conn);
      logger.log(Level.SEVERE, "Failed paymentValidSaveWithNameInvalid test: ", e);
    }
  }

  @Test
  public void deleteMoneySavingsInValid() {
    try {
      logger.log(Level.INFO, "Beginning deleteMoneySavingsInValid test.");

      String email = "DemoEmail@gmail.com";

      if(!email.isEmpty()) {
        logger.log(Level.INFO, "Successfully performed deleteMoneySavingsInValid test.");
      } else {
        logger.log(Level.INFO, "Failed deleteMoneySavingsInValid test.");
      }

      conn = ConnectionManager.getConnection();
      MoneySavingManager.deleteAll(conn, email);

    } catch (Exception e) {
      logger.log(Level.SEVERE, "Failed deleteMoneySavingsInValid test: ", e);
    }
  }
}