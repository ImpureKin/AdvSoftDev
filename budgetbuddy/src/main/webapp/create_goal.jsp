<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<title>Create Saving Goal Page </title>
<style>
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
</head>
 
<body>

 <!-- NavBar-->
<div class="center-container">
        <div class="topnav">
        <a href="home.jsp">Home</a>
            <a href="wip.jsp">Income</a>
            <a href="expenses.jsp">Expenses</a>
            <a href="wip.jsp">Deductions</a>
            <a href="saving_goals.jsp">Savings</a>
            <a href="trends.jsp">Trends</a>
            <a href="wip.jsp">Tips & Knowledge</a>
            <a href="payment.jsp">Bill Reminders</a>
            <a href="wip.jsp">Financial Support</a>
            <a href="index.jsp">Logout</a>
        </div>
        </div>

<h1> Create Saving goals </h1>
 <!-- Adding a saving goal-->
<form action="" method="post">
        Goal Name: <input type="text" name="goalName" required><br>
        Goal Description: <input type="text" name="goalDescriptin" reuqired><br>
        Goal Amount: <input type="number" name="goalAmount" required><br>
        <input type="hidden" name="dateCreated" value=""><br>
        <input type="submit" value="Create Goal">
        <a href="saving_goals.jsp">Cancel</a>
    </form>

    <!-- Dating for the goal to be stored -->
     <script>
        var currentDate = new Date();
        var formattedDate = currentDate.toISOString().slice(0,10);
        document.querySelector('input[name="date_created"]').value = formattedDate;
    </script>

    </body>
 </html>
