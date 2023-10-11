package controller;

import database.ConnectionManager;
import database.PaymentManager;
import model.Payment;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PaymentController {

  Connection conn;

  public List<Payment> getListPayments() throws Exception {
    conn = ConnectionManager.getConnection();
    return PaymentManager.getListPayments(conn);
  }

  public List<String> savePayment(String name, String amount, String dob) throws Exception {
    conn = ConnectionManager.getConnection();
    List<String> statusMessage = new ArrayList<>();
    System.out.println(name + " " + amount + " " + dob);
    if (isEmpty(name) || isEmpty(amount) || isEmpty(dob)) {
      statusMessage.add("Please fill in all fields.");
      return statusMessage;
    }

    // Check if amount is valid
    if (isValidAmount(amount)) {
      statusMessage.add("Please enter a valid amount.");
      return statusMessage;
    }

    // Save payment in Database
    try {
      PaymentManager.savePayment(conn, name, amount, dob);
    } catch (Exception e) {
      ConnectionManager.closeConnection(conn);
      System.out.println("Connection Failed: " + e);
      throw e;
    }
    ConnectionManager.closeConnection(conn);

    return statusMessage;
  }

  private Boolean isValidAmount(String amount) {
    return amount.matches("[^0-9]");
  }

  public boolean isEmpty(String value) {
    return value == null || value.trim().isEmpty();
  }

}
