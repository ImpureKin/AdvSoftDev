<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<title>Edit Goal </title>
</head>
<body>
    <a href="index.jsp">Home</a>
    <a href="savinggoals.jsp">Trends</a>
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
    </form>
    
    <button onclick="goBack()">Back</button>

     <!-- Script that sends a user back -->
    <script>
        function goBack() {
            window.history.back();
        }
    </script>

</body>
</html>