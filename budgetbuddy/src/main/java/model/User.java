// User.java
package model;
import controller.UserController;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String dob;
    private String gender;
    private UserController uc;

    public User(String id, String firstName, String lastName, String email, String password, String phoneNumber, String dob, String gender, UserController uc) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.gender = gender;
        this.uc = uc;
    }

    // Getter for UserController
    public UserController getUserController() {
        return uc;
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Getters and Setters for firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception {
        uc.editUser("first_name", firstName, this.id);
        this.firstName = firstName;
    }

    // Getters and Setters for lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exception {
        uc.editUser("last_name", lastName, this.id);
        this.lastName = lastName;
    }

    // Getters and Setters for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        uc.editUser("email", email, this.id);
        this.email = email;
    }

    // Getters and Setters for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        uc.editUser("password", password, this.id);
        this.password = password;
    }

    // Getters and Setters for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws Exception {
        uc.editUser("phone", phoneNumber, this.id);
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters for dob
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) throws Exception {
        uc.editUser("dob", dob, this.id);
        this.dob = dob;
    }

    // Getters and Setters for gender
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) throws Exception {
        uc.editUser("gender", gender, this.id);
        this.gender = gender;
    }
}
