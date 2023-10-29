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
    <title>MFA Verification</title>
</head>
<body>

    <%
    EmailController ec = new EmailController();
    String email = (String) session.getAttribute("currentEmail");
    String expectedMfa = ec.sendMfaCode(email);
    %>

    <h1>MFA Verification</h1>
    <p>Your MFA code has been sent your email address.</p>
    
    <form action="mfa_check.jsp" method="post">
        Enter your MFA code: <input type="text" name="mfaCode">
        <input type="submit" value="Submit">
        <input type="hidden" name="expectedMfa" value="<%= expectedMfa %>">
    </form>

<%@include file="sections/foot.jsp" %>
</body>
<%@include file="sections/footer.jsp" %>
</html>


