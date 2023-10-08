

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="sections/navbar.jsp" %>
<%@include file="sections/head.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>BudgetBuddy Welcome</title>
    </head>
    <body>
        
        <div class="container">
            <div class="textcenter">
                <h1>Welcome!</h1><br><br><br>
                <% 
                // get the user inputs
                String email = (String) session.getAttribute("registeredEmail");
                String password = (String) session.getAttribute("registeredPassword");
                %>
                <h2>
                    <%= "Email: " + email%><br><br><br>
                    <%= "Password: " + password%><br><br><br>
                    <a href="index.jsp">Logout</a>
                </h2>
            </div>
        </div>
    </body>
</html>
