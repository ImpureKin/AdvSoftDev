package database;

import model.MoneySavings;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MoneySavingManager {
  public static List<MoneySavings> getListMoneySaving(Connection conn, String userId) {
    List<MoneySavings> output = new ArrayList<>();
    try {
      String query = "SELECT * FROM MoneySavings WHERE userId = " + "\'" + userId + "\'";
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        output.add(new MoneySavings(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
            rs.getString(5), rs.getString(7), rs.getString(6)));

        System.out.println("MoneySavings: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " +
            rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(7) + " " + rs.getString(6));
      }

      rs.close();
      stmt.close();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

    return output;
  }

  public static String saveMoneySavings(Connection conn, String name, String totalAmount, String interest,
                                        String totalMonth, String userId) {

    try {
      String query = "INSERT INTO MoneySavings (name, totalAmount, interest, totalMonth,userId, createdDate ) VALUES " +
          "(?, ?, ?, ?, ?, ?);";
      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, name);
      pstmt.setString(2, totalAmount);
      pstmt.setString(3, interest);
      pstmt.setString(4, totalMonth);
      pstmt.setString(5, userId);
      LocalDate currentDate = LocalDate.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      pstmt.setString(6, currentDate.format(formatter));
      pstmt.executeUpdate();
      System.out.println("Successfully save MoneySavings: " + name + " " + totalAmount + " " + interest);
      return "Success";
    } catch (SQLException e) {
      System.out.println("Save MoneySavings error: " + e);
      return "Failed. " + e;
    }
  }

  public static void deleteAll(Connection conn, String userId) {
    try {
      String query = "DELETE FROM MoneySavings WHERE userId = " + "\'" + userId + "\'";
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(query);
      stmt.close();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }
}
