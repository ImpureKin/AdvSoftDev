package controller;

import database.ConnectionManager;
import database.MoneySavingManager;
import model.MoneySavings;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MoneySavingController {

  Connection conn;

  public List<MoneySavings> getListMoneySaving(String userId) throws Exception {
    conn = ConnectionManager.getConnection();
    return MoneySavingManager.getListMoneySaving(conn, userId);
  }

  public List<String> validMoneySaving(String name, String totalAmount, String interest, String totalMonth) {
    List<String> statusMessage = new ArrayList<>();
    if (isEmpty(name)) {
      statusMessage.add("Please fill name");
    }
    if (isEmpty(totalAmount)) {
      statusMessage.add("Please fill totalAmount");
    }
    if (isEmpty(interest)) {
      statusMessage.add("Please fill interest");
    }
    if (isEmpty(totalMonth)) {
      statusMessage.add("Please fill totalMonth.");
    }
    if (isEmpty(totalAmount)) {
      statusMessage.add("Please enter a valid totalAmount.");
    }
    if (isEmpty(totalMonth)) {
      statusMessage.add("Please enter a valid totalMonth.");
    }

    return statusMessage;
  }

  public List<String> saveMoneySaving(String name, String totalAmount, String interest, String totalMonth,
                                      String userId) throws Exception {
    conn = ConnectionManager.getConnection();
    List<String> statusMessage = validMoneySaving(name, totalAmount, interest, totalMonth);

    if (!statusMessage.isEmpty()) {
      return statusMessage;
    }

    try {
      MoneySavingManager.saveMoneySavings(conn, name, totalAmount, interest, totalMonth, userId);
    } catch (Exception e) {
      ConnectionManager.closeConnection(conn);
      System.out.println("Connection Failed: " + e);
      throw e;
    }
    ConnectionManager.closeConnection(conn);

    return statusMessage;
  }

  public boolean isEmpty(String value) {
    return value == null || value.trim().isEmpty();
  }

  public void deleteAll(String currentEmail) {
    try {
      conn = ConnectionManager.getConnection();
      MoneySavingManager.deleteAll(conn, currentEmail);
    } catch (Exception e) {
      System.out.println("Connection Failed: " + e);
    }
  }

  public Boolean isNumber(String input) {
    return input.matches("[^0-9]");
  }
}