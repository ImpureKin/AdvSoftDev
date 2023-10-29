<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="sections/logged_out_navbar.jsp" %>
<%@include file="sections/head.jsp" %>

<!DOCTYPE html>
<!-- index.jsp -->
<html>
    <head>
        <title>Recover Password</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body class="d-flex flex-column h-100">
    <div class="container">
        <div class="text-center">
              <h1>Recover Password</h1><br><br><br><br><br>
        </div>
        <div class="text-center">
            <form action="send_password_recovery.jsp" method="post">
                <label for="email">Account Email:</label><br><br>
                <input type="email" id="email" name="email" required><br><br>
                <input type="submit" value="Submit">
            </form>
        </div>
    </div>
    <%@include file="sections/foot.jsp" %>
    </body>
<%@include file="sections/footer.jsp" %>
</html>
