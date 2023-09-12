<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<title>Saving and Goals Page </title>
</head>
<body>
    <a href="index.jsp">Home</a>
    <a href="trends.jsp">Trends</a>
    <h1> Saving Goals </h1>
    <p>This page will show data for both savings and goals. Placeholder data is present on this page to show layout</p>
    <h2>Savings Avaliable: </h2>
    <p>$50<c:out value="${formattedSavings}" /></p>
   <br>
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
    
    <a href="creategoal.jsp">Create Goal</a>
</body>
</html>