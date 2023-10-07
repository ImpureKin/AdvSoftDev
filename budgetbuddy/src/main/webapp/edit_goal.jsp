<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> 
<head>
<title>Edit Goal </title>
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

   <div class="container mt-5">
    <h1 class="text-center mb-4">Edit <c:out value='${goal.name}'/> </h1>

    <!-- Form to edit a goal -->
    <form action="UpdateGoalServlet" method="post">
        <input type="hidden" name="goalId" value="<c:out value='${goal.id}'/>">

        <div class="form-group">
            <label for="goalName">Goal Name:</label>
            <input type="text" class="form-control" id="goalName" name="goalName" value="<c:out value='${goal.name}'/>"required>
        </div>

        <div class="form-group">
            <label for="goalDescription">Goal Description:</label>
            <input type="text" class="form-control" id="goalDescription" name="goalDescription" value="<c:out value='${goal.description}'/>" >
        </div>

        <div class="form-group">
            <label for="goalAmount">Goal Amount:</label>
            <input type="number" class="form-control" id="goalAmount" name="goalAmount" value="<c:out value='${goal.goalAmount}'/>" required>
        </div>


        <div class="text-center mt-4">
            <button type="submit" class="btn btn-primary">Save Changes</button>
            <button type="reset" class="btn btn-secondary">Reset</button>
            <a href="GetGoalDetailServlet?goalId=${goal.id}" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>


</body>
</html>