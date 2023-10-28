<!-- edit_user.jsp -->
<%@ page import="model.*, controller.*, database.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.*" %>
<%
// Retrieve the user's session
User user = (User) session.getAttribute("User");

// Get the submitted form data
String newEmail = request.getParameter("email");
String newFirstName = request.getParameter("firstName");
String newLastName = request.getParameter("lastName");
String newPassword = request.getParameter("password");
String newConfirmPassword = request.getParameter("confirmPassword");
String newPhone = request.getParameter("phone");
String newDob = request.getParameter("dob");

// Initialize a list to store validation messages
List<String> validationMessages = new ArrayList<>();

// Get a DB connection
Connection connection = ConnectionManager.getConnection();

SignupController sc = new SignupController();

// Perform validation on the submitted data
// If a field is empty, keep the existing value; otherwise, validate and update
if (!newEmail.isEmpty()) {
    // Check email format and uniqueness
    String emailStatus = sc.isValidEmail(connection, newEmail);
    if (emailStatus != null) {
        validationMessages.add(emailStatus);
    } else {
        user.setEmail(connection, newEmail);
    }
}

if (!newFirstName.isEmpty()) {
    user.setFirstName(connection, newFirstName);
}

if (!newLastName.isEmpty()) {
    user.setLastName(connection, newLastName);
}

if ((newPassword.isEmpty() && !newConfirmPassword.isEmpty()) || (!newPassword.isEmpty() && newConfirmPassword.isEmpty())) {
    validationMessages.add("Both a password and an identical confirmation password must be provided.");
}

if (!newPassword.isEmpty() && !newConfirmPassword.isEmpty()) {
    // Check password criteria
    List<String> passwordValidations = sc.isValidPassword(newPassword);
    if (!passwordValidations.isEmpty()) {
        validationMessages.addAll(passwordValidations);
    } else {
        // Check if newPassword and newConfirmPassword match
        if (!newPassword.equals(newConfirmPassword)) {
            validationMessages.add("Passwords do not match.");
        } else {
            user.setPassword(connection, newPassword);
        }
    }
}

if (!newPhone.isEmpty()) {
    // Check phone format and uniqueness
    String phoneStatus = sc.isValidPhone(connection, newPhone);
    if (phoneStatus != null) {
        validationMessages.add(phoneStatus);
    } else {
        user.setPhoneNumber(connection, newPhone);
    }
}

if (!newDob.isEmpty()) {
    user.setDob(connection, newDob);
}

ConnectionManager.closeConnection(connection);

%>
<%@include file="sections/navbar.jsp" %>
<%@include file="sections/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Profile</title>
</head>
<body>
    <% if (!validationMessages.isEmpty()) { %>
        <h1>Error: Unable to update your details!</h1>
        <div>
            <h2>Validation Errors:</h2>
            <ul>
                <% for (String message : validationMessages) { %>
                    <li><%= message %></li>
                <% } %>
            </ul>
        </div>
    <% } else { %>
        <div>
            <h1>Details Updated Successfully!</h1>
        </div>
    <% } %>
    <%@include file="sections/foot.jsp" %>
    </body>
<%@include file="sections/footer.jsp" %>
</html>
