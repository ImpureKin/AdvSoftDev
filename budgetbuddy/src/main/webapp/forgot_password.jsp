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
        <div class="text-center mt-5">
              <h1>Recover Password</h1><br><br><br><br><br>
        </div>
        <div class="text-center">
            <form action="send_password_recovery.jsp" method="post">
                <div class="form-group">
                    <label class ="mb-2" for="email"><strong>Account Email:</strong></label><br>
                    <div class="d-flex justify-content-center"> 
                        <input type="email" id="email" name="email" class="form-control custom-input" required>
                    </div>
                </div><br>
                <input type="submit" class="btn btn-primary" value="Submit">
            </form>
        </div>
    </div>
    <%@include file="sections/foot.jsp" %>
    </body>
<%@include file="sections/footer.jsp" %>
</html>
