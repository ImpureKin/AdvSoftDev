<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="sections/logged_out_navbar.jsp" %>
<%@include file="sections/head.jsp" %>

<!DOCTYPE html>
<!-- index.jsp -->
<html>
    <head>
        <title>BudgetBuddy Homepage</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
    <div class="container">
        <div class="titlecenter">
              <h1>Welcome to BudgetBuddy!</h1><br><br><br><br><br>
        </div>
        <div class="textcenter">
            <form action="login_check.jsp" method="post">
                <label for="email">Email:</label><br><br>
                <input type="email" id="email" name="email" required><br><br>
                <label for="password">Password:</label><br><br>
                <input type="password" id="password" name="password" required><br><br><br>
                <input type="submit" value="Submit">
            </form>
        </div>
        <div class="notecenter">
            <h3>Don't have an account yet?</h3>
        </div>
        <div class="buttoncenter">
            <button type="button" style="height:40px;width:100px" onClick="location.href='register.jsp'">Register</button><br><br><br><br><br><br><br><br>
        </div>
    </div>
    <%@include file="sections/foot.jsp" %>
    </body>
<%@include file="sections/footer.jsp" %>
</html>
