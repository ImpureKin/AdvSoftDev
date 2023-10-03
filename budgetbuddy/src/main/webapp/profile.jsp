<%-- 
    Document   : Profile
    Created on : 28 Mar 2023, 6:38:18 pm
    Author     : John
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="controller.*" %>
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
        <div class="container">
            <div class="textcenter">
                <h1>What would you like to do?</h1><br><br><br>
            </div>
            <form action="edit_user.jsp" method="POST">
              <% 
              User user = (User) session.getAttribute("User");
              UserController uc = user.getUserController(); 
              %>
              <p>Your email is: <%= uc.getValue(user, "email") %></p>

              <br>
              <h3> Edit your account details below: </h3>
              <table>
                  <tr>
                          <td><label for="email">Email:</label></td>
                          <td><input type="email" name="email"></td>
                  </tr>
                  <tr>
                          <td><label for="firstName">First Name:</label></td>
                          <td><input type="text" name="firstName"></td>
                  </tr>
                  <tr>
                          <td><label for="lastName">Last Name:</label></td>
                          <td><input type="text" name="lastName"></td>
                  </tr>
                  <tr>
                          <td><label for="password">Password:</label></td>
                          <td><input type="password" name="password"></td>
                  </tr>
                  <tr>
                          <td><label for="phoneNumber">Phone Number:</label></td>
                          <td><input type="text" name="phoneNumber"></td>
                  </tr>
                  <tr>
                          <td><label for="address">Address:</label></td>
                          <td><input type="text" name="address"></td>
                  </tr>
              </table>
              <br>
              <input type="submit" value="Save">
              <button type="button" style="height:20px;width:75px" onClick="location.href='index.jsp'">Cancel</button>
              <input type="hidden" name="submitted" value="yes">
            </form>
            <form action="delete_user.jsp" method="POST">
              <h2>Delete your account?</h2>
              <p> WARNING! This will remove your account entirely. This cannot be undone!</p> 
              <input type="submit" value="Delete Account">
            </form>
        </div>
    </body>
</html>
