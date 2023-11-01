<!-- register_fail.jsp -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="sections/logged_out_navbar.jsp" %>
<%@include file="sections/head.jsp" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BudgetBuddy Register</title>
    </head>
    <body class="d-flex flex-column h-100">
    <div class="container">
        <div class="text-center mt-5">
            <h1>Registration Failed!</h1><br><br><br><br><br>
        </div>
        <div class="text-center mt-4">
            <% List<String> status_message = (List<String>) session.getAttribute("StatusMessage"); %>
            <h1>
            <% if (!status_message.isEmpty()) { %>
                <ul class="list-unstyled">
                    <% for (String message : status_message) { %>
                        <li><%= message %></li>
                    <% } %>
                </ul>
            <% } %>
            </h1>
        </div>
        <div class="text-center mt-4">
            <h2>
                <a href="register.jsp">Return to Register</a>
            </h2>
        </div>
    </div>
    <%@include file="sections/foot.jsp" %>
</body>
<%@include file="sections/footer.jsp" %>
</html>
