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
    <body class="d-flex flex-column h-100">
     <div class="container">
        <div class="text-center mt-5">
            <h1 class="h1-title">Welcome to BudgetBuddy!</h1><br>
        </div>
       <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6 text-center">
                    <form action="login_check.jsp" method="post">
                        <div class="form-group">
                                <label class ="mb-2" for="email"><strong>Email:</strong></label><br>
                                <div class="d-flex justify-content-center"> 
                                     <input type="email" id="email" name="email" class="form-control custom-input" required><br>
                                </div>
                        </div>
                        <br>
                        <div class="form-group">
                            <label class ="mb-2" for="password"><strong>Password:</strong></label><br>
                            <div class="d-flex justify-content-center"> 
                                <input type="password" id="password" name="password" class="form-control custom-input" required>
                            </div>
                        </div>
                        <br>
                        <button class="btn btn-orange-custom" type="submit">Submit</button> <br>
                    </form>
                </div>
            </div>
        </div>

        <div class="text-center mt-4">
            <h5>Forgot your password?</h5>
            <button class="btn btn-secondary" type="button" class="btn btn-link" onClick="location.href='forgot_password.jsp'">Recover Password</button>
        </div>

        <div class="text-center mt-4 mb-4">
            <h5>Don't have an account yet?</h5>
            <button class="btn btn-secondary" type="button" class="btn btn-link" onClick="location.href='register.jsp'">Register</button>
        </div>
    </div>
    <br>
    <%@include file="sections/foot.jsp" %>
    </body>
<%@include file="sections/footer.jsp" %>
</html>
