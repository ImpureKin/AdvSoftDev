<%-- 
    Document   : Register Page
    Created on : 28 Mar 2023, 5:20:19 pm
    Author     : John
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
          top: 45%;
          left: 50%;
          -ms-transform: translate(-50%, -50%);
          transform: translate(-50%, -50%);
        }

        .center-container {
          display: flex;
           justify-content: center;
          align-items: center;
        }

        .topnav a {
          text-align: center;
          padding: 10px 15px;
        }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BudgetBuddy Register</title>
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
        <div class="container">
            <div class="titlecenter">
                <h1>BudgetBuddy</h1><br><br><br><br><br>
            </div>
            <div class="titlecenter">
                <h1>Register Page</h1>
            </div>
            <div class="textcenter">
                <form action="register_check.jsp">
                    <label for="email">Email:</label><br><br>
                    <input type="email" id="email" name="email" required value="${sessionScope.registrationEmail}"><br><br>

                    <label for="password">Password:</label><br><br>
                    <input type="password" id="password" name="password" required><br><br><br>

                    <label for="confirmPassword">Confirm Password:</label><br><br>
                    <input type="password" id="confirmPassword" name="confirmPassword" required><br><br><br>

                    <label for="firstName">First Name:</label><br><br>
                    <input type="text" id="firstName" name="firstName" required value="${sessionScope.registrationFirstName}"><br><br>

                    <label for="lastName">Last Name:</label><br><br>
                    <input type="text" id="lastName" name="lastName" required value="${sessionScope.registrationLastName}"><br><br>

                    <label for="phone">Phone Number:</label><br><br>
                    <input type="text" id="phone" name="phone" required value="${sessionScope.registrationPhone}"><br><br>

                    <label for="dob">Date of Birth:</label><br><br>
                    <input type="date" id="dob" name="dob" required value="${sessionScope.registrationDob}"><br><br>

                    <label for="gender">Gender:</label><br>
                    <input type="radio" id="genderMale" name="gender" value="Male" 
                        ${sessionScope.registrationGender == 'Male' ? 'checked' : ''}>
                    <label for="genderMale">Male</label><br>
                    
                    <input type="radio" id="genderFemale" name="gender" value="Female" 
                        ${sessionScope.registrationGender == 'Female' ? 'checked' : ''}>
                    <label for="genderFemale">Female</label><br><br>
                    

                    <input type="submit" id="submit" value="Submit">
                </form>
            </div>
        </div>
    </body>
</html>
