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
<body class= "d-flex flex-column h-100">

    <!-- Heading of the page -->
   <div class="container mt-5">
   <h1 class="text-center mb-4">Edit <c:out value='${goal.name}'/> </h1>

        <!-- Form to edit a goal -->
        <form action="UpdateGoalServlet" method="post">
            <input type="hidden" name="goalId" value="<c:out value='${goal.id}'/>">

            <div class="form-group mb-5">
                <label for="goalName"><strong>Goal Name:</strong></label>
                <input type="text" class="form-control" id="goalName" name="goalName" value="<c:out value='${goal.name}'/>"required>
            </div>

            <div class="form-group mb-5">
                <label for="goalDescription"><strong>Goal Description:</strong></label>
                <input type="text" class="form-control" id="goalDescription" name="goalDescription" value="<c:out value='${goal.description}'/>" >
            </div>

            <div class="form-group mb-5">
                <label for="goalAmount"><strong>Goal Amount:</strong></label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">$</span>
                    </div>
                    <input type="number" class="form-control" id="goalAmount" name="goalAmount" value="<c:out value='${goal.goalAmount}'/>" required step="1">
                </div>
            </diV>

            <div class="text-center mt-4 p-5">
                <button type="submit" id="submit-button" class="btn btn-primary">Save Changes</button>
                <button type="reset" class="btn btn-secondary">Reset</button>
                <a href="GoalDetails?goalId=${goal.id}" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
    <script>
    document.getElementById('submit-button').addEventListener('click', function(event) {
        event.preventDefault(); // Prevent the form from submitting (so we can display the alert first)

        Swal.fire({
            icon: 'success',
            title: 'Changes Saved',
            text: 'Your changes have been saved successfully.',
            showCloseButton: false,
            showConfirmButton: true,
        }).then((result) => {
            if (result.isConfirmed) {
                this.form.submit(); // Submit the form when the close button is clicked
            }
        });
    });
    </script>
    <%@include file="sections/foot.jsp" %>
    <%@include file="sections/footer.jsp" %>
</body>
</html>