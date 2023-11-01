<!-- send_mfa.jsp -->
<%@ page import="java.sql.*" %>
<%@ page import="controller.*" %>
<%@ page import="model.*" %>
<%@ page import="database.*" %>

<%@include file="sections/logged_out_navbar.jsp" %>
<%@include file="sections/head.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Password Reset</title>
</head>
<body class="d-flex flex-column h-100"><br><br><br>

    <%
    Connection connection = ConnectionManager.getConnection();
    EmailController ec = new EmailController();
    String email = request.getParameter("email");
    ec.sendPasswordRecovery(connection, email);
    ConnectionManager.closeConnection(connection);
    %>

    <div class="container text-center mt-5 mb-4">
        <h1 class="mb-5">Password Sent!</h1>
        <p>If an account is associated with the provided email, a password has been sent for recovery.</p><br>
        <a href="index.jsp" class="btn btn-secondary">Back to Login</a>
    </div>

<%@include file="sections/foot.jsp" %>
</body>
<%@include file="sections/footer.jsp" %>
</html>


