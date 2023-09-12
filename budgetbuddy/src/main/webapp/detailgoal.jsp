<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<title>Goal Details</title>
</head>
<body>
    <a href="index.jsp">Home</a>
    <a href="savinggoals.jsp">Trends</a>
    <h1>Detail Goal </h1>

    <label for="goalName">Goal Name:</label>
    <span id="goalName">New Computer</span><br>

    <label for="goalDescription">Goal Description:</label>
    <span id="goalDescription">Because I want it</span><br>

    <label for="goalAmount">Goal Amount:</label>
    <span id="goalAmount">$1000</span><br>

    <label for="dateCreated">Date Created:</label>
    <span id="dateCreated">2023-09-05</span><br>

    <a href="editgoal.jsp">Edit Goal</a> 
    <a href="#" onclick="confirmDelete()">Delete Goal</a><br>
    
    <button onclick="goBack()">Back</button>

    <script>
        function goBack() {
            window.history.back();
        }

        function confirmDelete() {
            var confirmDelete = confirm("Are you sure you want to delete this goal?");
            if (confirmDelete) {
                // Add logic to delete goal (e.g., AJAX request to server)
            }
        }
    </script>
    
</body>

</html>