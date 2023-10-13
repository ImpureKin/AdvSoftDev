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

  public List<String> isValidPayment(String name, String amount, String dob) {
    List<String> statusMessage = new ArrayList<>();
    if (isEmpty(name)) {
      statusMessage.add("Please fill name");
    }
    if (isEmpty(amount)) {
      statusMessage.add("Please fill amount");
    }
    if (isEmpty(dob)) {
      statusMessage.add("Please fill dob");
    }
    if (isValidAmount(amount)) {
      statusMessage.add("Please enter a valid amount.");
    }
    return statusMessage;
  }

  public List<String> savePayment(String name, String amount, String dob) throws Exception {
    conn = ConnectionManager.getConnection();
    List<String> statusMessage = isValidPayment(name, amount, dob);

    if (!statusMessage.isEmpty()) {
      return statusMessage;
    }

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

  public Boolean isValidAmount(String amount) {
    return amount.matches("[^0-9]");
  }

  public boolean isEmpty(String value) {
    return value == null || value.trim().isEmpty();
  }

}