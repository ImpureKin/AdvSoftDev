import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.User;
import controller.*;
import database.*;

public class TestUser {

    static ConnectionManager cm = new ConnectionManager();
    static DatabaseManager dm = new DatabaseManager();

    static UserController uc = new UserController();
    static SignupController sc = new SignupController();
    static LoginController lc = new LoginController();

    static Logger logger = Logger.getLogger(TestUser.class.getName());

    @BeforeAll
    public static void initialiseDatabase() {
        try {
            dm.resetDatabase();
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed resetting Database: ", e);
        }
    }

    @AfterEach
    public void resetDatabase() {
        try {
            dm.resetDatabase();
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed resetting Database: ", e);
        }
    }

    @Test
    public void userValidRegistration() {
        try {
            logger.log(Level.INFO, "Beginning userValidRegistration test.");
            String email = "test@hotmail.com";
            String password = "@Password1";
            String confirmPassword = "@Password1";
            String firstName = "Tester";
            String lastName = "Testerton";
            String phone = "0000000000";
            String dob = "24/08/2000";
            String gender = "Male";

            // Check for valid sign up and if it's valid, register user
            List<String> status_message = sc.isValidSignup(email, password, confirmPassword, firstName, lastName, phone, dob, gender);
            assertNull(status_message);
            logger.log(Level.INFO, "Successfully performed userValidRegistration test.");
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed userValidRegistration test: ", e);
        }
    }

    // @Test
    // public void userValidLogin() {

    // }

    // @Test
    // public static void userDelete() {

    // }

    // @Test
    // public static void userValidModifyPassword() {

    // }

    // @Test
    // public static void userValidModifyEmail() {

    // }
}
