<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="sections/navbar.jsp" %>
<%@include file="sections/head.jsp" %>

<!DOCTYPE html>
<html> 

<head>
    <title>Saving and Goals Page</title>
</head>

<body>
    <!-- Information about how the saving page works and saving goals -->
    <div class="container mt-4">
    <h1 class="text-center">Saving and Goals</h1>
    <p class="text-center">This page will display your saving and your saving goals. At BudgetBuddy we are all about 
         achieving your saving goals. To do this we will show you how you and use your remaining money to save towards a saving goal.
         This could be towards a trip, buying a house, or an item which you really wanted! <br> <br> Below you can create a new goal,
         send money towards a goal, see your savings and see all your current goals.</p>

    <div class="row">
        <div class="col-md-6">
            <!-- Shows money available for goals and savigns the user has -->
            <% 
                //Gathers Users Data 
                Integer totalSavings = (Integer) request.getAttribute("totalSavings"); 
                Integer totalSaved = (Integer) request.getAttribute("totalSaved");
                
                // Check if totalSavings or totalSaved is null or zero and sets it to display zero 
                if (totalSavings == null || totalSavings == 0) { 
                    totalSavings = 0; 
                }
                
                if (totalSaved == null || totalSaved == 0) { 
                    totalSaved = 0; 
                } 
            %>
        
                <!-- Displays information of overall user savings and savings in which the user has saved 
                    to be used towards goals --> 
                <h2 class="pb-2">Savings Information</h2> 
                <p class="mb-4 fs-5"> <strong>Total Savings:</strong> $ <%= totalSavings %> </p> 
                <p class="mb-4 fs-5"> <strong> Savings for Goals:</strong> $ <%= totalSaved %> </p> 
        </div>

                        <!-- Add Money to a saving goal if available --> 
                    <div class="col-md-6">
                        <h2>Add Money to Goal</h2>
                        <form id="moneyForm" action="AddMoneytoGoalServlet" method="post" class="row"> 

                            <!-- Goal Selection --> 
                            <div class="form-group"> 
                                <label for="goalId"><strong>Select Goal:</strong></label> 
                                <select id="goalId" name="goalId" class="form-control"> 
                                <c:forEach var="goal" items="${userGoals}">
                                    <option value="${goal.id}" 
                                        <c:if test="${goal.savedAmount eq goal.goalAmount}">disabled</c:if>>
                                        <c:out value="${goal.name}" />
                                    </option>
                                </c:forEach>
                                </select> 
                            </div>
                            
                            <!-- Amount Input --> 
                            <div class="form-group"> 
                                <label for="amount"><strong>Amount:</strong></label> 
                                <input type="number" id="amount" name="amount" class="form-control" 
                                    <c:if test="${totalSaved == 0}">disabled</c:if> required step="1"> 
                            </div>
                            
                            <!-- Submit Button --> 
                            <button type="submit" class="btn btn-primary mt-4" <c:if test="${totalSaved == 0}">disabled</c:if>>Add Money</button>

                        </form>
                        <div id="successNotification" class="alert alert-success mt-3" style="display:none;">
                            Money successfully added to goal!
                        </div>
                    </div>
    </div>

</div>


        
<!-- Savings Goals -->
<div class="container text-center mt-5 mb-5 pb-5">
    <h2 class="mb-4">Saving Goals</h2>
    <c:if test="${not empty userGoals}">
        <ul class="list-group">
            <c:forEach var="goal" items="${userGoals}">
                <li class="list-group-item">
                    <strong>${goal.name}</strong> - Saved: $${goal.savedAmount} / Goal: $${goal.goalAmount}
                    <c:choose>
                        <c:when test="${goal.savedAmount eq goal.goalAmount}">
                            <span class="badge badge-custom-success float-right">Goal Complete</span>
                            <a href="GetGoalDetailServlet?goalId=${goal.id}" class="btn btn-info btn-sm float-right mr-2">Details</a>
                        </c:when>
                        <c:otherwise>
                            <a href="GetGoalDetailServlet?goalId=${goal.id}" class="btn btn-primary btn-sm float-right">Details</a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty userGoals}">
        <p class="mt-3">No saving goals set yet. </p>
    </c:if>

    <a href="create_goal.jsp" class="btn btn-success btn-lg mt-3 col-12">Create Goal</a>
</div>

 <script>
    document.getElementById('moneyForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent the form from submitting normally

        var goalId = document.getElementById('goalId').value;
        var amount = document.getElementById('amount').value;

        if (goalId && amount) {
            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'AddMoneytoGoalServlet', true);
            xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

            xhr.onload = function () {
                if (xhr.status >= 200 && xhr.status < 400) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Success',
                        text: xhr.responseText,
                        showCancelButton: false,
                        confirmButtonText: 'Ok',
                    }).then((result) => {
                        if (result.isConfirmed) {
                            location.reload(); // Refresh the page
                        }
                    });
                } else {
                    console.error('Error:', xhr.responseText);
                }
            };

            xhr.onerror = function () {
                console.error('Network error');
            };

            var params = 'goalId=' + encodeURIComponent(goalId) + '&amount=' + encodeURIComponent(amount);
            xhr.send(params);
        } else {
            console.error('Error: Goal ID or Amount is empty');
        }
    });
</script>

<%@include file="sections/foot.jsp" %>
</body>

<%@include file="sections/footer.jsp" %>
</html>
