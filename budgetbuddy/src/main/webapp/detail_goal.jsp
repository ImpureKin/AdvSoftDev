<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<title>Goal Details</title>
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

    <h1>Saving Goal Detail</h1>
     <!-- Shows details for the goal -->
    <label for="goalName">Goal Name:</label>
    <span id="goalName">New Computer</span><br>

    <label for="goalDescription">Goal Description:</label>
    <span id="goalDescription">Because I want it</span><br>

    <label for="goalAmount">Goal Amount:</label>
    <span id="goalAmount">$1000</span><br>

    <label for="dateCreated">Date Created:</label>
    <span id="dateCreated">2023-09-05</span><br>

     <!-- links to either allow the user to edit or remove a goal -->
    <a href="edit_goal.jsp">Edit Goal</a> 
    <a href="#" onclick="confirmDelete()">Delete Goal</a>
    <a href="saving_goals.jsp">Cancel</a>

     <!-- Script that will allow the users to go back and message to confirm a delete -->
    <script>
        function confirmDelete() {
            var confirmDelete = confirm("Are you sure you want to delete this goal?");
            if (confirmDelete) {
                // Add later the logic to delete a goal. Not adding now as it will error 
            }
        }
    </script>
    
</body>

</html>