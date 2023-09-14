<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<title>Edit Goal </title>
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
            <a href="income.jsp">Income</a>
            <a href="expenses.jsp">Expenses</a>
            <a href="wip.jsp">Deductions</a>
            <a href="saving_goals.jsp">Savings</a>
            <a href="trends.jsp">Trends</a>
            <a href="tips_and_knowledge.jsp">Tips & Knowledge</a>
            <a href="payment.jsp">Bill Reminders</a>
            <a href="wip.jsp">Financial Support</a>
            <a href="index.jsp">Logout</a>
         </div>
    </div>

    <h1>Edit Saving Goal</h1>
     <!-- Form to edit a goal -->
    <form action="" method="post">
    <label for="goalName"> Goal Name: </label>
    <input type="text" id="goalName" value="Computer"><br>

    <label for="goalDecription"> Goal Description: </label>
    <input type="text" id="goalDecription" value="Because I want" required><br>

    <label for="goalAmount"> Goal Amount: </label>
    <input type="number" id="goalAmount" value="1000" required><br>

    <input type="hidden" name="date_created" value="2023-09-05">

        <input type="submit" value="Save Changes">
        <input type="reset" value="Reset">
        <a href="detail_goal.jsp">Cancel</a>
    </form>

</body>
</html>