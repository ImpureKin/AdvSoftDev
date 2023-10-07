package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import database.*;
import java.sql.*;

public class SignupController {

    public String isValidSignup(String email, String password, String confirmPassword, String firstName,
            String lastName, String phone, String dob, String gender) throws Exception {
        // Check for empty fields
        if (isEmpty(email) || isEmpty(password) || isEmpty(confirmPassword) || isEmpty(firstName) || isEmpty(lastName)
                || isEmpty(phone) || isEmpty(dob) || isEmpty(gender)) {
            return "Please fill in all fields.";
        }

        // Check if password meets criteria
        String passwordStatus = isValidPassword(password);
        if (passwordStatus != null) {
            return passwordStatus;
        }

        // Check if password and confirm password match
        if (passwordsMatch(password, confirmPassword)) {
            return "Provided passwords do not match.";
        }

        // Check email format (you can use a regular expression for more precise
        // validation)
        if (!isValidEmail(email)) {
            return "Email is invalid. Please try again.";
        }

        // Check phone length
        if (phone.length() != 10) {
            return "Phone number is not valid. Please try again.";
        }

        // Check if email is registered already
        if (emailAlreadyExists(email)) {
            return "This email is already in use. Please try again.";
        }

        // Check if phone is registered already
        if (phoneAlreadyExists(email)) {
            return "This phone number is already in use. Please try again.";
        }

        // Register user in DB
        try {
            Connection conn = ConnectionManager.getConnection();
            UserManager.registerUser(conn, firstName, lastName, email, password, phone, dob, gender);
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
            throw e;
        }

        // If all checks pass, return null (indicating successful validation)
        return null;
    }

    private boolean emailAlreadyExists(String email) {
        try {
            Connection conn = ConnectionManager.getConnection();
            if (UserManager.getUser(conn, "email", email) != null) {
                conn.close();
                return true;
            } 
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
            return false;
        }
        return false;
    }

    private boolean phoneAlreadyExists(String phone) {
        try {
            Connection conn = ConnectionManager.getConnection();
            if (UserManager.getUser(conn, "phone", phone) != null) {
                conn.close();
                return true;
            } 
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
            return false;
        }
        return false;
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean isValidEmail(String email) {
        // Validate email format using regex
        return email.matches(".+@.+\\..+");
    }

    private boolean passwordsMatch(String password, String confirmPassword) {
        return !password.equals(confirmPassword);
    }

    private String isValidPassword(String password) {
        // Check password length (min 6 characters, max 12 characters)
        if (password.length() < 6 || password.length() > 12) {
            return "Password must be between 6 and 12 characters.";
        }

        // Check for at least one digit
        if (!containsDigit(password)) {
            return "Password must contain at least one digit.";
        }

        // Check for at least one special character (using a regex pattern)
        if (!containsSpecialCharacter(password)) {
            return "Password must contain at least one special character.";
        }

        // If all checks pass, return null (indicating successful validation)
        return null;
    }

    private boolean containsDigit(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsSpecialCharacter(String password) {
        // Check if string (password) contains a special character
        Pattern pattern = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>?]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}
