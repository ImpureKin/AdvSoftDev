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

  public List<String> saveMoneySaving(String name, String totalAmount, String interest, String totalMonth,
                                      String userId) throws Exception {
    conn = ConnectionManager.getConnection();
    List<String> statusMessage = new ArrayList<>();
    System.out.println(name + " " + totalAmount + " " + interest);
    if (isEmpty(name) || isEmpty(totalAmount) || isEmpty(interest) || isEmpty(totalMonth)) {
      statusMessage.add("Please fill in all fields.");
      return statusMessage;
    }

    // Save payment in Database
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
}
