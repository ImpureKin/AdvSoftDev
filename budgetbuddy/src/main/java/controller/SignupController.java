package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import database.*;
import java.sql.*;

public class SignupController {

    Connection conn;

    public List<String> isValidSignup(String email, String password, String confirmPassword, String firstName,
            String lastName, String phone, String dob, String gender) throws Exception {

        conn = ConnectionManager.getConnection();

        List<String> signupStatus = new ArrayList<>();;
        // Check for empty fields
        if (isEmpty(email) || isEmpty(password) || isEmpty(confirmPassword) || isEmpty(firstName) || isEmpty(lastName)
                || isEmpty(phone) || isEmpty(dob) || isEmpty(gender)) {
            signupStatus.add("Please fill in all fields.");
            return signupStatus;
        }

        // Check email format
        String emailStatus = isValidEmail(email, conn);
        if (emailStatus != null) {
            signupStatus.add(emailStatus);
            return signupStatus;
        }

        // Check if password meets criteria
        List<String> passwordStatus = isValidPassword(password);
        if (!passwordStatus.isEmpty()) {
            signupStatus.addAll(passwordStatus);
            return passwordStatus;
        }

        // Check if password and confirm password match
        if (passwordsMatch(password, confirmPassword)) {
            signupStatus.add("Provided passwords do not match.");
            return signupStatus;
        }

        String phoneStatus = isValidPhone(phone, conn);
        if (phoneStatus != null) {
            signupStatus.add(phoneStatus);
            return signupStatus;
        }

        // Register user in Database
        try {
            UserManager.registerUser(conn, firstName, lastName, email, password, phone, dob, gender);
        } catch (Exception e) {
            ConnectionManager.closeConnection(conn);
            System.out.println("Connection Failed: " + e);
            throw e;
        }
        // If all checks pass, return null (indicating successful validation)
        ConnectionManager.closeConnection(conn);
        return null;
    }

    public boolean phoneIsValidLength(String phone) {
        return phone.length() == 10;
    }

    public boolean phoneAlreadyExists(String phone, Connection connection) {
        try {
            if (UserManager.getUser(connection, "phone", phone) != null) {
                return true;
            } 
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
            return false;
        }
        return false;
    }

    public String isValidPhone(String phone, Connection connection) {
        if (!phoneIsValidLength(phone)) {
            return "Phone number is invalid. It needs to have 10 digits.";
        }

        if (phoneAlreadyExists(phone, connection)) {
            return "Phone number is already in use.";
        }
        return null;
    }

    public boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public boolean emailMatchesFormat(String email) {
        return email.matches(".+@.+\\..+");
    }

    public boolean emailAlreadyExists(String email, Connection connection) {
        try {
            if (UserManager.getUser(connection, "email", email) != null) {
                return true;
            } 
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
            return false;
        }
        return false;
    }

    public String isValidEmail(String email, Connection connection) {
        // Validate email format using regex
        if (!emailMatchesFormat(email)) {
            return "Email format is invalid.";
        }

        // Check if email is registered already
        if (emailAlreadyExists(email, connection)) {
            return "Email already exists.";
        }
        return null;
    }

    public boolean passwordsMatch(String password, String confirmPassword) {
        return !password.equals(confirmPassword);
    }

    public List<String> isValidPassword(String password) {
        List<String> passwordValidationMessages = new ArrayList<>();

        // Check password length (min 6 characters, max 12 characters)
        if (password.length() < 6 || password.length() > 12) {
            passwordValidationMessages.add("Password must be between 6 and 12 characters.");
        }

        // Check for at least one digit
        if (!containsDigit(password)) {
            passwordValidationMessages.add("Password must contain at least 1 numeric value.");
        }

        // Check for at least one special character (using a regex pattern)
        if (!containsSpecialCharacter(password)) {
            passwordValidationMessages.add("Password must contain at least 1 special character (e.g., @).");
        }
        return passwordValidationMessages;
    }

    public boolean containsDigit(String string) {
        for (char c : string.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsSpecialCharacter(String string) {
        // Check if string (password) contains a special character
        Pattern pattern = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>?]");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }
}
