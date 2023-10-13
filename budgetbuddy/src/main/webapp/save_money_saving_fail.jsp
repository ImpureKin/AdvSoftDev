<!-- register_fail.jsp -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

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
                <h1>Save failed!</h1><br><br><br><br><br>
            </div>
            <div class="titlecenter">
                <% List<String> status_message = (List<String>) session.getAttribute("StatusMessage"); %>
                <h1>
                <% if (!status_message.isEmpty()) { %>
                    <ul>
                        <% for (String message : status_message) { %>
                            <li><%= message %></li>
                        <% } %>
                    </ul>
                <% } %>
                </h1>
            </div>
            <div class="textcenter">
                <h2>
                    <a href="wip.jsp">Return to Payment</a>
                </h2>
            </div>
        </div>
    </body>
</html>
