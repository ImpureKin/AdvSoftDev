<!-- edit_user.jsp -->
<%@ page import="model.*, controller.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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

SignupController sc = new SignupController();

// Perform validation on the submitted data
// If a field is empty, keep the existing value; otherwise, validate and update
if (!newEmail.isEmpty()) {
    // Check email format and uniqueness
    String emailStatus = sc.isValidEmail(newEmail);
    if (emailStatus != null) {
        validationMessages.add(emailStatus);
    } else {
        user.setEmail(newEmail);
    }
}

if (!newFirstName.isEmpty()) {
    user.setFirstName(newFirstName);
}

if (!newLastName.isEmpty()) {
    user.setLastName(newLastName);
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
            user.setPassword(newPassword);
        }
    }
}

if (!newPhone.isEmpty()) {
    // Check phone format and uniqueness
    String phoneStatus = sc.isValidPhone(newPhone);
    if (phoneStatus != null) {
        validationMessages.add(phoneStatus);
    } else {
        user.setPhoneNumber(newPhone);
    }
}

if (!newDob.isEmpty()) {
    user.setDob(newDob);
}

%>
<!DOCTYPE html>
<html>
<head>
    <style>
        .container {
          height: 700px;
          position: relative;
          border: 3px solid black;
        }

        .titlecenter {
          margin: 0;
          position: absolute;
          top: 30%;
          left: 50%;
          -ms-transform: translate(-50%, -50%);
          transform: translate(-50%, -50%);
        }
        
        .textcenter {
          height: 80px;
          margin: 0;
          position: absolute;
          top: 30%;
          left: 50%;
          -ms-transform: translate(-50%, -50%);
          transform: translate(-50%, -50%);
        }
        .center-container {
          display: flex;
           justify-content: center;
          align-items: center;
        }

        .center {
                text-align: center;
            }

        .topnav input[type=text] {
                padding: 6px;
                border: none;
                margin-top: 8px;
                font-size: 17px;
                background-color: #e9e9e9;
              }
        /* Style the links inside the navigation bar */
        .topnav a {
          text-align: center;
          padding: 10px 15px;
        }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
</head>
<body>
    <div class="center-container">
        <div class="topnav">
        <a href="home.jsp">Home</a>
            <a href="income.jsp">Income</a>
            <a href="expenses.jsp">Expenses</a>
            <a href="wip.jsp">Deductions</a>
            <a href="saving_goals.jsp">Savings</a>
            <a href="trends.jsp">Trends</a>
            <a href="tips_and_knowledge.jsp">Tips & Knowledge</a>
            <a href="payment.jsp">Bill Reminders</a>
            <a href="wip.jsp">Financial Support</a>
            <a href="profile.jsp">Profile</a>
            <a href="index.jsp">Logout</a>
        </div>
    </div>
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

</body>
</html>
