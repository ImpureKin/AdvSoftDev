<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<title>Goal Details</title>
</head>
<body>
    <a href="home.jsp">Home</a>
    <a href="savinggoals.jsp">Trends</a>
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
    <a href="editgoal.jsp">Edit Goal</a> 
    <a href="#" onclick="confirmDelete()">Delete Goal</a>
    <a href="savinggoals.jsp">Cancel</a>

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