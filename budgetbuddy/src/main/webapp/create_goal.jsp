<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="sections/navbar.jsp" %>
<%@include file="sections/head.jsp" %>
<!DOCTYPE html>
<html> 
<head>
<title>Create Saving Goal Page </title>
</head>
<body class="d-flex flex-column h-100 height-custom">
  
    <!-- Adding a new saving goal-->
    <div class="container-fluid mt-5 flex-grow-1">
        <h1 class="text-center"> Create a Saving Goal </h1>
        <div class="row justify-content-center">
            <div class="col-md-6">
                <form action="AddingSavingGoalServlet" method="post">
                    <div class="form-group mb-4"> <!-- Reduced margin for better spacing -->
                        <label for="goalName"><strong>Goal Name:</strong></label>
                        <input type="text" name="goalName" class="form-control" required>
                    </div>
                    <div class="form-group mb-4">
                        <label for="goalDescription"><strong>Goal Description:</strong></label>
                        <input type="text" name="goalDescription" class="form-control" required>
                    </div>
                    
                    <div class="form-group mb-4">
                        <label for="category"><strong>Goal Category:</strong></label>
                        <input type="text" name="category" class="form-control" required>
                    </div>
                    
                    <div class="form-group mb-4">
                        <label for="goalAmount"><strong>Goal Amount:</strong></label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">$</span>
                            </div>
                            <input type="number" name="goalAmount" class="form-control" required step="1">
                        </div>
                    </div>

                    <input type="hidden" name="dateCreated" value="">

                    <div class="form-group mt-5 mb-4 text-center">
                        <button type="submit" id="submit-button" class="btn btn-primary">Create Goal</button>
                        <a href="GoalsAndSavings" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

     <!-- Making date the correct fomatt for the goal to be stored -->
    <script>

    document.getElementById('submit-button').addEventListener('click', function(event) {
        event.preventDefault(); // Prevent the form from submitting (so we can display the alert first)

        Swal.fire({
        icon: 'success',
        title: 'Success!',
        text: 'Goal successfully created!',
        showCloseButton: false,
        showConfirmButton: true,
        heightAuto: false
        }).then((result) => {
            if (result.isConfirmed) {
                this.form.submit(); // Submit the form when the close button is clicked
            }
        });
    });


    var currentDate = new Date();

    var day = String(currentDate.getDate()).padStart(2, '0');
    var month = String(currentDate.getMonth() + 1).padStart(2, '0'); // January is 0!
    var year = currentDate.getFullYear();

    var formattedDate = day + '/' + month + '/' + year;
    document.querySelector('input[name="dateCreated"]').value = formattedDate;
    </script>
    
</body>
</html>
    <%@include file="sections/foot.jsp" %>
    <%@include file="sections/footer.jsp" %>
