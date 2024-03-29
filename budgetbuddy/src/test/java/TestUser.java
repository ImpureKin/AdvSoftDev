import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.User;
import controller.*;
import database.*;

public class TestUser {

    static DatabaseManager dm = new DatabaseManager();
    static UserController uc = new UserController();
    static SignupController sc = new SignupController();
    static LoginController lc = new LoginController();

    static Logger logger = Logger.getLogger(TestUser.class.getName());

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
    public void userValidRegistration() {
        try {
            logger.log(Level.INFO, "Beginning Test: userValidRegistration.");
            String email = "test@hotmail.com";
            String password = "@Password1";
            String confirmPassword = "@Password1";
            String firstName = "Tester";
            String lastName = "Testerton";
            String phone = "0000000000";
            String dob = "24/08/2000";
            String gender = "Male";

            // Check for valid sign up and if it's valid, register user
            List<String> status_message = sc.isValidSignup(connection, email, password, confirmPassword, firstName, lastName, phone, dob, gender);
            assertNull(status_message);
            logger.log(Level.INFO, "Successfully Performed Test: userValidRegistration.");
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed Test: userValidRegistration", e);
        }
    }

    @Test
    public void userValidLogin() {
        try {
            logger.log(Level.INFO, "Beginning Test: userValidLogin.");
            String email = "eren.atilgan@student.uts.edu.au";
            String password = "password";
            assertNull(lc.isValidLogin(connection, email, password));
            logger.log(Level.INFO, "Successfully Performed Test: userValidLogin."); 
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed Test: userValidLogin", e);
        }
    }

    @Test
    public void userDelete() {
        try {
            logger.log(Level.INFO, "Beginning Test: userDelete.");
            User testUser;

            testUser = uc.getUser(connection, "eren.atilgan@student.uts.edu.au");
            uc.deleteUser(connection, uc.getValue(testUser, "id"));

            testUser = uc.getUser(connection, "eren.atilgan@student.uts.edu.au");
            assertNull(testUser);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed Test: userDelete", e);
        }
    }

    @Test
    public void userValidModifyPassword() {
        try {
            logger.log(Level.INFO, "Beginning Test: userValidModifyPassword.");
            User testUser = uc.getUser(connection, "eren.atilgan@student.uts.edu.au");
            String newPassword = "@Password1";
            List<String> passwordStatus = sc.isValidPassword(newPassword);

            if (passwordStatus.isEmpty()) {
                testUser.setPassword(connection, newPassword);
            }

            testUser = uc.getUser(connection, "eren.atilgan@student.uts.edu.au");
            String password = uc.getValue(testUser, "password");
            assertEquals(password, newPassword);
        }
        catch (Exception e) {
           logger.log(Level.SEVERE, "Failed Test: userValidModifyPassword", e); 
        }
    }

    @Test
    public void userValidModifyEmail() {
        try {
            logger.log(Level.INFO, "Beginning Test: userValidModifyEmail");
            User testUser = uc.getUser(connection, "eren.atilgan@student.uts.edu.au");
            String newEmail = "eren,atilganTest@student.uts.edu.au";
            String emailStatus = sc.isValidEmail(connection, newEmail);

            if (emailStatus == null) {
                testUser.setEmail(connection, newEmail);
            }

            Boolean status = false;
            testUser = uc.getUser(connection, "eren.atilganTest@student.uts.edu.au");
            if (testUser == null) {
                status = true;
            }
            assertTrue(status);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed Test: userValidModifyEmail", e); 
        }
    }
}
