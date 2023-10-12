package database;

import model.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentManager {
  public static String savePayment(Connection conn, String name, String amount, String dob) {
    try {
      String query = "INSERT INTO Payments (name, amount, dob) VALUES (?, ?, ?);";
      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, name);
      pstmt.setString(2, amount);
      pstmt.setString(3, dob);
      pstmt.executeUpdate();
      System.out.println("Successfully save payment: " + name + " " + amount + " " + dob);
      return "Success";
    } catch (SQLException e) {
      System.out.println("Save payment error: " + e);
      return "Failed. " + e;
    }
  }

  public static List<Payment> getListPayments(Connection conn) {
    List<Payment> output = new ArrayList<>();
    try {
      String query = "SELECT * FROM Payments";
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        output.add(new Payment(rs.getString(2), rs.getString(3), rs.getString(4)));
      }

      rs.close();
      stmt.close();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

    return output;
  }
}
