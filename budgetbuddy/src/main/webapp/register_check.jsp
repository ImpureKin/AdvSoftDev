<!-- register_check.jsp -->
<%@ page import="java.sql.*" %>
<%@ page import="controller.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.*" %>
<%@ page import="database.*" %>



<%
SignupController sc = new SignupController();
UserController uc = new UserController();
Connection connection = ConnectionManager.getConnection();
// check if email and password are set in session
String email = request.getParameter("email");
String password = request.getParameter("password");
String confirmPassword = request.getParameter("confirmPassword");
String firstName = request.getParameter("firstName");
String lastName = request.getParameter("lastName");
String phone = request.getParameter("phone");
String dob = request.getParameter("dob");
String gender = request.getParameter("gender");

// Check for valid sign up and if it's valid, register user
List<String> status_message = sc.isValidSignup(connection, email, password, confirmPassword, firstName, lastName, phone, dob, gender);


if (status_message == null) {
    // Save user details in session
    User user = uc.getUser(connection, email);
    System.out.println("Save email details in session, "+ email);
    session.setAttribute("currentEmail", email);
    session.setAttribute("User", user);
    ConnectionManager.closeConnection(connection);
    response.sendRedirect("home.jsp");
}
else {
    // Store the form data in session attributes (to autofill for convenience)
    session.setAttribute("registrationEmail", email);
    session.setAttribute("registrationFirstName", firstName);
    session.setAttribute("registrationLastName", lastName);
    session.setAttribute("registrationPhone", phone);
    session.setAttribute("registrationDob", dob);
    session.setAttribute("registrationGender", gender);
    session.setAttribute("StatusMessage", status_message);
    ConnectionManager.closeConnection(connection);
    response.sendRedirect("register_fail.jsp");
}
%>