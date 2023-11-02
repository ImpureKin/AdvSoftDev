<%-- 
    Document   : Welcome Page
    Created on : 28 Mar 2023, 6:38:18 pm
    Author     :
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="sections/navbar.jsp" %>
<%@include file="sections/head.jsp" %>
<%@ page import="model.*" %>
<%@ page import="controller.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BudgetBuddy Welcome</title>
    </head>
     <% 
             User user = (User) session.getAttribute("User");
             UserController uc = user.getUserController(); 
     %>
<body class="d-flex flex-column h-100" background="images/final.png" style="background-image: url('images/final.png'); background-size: cover; background-position: center; background-repeat: no-repeat;">
   <div class="container-xxl mb-5">
        <div class="text-white mt-5">
                <h1>Welcome, <%= uc.getValue(user, "firstName") %>!</h1>

        </div>
    </div>
   <div class="d-flex flex-column mh-100" style="padding: 330px;">
       
    </div>
</div>
</body>
<%@include file="sections/foot.jsp" %>
<%@include file="sections/footer.jsp" %>
</html>
