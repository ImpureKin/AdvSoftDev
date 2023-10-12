<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="sections/navbar.jsp" %>
<%@include file="sections/head.jsp" %>
<!DOCTYPE html>
<html> 
<head>
<title>Goal Details</title>
</head>
<body class="d-flex flex-column h-100">

  <!-- Goal Details -->
  <div class="container-fluid text-center mt-5 mb-5">
    <h1><c:out value="${goal.name}" /> Details</h1>

    <div class="row justify-content-center">
      <div class="col-md-6">
        <label for="goalName"><strong>Goal Name:</strong></label>
        <span id="goalName"><c:out value="${goal.name}" /></span><br>

        <label for="goalDescription"><strong>Goal Description: </strong></label>
        <span id="goalDescription"><c:out value="${goal.description}" /></span><br>

        <label for="goalAmount"><strong>Goal Amount:</strong></label>
        <span id="goalAmount"><c:out value="${currencyFormatter.format(goal.goalAmount)}"/> </span><br>

        <label for="dateCreated"><strong>Date Created:</strong></label>
        <span id="dateCreated"><c:out value="${goal.date}" /></span><br>

        <label for="category"><strong>Category:</strong></label>
        <span id="category"><c:out value="${goal.category}" /></span><br>
      </div>
    </div>

    <!-- Goal Progress and Progrees Bar -->
    <div class="row justify-content-center mt-4 p-5">
      <div class="col-md-6">
        <h3>Goal Progress:</h3>
        <p>You've saved <c:out value="${currencyFormatter.format(goal.savedAmount)}"/> out of <c:out value="${currencyFormatter.format(goal.goalAmount)}"/> so far.</p>
        <div class="progress">
          <div class="progress-bar" role="progressbar" style="width: ${percentage}%;" aria-valuenow="${percentage}" aria-valuemin="0" aria-valuemax="100">
            ${percentage}%
          </div>
        </div>
      </div>
    </div>

    <!-- Action Buttons for the user to choose-->
    <div class="row justify-content-center mt-4 p-5">
      <div class="col-md-6">
        <a href="GoalForEdit?goalId=${goal.id}" class="btn btn-primary mr-2">Edit Goal</a>
        <a href="#" onclick="confirmDelete()" class="btn btn-danger mr-2">Delete Goal</a>
        <a href="GoalsAndSavings" class="btn btn-secondary">Go Back</a>
      </div>
    </div>
  </div>

  <!-- Script to confirm and delete -->
  <script>
    function confirmDelete() {
      var confirmDelete = confirm("Are you sure you want to delete this goal?");
      if (confirmDelete) {
        // will be added later. Not adding now as it will error for R2
      }
    }
  </script>

  <%@include file="sections/foot.jsp" %>
  <%@include file="sections/footer.jsp" %>
</body>  
</html>