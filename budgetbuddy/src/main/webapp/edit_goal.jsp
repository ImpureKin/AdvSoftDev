<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="sections/navbar.jsp" %>
<%@include file="sections/head.jsp" %>
<!DOCTYPE html>
<html> 
<head>
<title>Edit Goal </title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
</head>
<body>

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

<%@include file="sections/foot.jsp" %>
</body>

<%@include file="sections/footer.jsp" %>
</html>