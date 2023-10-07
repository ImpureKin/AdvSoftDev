package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import database.*;
import java.sql.*;

public class SignupController {

    public List<String> isValidSignup(String email, String password, String confirmPassword, String firstName,
            String lastName, String phone, String dob, String gender) throws Exception {

        List<String> signupStatus = new ArrayList<>();;
        // Check for empty fields
        if (isEmpty(email) || isEmpty(password) || isEmpty(confirmPassword) || isEmpty(firstName) || isEmpty(lastName)
                || isEmpty(phone) || isEmpty(dob) || isEmpty(gender)) {
            signupStatus.add("Please fill in all fields.");
            return signupStatus;
        }

        // Check email format
        if (!isValidEmail(email)) {
            signupStatus.add("Email is invalid.");
            return signupStatus;
        }

        // Check if email is registered already
        if (emailAlreadyExists(email)) {
            signupStatus.add("This email is already in use.");
            return signupStatus;
        }

        // Check if password meets criteria
        signupStatus = isValidPassword(password);
        if (!signupStatus.isEmpty()) {
            return signupStatus;
        }

        // Check if password and confirm password match
        if (passwordsMatch(password, confirmPassword)) {
            signupStatus.add("Provided passwords do not match.");
            return signupStatus;
        }

        // Check phone length
        if (phone.length() != 10) {
            signupStatus.add("Phone number is not valid!");
            return signupStatus;
        }

        // Check if phone is registered already
        if (phoneAlreadyExists(email)) {
            signupStatus.add("This phone number is already in use.");
            return signupStatus;
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

    private List<String> isValidPassword(String password) {
        List<String> validationMessages = new ArrayList<>();

        // Check password length (min 6 characters, max 12 characters)
        if (password.length() < 6 || password.length() > 12) {
            validationMessages.add("Password must be between 6 and 12 characters.");
        }

        // Check for at least one digit
        if (!containsDigit(password)) {
            validationMessages.add("Password must contain at least 1 numeric value.");
        }

        // Check for at least one special character (using a regex pattern)
        if (!containsSpecialCharacter(password)) {
            validationMessages.add("Password must contain at least 1 special character (e.g., @).");
        }

        return validationMessages;
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
