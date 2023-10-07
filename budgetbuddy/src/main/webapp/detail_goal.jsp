<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> 
<head>
<title>Goal Details</title>
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

    <div class="container text-center mt-5 mb-5">
    <h1><c:out value="${goal.name}" /> Details</h1> 
    <!-- Goal Details -->
    <div class="row justify-content-center">
        <div class="col-md-6">
            <label for="goalName"><strong>Goal Name:</strong></label>
            <span id="goalName"><c:out value="${goal.name}" /></span><br>

            <label for="goalDescription"><strong>Goal Description: </strong></label>
            <span id="goalDescription"><c:out value="${goal.description}" /></span><br>

            <label for="goalAmount"><strong>Goal Amount:</strong></label>
            <span id="goalAmount">$<c:out value="${goal.goalAmount}" /></span><br>

            <label for="dateCreated"><strong>Date Created:</strong></label>
            <span id="dateCreated"><c:out value="${goal.date}" /></span><br>

            <label for="category"><strong>Category:</strong></label>
            <span id="category"><c:out value="${goal.category}" /></span><br>
        </div>
    </div>

    <!-- Goal Progress -->
    
    <div class="row justify-content-center mt-4">
        <div class="col-md-6">
            <h3>Goal Progress:</h3>
            <p>You've saved $<c:out value="${goal.savedAmount}" /> out of $<c:out value="${goal.goalAmount}" /> so far.</p>
            <div class="progress">
            <div class="progress-bar" role="progressbar" 
                 style="width: ${percentage}%;" 
                 aria-valuenow="${percentage}" 
                 aria-valuemin="0" 
                 aria-valuemax="100">
                ${percentage}%
            </div>
        </div>
    </div>

    <!-- Action Buttons -->
    <div class="row justify-content-center mt-4">
        <div class="col-md-6">
            <a href="GetGoalForEditServlet?goalId=${goal.id}" class="btn btn-primary mr-2">Edit Goal</a> 
            <a href="#" onclick="confirmDelete()" class="btn btn-danger mr-2">Delete Goal</a>
            <a href="LoadGoalsAndSavingsServlet" class="btn btn-secondary">Go Back</a>
        </div>
    </div>
</div>

<!-- Script for confirmation and delete -->
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