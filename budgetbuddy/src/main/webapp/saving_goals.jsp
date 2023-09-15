<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<title>Saving and Goals Page </title>
<style>
     .center-container {
          display: flex;
           justify-content: center;
          align-items: center;
        }

        .center {
                text-align: center;
            }

        .topnav input[type=text] {
                padding: 6px;
                border: none;
                margin-top: 8px;
                font-size: 17px;
                background-color: #e9e9e9;
              }
        /* Style the links inside the navigation bar */
        .topnav a {
          text-align: center;
          padding: 10px 15px;
        }
</style>
</head>

<body>
    <!-- NavBar-->
    <div class="center-container">
        <div class="topnav">
            <a href="home.jsp">Home</a>
            <a href="income.jsp">Income</a>
            <a href="expenses.jsp">Expenses</a>
            <a href="wip.jsp">Deductions</a>
            <a href="saving_goals.jsp">Savings</a>
            <a href="trends.jsp">Trends</a>
            <a href="tips_and_knowledge.jsp">Tips & Knowledge</a>
            <a href="payment.jsp">Bill Reminders</a>
            <a href="wip.jsp">Financial Support</a>
            <a href="index.jsp">Logout</a>
         </div>
    </div>

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
            <c:when test="${not empty saving_goals}">
                <c:forEach var="goal" items="${saving_goals}">
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
    <c:if test="${not empty saving_goals}">
        <ul>
            <c:forEach var="goal" items="${saving_goals}">
                
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty saving_goals}">
        <p>No saving goals set yet. <br> Here is a tester <a href="detail_goal.jsp">Computer</a></p>
    </c:if>
    
     <!-- Link to create a Goal -->
    <a href="create_goal.jsp">Create Goal</a>

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