package model;
import java.sql.*;

// Class for User
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String dob;
    private String gender;
    
    // Customer Constructor
    public User(int id, String firstName, String lastName, String email, String password, String phoneNumber, String dob, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.gender = gender;
    }
}