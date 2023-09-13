<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<title>Create Saving Goal Page </title>
</head>
<body>
 
<a href="index.jsp">Home</a>
<a href="trends.jsp">Trends</a>

<h1> Create Saving goals </h1>

 <!-- Adding a saving goal-->
<form action="" method="post">
        Goal Name: <input type="text" name="goalName" required><br>
        Goal Description: <input type="text" name="goalDescriptin" reuqired><br>
        Goal Amount: <input type="number" name="goalAmount" required><br>
        <input type="hidden" name="dateCreated" value=""><br>
        <input type="submit" value="Create Goal">
        <a href="savinggoals.jsp">Cancel</a>
    </form>

    <!-- Dating for the goal to be stored -->
     <script>
        var currentDate = new Date();
        var formattedDate = currentDate.toISOString().slice(0,10);
        document.querySelector('input[name="date_created"]').value = formattedDate;
    </script>

    </body>
 </html>
