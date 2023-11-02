<!-- login_fail.jsp -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="sections/logged_out_navbar.jsp" %>
<%@include file="sections/head.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Failed</title>
    </head>
    <body class="d-flex flex-column h-100">
    <div class="container text-center mt-5 mb-5">
        <br><br><br><br>
        <h1>Login Failed! Please create an account or use the correct login details.</h1><br><br><br>
        <button class="btn btn-secondary mb-5" onclick="location.href='index.jsp'">Return to Login/Register</button>
    </div>
    <%@include file="sections/foot.jsp" %>
    </body>
<%@include file="sections/footer.jsp" %>
</html>
