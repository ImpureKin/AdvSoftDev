<%-- 
    Document   : Welcome Page
    Created on : 28 Mar 2023, 6:38:18 pm
    Author     : John
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="sections/navbar.jsp" %>
<%@include file="sections/head.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BudgetBuddy Welcome</title>
    </head>
    <body class="d-flex flex-column h-100">
        <div class="container">
            <div class="text-center">
                <h1>Welcome!</h1><br><br><br>
            </div>
        </div>
    <%@include file="sections/foot.jsp" %>
    </body>
<%@include file="sections/footer.jsp" %>
</html>
