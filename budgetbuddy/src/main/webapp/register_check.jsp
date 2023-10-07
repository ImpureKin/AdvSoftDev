<%@ page import="java.sql.*" %>
<%@ page import="controller.*" %>
<%@ page import="model.*" %>


<%
SignupController sc = new SignupController();
UserController uc = new UserController();
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
String status_message = sc.isValidSignup(email, password, confirmPassword, firstName, lastName, phone, dob, gender);


if (status_message == null) {
    // Save user details in session
    User user = uc.getUser(email);
    session.setAttribute("User", user);
    response.sendRedirect("home.jsp");
}
else {
    response.sendRedirect("register_fail.jsp");
}
%>