<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<title>Saving and Goals Page </title>
</head>
<body>
    <a href="home.jsp">Home</a>
    <a href="trends.jsp">Trends</a>
    <h1> Saving Goals </h1>
    <p>This page will show data for both savings and goals. Placeholder data is present on this page to show layout</p>

     <!-- Shows money avaliable for goals -->
    <h2>Savings Avaliable: </h2>
    <p>$50<c:out value="${formattedSavings}" /></p>

     <!-- Add Money to a saving goal avaliable -->
    <h2>Add Money to Goal</h2>
    <form action="" method="post">
        <label for="goalId">Select Goal:</label>
        <select id="goalId" name="goalId">
        <c:choose>
            <c:when test="${not empty savingGoals}">
                <c:forEach var="goal" items="${savingGoals}">
                    <option value="${goal.id}"><c:out value="${goal.name}" /></option>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <option value="" disabled>No goals available</option>
            </c:otherwise>
        </c:choose>
        </select><br>
        <label for="amount">Amount:</label>
        <input type="number" id="amount" name="amount" disabled required><br>
        <input type="submit" value="Add Money">
    </form>
   <br>

    <!-- Shows a list of goals  -->
    <h2>Saving Goals</h2>
    <c:if test="${not empty savingGoals}">
        <ul>
            <c:forEach var="goal" items="${savingGoals}">
                
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty savingGoals}">
        <p>No saving goals set yet. <br> Here is a tester <a href="detailgoal.jsp">Computer</a></p>
    </c:if>
    
     <!-- Link to create a Goal -->
    <a href="creategoal.jsp">Create Goal</a>

    <!-- Script to enable and disable adding money to a goal -->
    <script>
    function validateForm() {
        var selectGoal = document.getElementById('goalId');
        var amountField = document.getElementById('amount');
        if (selectGoal.value === '') {
            alert('No goals available. Please add money to savings directly.');
            return false; 
        }else {
            amountField.disabled = false; 
        }
        return true;
    }
</script>
</body>
</html>