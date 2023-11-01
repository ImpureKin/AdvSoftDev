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
<body class="d-flex flex-column h-100">

    <%
    EmailController ec = new EmailController();
    String email = (String) session.getAttribute("currentEmail");
    String expectedMfa = ec.sendMfaCode(email);
    %>
    <div class="container text-center mt-5">
    <br><br><br>
    <h1>MFA Verification</h1>
    <p>Your MFA code has been sent to your email address.</p>
    <div class="d-flex justify-content-center">
        <form action="mfa_check.jsp" method="post" class="col-md-6 mt-5">
            <div class="form-group">
                <label class ="mb-2" for="mfaCode"><strong>Enter your MFA code:</strong></label>
                <div class="d-flex justify-content-center"> 
                     <input type="text" class="form-control custom-input" id="mfaCode" name="mfaCode" required>
                </div>
            </div>
            <br>
            <input type="submit" class="btn btn-primary" value="Submit">
            <input type="hidden" name="expectedMfa" value="<%= expectedMfa %>">
        </form>
    </div>
</div>
<%@include file="sections/foot.jsp" %>
</body>
<%@include file="sections/footer.jsp" %>
</html>


