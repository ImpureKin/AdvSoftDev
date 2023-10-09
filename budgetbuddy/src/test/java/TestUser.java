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

    Connection conn;

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
            ConnectionManager.closeConnection(conn);
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
            ConnectionManager.closeConnection(conn);
            assertNull(status_message);
            logger.log(Level.INFO, "Successfully performed userValidRegistration test.");
        }
        catch (Exception e) {
            ConnectionManager.closeConnection(conn);
            logger.log(Level.SEVERE, "Failed userValidRegistration test: ", e);
        }
    }

    @Test
    public void userValidLogin() {
        try {
            logger.log(Level.INFO, "Beginning userValidLogin test.");
            String email = "eren.atilgan@student.uts.edu.au";
            String password = "password";
            ConnectionManager.closeConnection(conn);
            assertNull(lc.isValidLogin(email, password));
            logger.log(Level.INFO, "Successfully performed userValidLogin test."); 
        }
        catch (Exception e) {
            ConnectionManager.closeConnection(conn);
            logger.log(Level.SEVERE, "Failed userValidLogin test: ", e);
        }
    }

    @Test
    public void userDelete() {
        try {
            logger.log(Level.INFO, "Beginning userDelete test.");
            User testUser;

            testUser = uc.getUser("eren.atilgan@student.uts.edu.au");
            uc.deleteUser(uc.getValue(testUser, "id"));

            testUser = uc.getUser("eren.atilgan@student.uts.edu.au");
            assertNull(testUser);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed userDelete test: ", e);
        }
    }

    @Test
    public void userValidModifyPassword() {
        try {
            logger.log(Level.INFO, "Beginning userValidModifyPassword test.");
            User testUser = uc.getUser("eren.atilgan@student.uts.edu.au");
            String newPassword = "@Password1";
            List<String> passwordStatus = sc.isValidPassword(newPassword);

            if (passwordStatus.isEmpty()) {
                testUser.setPassword(newPassword);
            }

            testUser = uc.getUser("eren.atilgan@student.uts.edu.au");
            String password = uc.getValue(testUser, "password");
            assertEquals(password, newPassword);
        }
        catch (Exception e) {
           logger.log(Level.SEVERE, "Failed userValidModifyPassword test: ", e); 
        }
    }

    @Test
    public void userValidModifyEmail() {
        try {
            logger.log(Level.INFO, "Beginning userValidModifyEmail test.");
            conn = ConnectionManager.getConnection();
            User testUser = uc.getUser("eren.atilgan@student.uts.edu.au");
            String newEmail = "eren,atilganTest@student.uts.edu.au";
            String emailStatus = sc.isValidEmail(newEmail, conn);

            if (emailStatus == null) {
                testUser.setEmail(newEmail);
            }

            Boolean status = false;
            testUser = uc.getUser("eren.atilganTest@student.uts.edu.au");
            if (testUser == null) {
                status = true;
            }
            ConnectionManager.closeConnection(conn);
            assertTrue(status);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed userValidModifyEmail test: ", e); 
            ConnectionManager.closeConnection(conn);
        }
    }
}
