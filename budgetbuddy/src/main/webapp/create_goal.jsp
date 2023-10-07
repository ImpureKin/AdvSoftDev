<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> 
<head>
<title>Create Saving Goal Page </title>
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
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav> 

 <!-- Adding a saving goal-->
<div class="container mt-4">
<h1 class="text-center"> Create a Saving Goal </h1>
<p clas="text-center"></p>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <form action="AddingSavingGoalServlet" method="post">
                <div class="form-group mb-5">
                    <label for="goalName">Goal Name:</label>
                    <input type="text" name="goalName" class="form-control" required>
                </div>
                <div class="form-group mb-5">
                    <label for="goalDescription">Goal Description:</label>
                    <input type="text" name="goalDescription" class="form-control" required>
                </div>

                <div class="form-group mb-5">
                    <label for="category">Goal Category:</label>
                    <input type="text" name="category" class="form-control" required>
                </div>

                <div class="form-group mb-5">
                    <label for="goalAmount">Goal Amount:</label>
                    <input type="number" name="goalAmount" class="form-control" required step="1">
                </div>
                <input type="hidden" name="dateCreated" value="">

                <div class ="form-group mt-5 text-center">
                    <button type="submit" class="btn btn-primary">Create Goal</button>
                    <a href="LoadGoalsAndSavingsServlet" class="btn btn-secondary">Cancel</a>
                <div>
            </form>
        </div>
  </div>
</div>

     <!-- Dating for the goal to be stored -->
    <script>
    var currentDate = new Date();

    var day = String(currentDate.getDate()).padStart(2, '0');
    var month = String(currentDate.getMonth() + 1).padStart(2, '0'); // January is 0!
    var year = currentDate.getFullYear();

    var formattedDate = day + '/' + month + '/' + year;
    document.querySelector('input[name="date_created"]').value = formattedDate;
    </script>

    </body>
 </html>
