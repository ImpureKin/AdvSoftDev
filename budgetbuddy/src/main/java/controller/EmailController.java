package controller;

import java.sql.*;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import model.User;

import java.util.Random;

import database.UserManager;

public class EmailController {

    public static String sendMfaCode(String recipientEmail) {
        // Gmail credentials
        final String username = "TheKeyLoggerMailer@gmail.com";
        final String password = "hxdjmthlivfiwatj";

        // Generate a 5-digit numerical MFA code
        String mfaCode = generateRandomMfa();

        // Set up the properties for the SMTP server
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session with authentication
        jakarta.mail.Session session = jakarta.mail.Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a MimeMessage
            Message message = new MimeMessage(session);

            // Set the sender and recipient email addresses
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            // Set the email subject and content
            message.setSubject("BudgetBuddy: Your MFA Code for Login");
            message.setText("Your MFA code for login is: " + mfaCode);

            // Send the email
            Transport.send(message);

            System.out.println("MFA code sent successfully sent to " + recipientEmail);

            // Return the generated MFA code
            return mfaCode;

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Failed to send the MFA code.");
            return null;
        }
    }

    public static void sendPasswordRecovery(Connection connection, String recipientEmail) {
        // Gmail credentials
        final String username = "TheKeyLoggerMailer@gmail.com";
        final String password = "hxdjmthlivfiwatj";

        // Set up the properties for the SMTP server
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session with authentication
        jakarta.mail.Session session = jakarta.mail.Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            User user = UserManager.getUser(connection, "email", recipientEmail);
            if (user != null) {
                UserController uc = user.getUserController();
                String userPassword = uc.getValue(user, "password");

                // Create a MimeMessage
                Message message = new MimeMessage(session);

                // Set the sender and recipient email addresses
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

                // Set the email subject and content
                message.setSubject("BudgetBuddy: Your Password");
                message.setText("Your BudgetBuddy password for " + recipientEmail + " is:\n\n" + userPassword);

                // Send the email
                Transport.send(message);

                System.out.println("Account password successfully sent to " + recipientEmail);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Failed to send password.");
        }
    }

    private static String generateRandomMfa() {
        Random random = new Random();
        int min = 10000;
        int max = 99999;
        int randomMFA = random.nextInt(max - min + 1) + min;
        System.out.println(randomMFA);
        return String.valueOf(randomMFA);
    }
}
