package controller;
import model.User;
import database.DB;
import java.sql.*;

public class UserController {
    public User getUser(String email) throws Exception {
        Connection conn = DB.getConnection();
        User user = DB.getUser(conn, email);
        if (user != null) {
            conn.close();
            return user;
        }
        conn.close();
        return null;
    }

    public String editUser(String field, String value, int userId) throws Exception {
        Connection conn = DB.getConnection();
        if (DB.updateUserDetail(conn, field, value, userId) != null) {
            return null;
        }
        else {
            return "Error updating user details.";
        }
    }
}
