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
    <body>
        <div class="container">
            <div class="textcenter">
                <h1>Login Failed! Please create an account or use the correct login details.</h1><br><br><br>
                <h2>
                    <a href="index.jsp">Return to Login/Register</a>
                </h2>
            </div>
        </div>
    <%@include file="sections/foot.jsp" %>
    </body>
<%@include file="sections/footer.jsp" %>
</html>
