<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Goals" %>
<%@ page import="model.Finances" %>
<%@page import="java.util.ArrayList"%>
<%@ page import="database.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> 
<head>
    <title>Saving and Goals Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"> 
</head>
<body>
    <!-- NavBar-->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">BudgetBuddy</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-between" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="home.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="income.jsp">Income</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="expenses.jsp">Expenses</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="wip.jsp">Deductions</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="LoadGoalsAndSavingsServlet">Savings</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="trends.jsp">Trends</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="tips_and_knowledge.jsp">Tips & Knowledge</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="payment.jsp">Bill Reminders</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="wip.jsp">Financial Support</a>
                    </li>
                </ul>
                <br>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    
    <!-- Information about the page -->
    <div class="container mt-4">
    <h1 class="text-center">Saving and Goals</h1>
    <p class="text-center">This page will display your saving and your saving goals. At BudgetBuddy we are all about 
         achieving your saving goals. To do this we will show you how you and use your remaining money to save towards a saving goal.
         This could be towards a trip, buying a house, or an item which you really wanted! <br> <br> Below you can create a new goal,
         send money towards a goal, see your savings and see all your current goals.</p>

      <div class="row">
    <div class="col-md-6">
        <!-- Shows money available for goals -->
        <% 
            Integer totalSavings = (Integer) request.getAttribute("totalSavings"); 
            Integer totalGoalSavings = (Integer) request.getAttribute("totalGoalSavings"); 
            Integer totalSaved = (Integer) request.getAttribute("totalSaved");
            
            // Check if totalSavings, totalGoalSavings, or totalSaved is null or zero 
            if (totalSavings == null || totalSavings == 0) { 
                totalSavings = 0; // Set it to 0 if it's null or zero 
            }
            
            if (totalGoalSavings == null || totalGoalSavings == 0) { 
                totalGoalSavings = 0; // Set it to 0 if it's null or zero 
            }
            
            if (totalSaved == null || totalSaved == 0) { 
                totalSaved = 0; // Set it to 0 if it's null or zero 
            } 
        %>
    
        <!-- Display the total savings information --> 
        <h2 class="pb-2">Savings Information</h2> 
        <p class="mb-4 fs-5"> Total Savings: $ <%= totalSavings %> </p> 
        <p class="mb-4 fs-5">Savings for Goals: $ <%= totalSaved %> </p> 
    </div>

    <!-- Add Money to a saving goal if available --> 
<div class="col-md-6">
    <h2>Add Money to Goal</h2> 
    <form action="AddMoneytoGoalServlet" method="post" class="row"> 
        <!-- Goal Selection --> 
        <div class="form-group"> 
            <label for="goalId"><strong>Select Goal:</strong></label> 
            <select id="goalId" name="goalId" class="form-control"> 
                <c:forEach var="goal" items="${userGoals}"> 
                    <option value="${goal.id}"><c:out value="${goal.name}" /></option> 
                </c:forEach> 
            </select> 
        </div>
        
        <!-- Amount Input --> 
        <div class="form-group"> 
            <label for="amount"><strong>Amount:</strong></label> 
            <input type="number" id="amount" name="amount" class="form-control" 
                <c:if test="${totalSaved == 0}">disabled</c:if> required step="1"> 
        </div>
        
        <!-- Submit Button --> 
        <button type="submit" class="btn btn-primary mt-4" 
            <c:if test="${totalSaved == 0}">disabled</c:if>>Add Money</button> 
    </form>
    
    <div id="successNotification" class="alert alert-success mt-3" style="display:none;"> 
        Money successfully added to goal! 
    </div> 
</div>


            

            <!-- Savings Goals -->
          <div class="container text-center mt-5 mb-5">
    <h2 class="mb-4">Saving Goals</h2>
    <c:if test="${not empty userGoals}">
        <ul class="list-group">
            <c:forEach var="goal" items="${userGoals}">
                <li class="list-group-item">
                    <strong>${goal.name}</strong> - Saved: $${goal.savedAmount} / Goal: $${goal.goalAmount}
                    <a href="GetGoalDetailServlet?goalId=${goal.id}" class="btn btn-info btn-sm float-right">Details</a>
                </li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty userGoals}">
        <p class="mt-3">No saving goals set yet. <br> Here is a tester <a href="detail_goal.jsp">Computer</a></p>
    </c:if>

    <a href="create_goal.jsp" class="btn btn-success btn-lg mt-3 col-12">Create Goal</a>
</div>

    <!-- Script to enable and disable adding money to a goal -->
    <script>
        function validateForm() {
            var selectGoal = document.getElementById('goalId');
            var amountField = document.getElementById('amount');
            if (selectGoal.value === '') {
                alert('No goals available. Please add money to savings directly.');
                return false; 
            } else {
                amountField.disabled = false; 
            }
            return true;
        }
    </script>

 <%
    Boolean moneyAdded = (Boolean) request.getAttribute("moneyAdded");
    if (moneyAdded != null && moneyAdded) {
%>
    <script>
        document.getElementById("successNotification").style.display = "block";
    </script>
<%
    }
%>
    
    <!-- Bootstrap JavaScript and jQuery (Include them at the end for better performance) -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
