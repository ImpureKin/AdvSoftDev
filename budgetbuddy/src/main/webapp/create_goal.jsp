<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="sections/navbar.jsp" %>
<%@include file="sections/head.jsp" %>
<!DOCTYPE html>
<html> 
<head>
<title>Create Saving Goal Page </title>
</head>
<body>
  
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
    
<%@include file="sections/foot.jsp" %>
</body>

<%@include file="sections/footer.jsp" %>
 </html>
