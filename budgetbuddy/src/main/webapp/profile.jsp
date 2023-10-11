<%-- 
    Document   : Profile
    Created on : 28 Mar 2023, 6:38:18 pm
    Author     : John
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
        <title>Profile</title>
    </head>
    <body>
        <div class="container">

            <form action="edit_user.jsp" method="POST">
              <h3> Your current account details: </h3>
              <% 
              User user = (User) session.getAttribute("User");
              UserController uc = user.getUserController(); 
              %>
              <p>Email: <%= uc.getValue(user, "email") %></p>
              <p>First Name: <%= uc.getValue(user, "firstName") %></p>
              <p>Last Name: <%= uc.getValue(user, "lastName") %></p>
              <p>Password: <%= uc.getValue(user, "password") %></p>
              <p>Phone Number: <%= uc.getValue(user, "phoneNumber") %></p>
              <p>Date of Birth: <%= uc.getValue(user, "dob") %></p>
              <p>Gender: <%= uc.getValue(user, "gender") %></p>


              <br>
              <h3> Edit your account details below: </h3>
              <table>
                  <tr>
                          <td><label for="email">Email:</label></td>
                          <td><input type="email" name="email"></td>
                  </tr>
                  <tr>
                          <td><label for="firstName">First Name:</label></td>
                          <td><input type="text" name="firstName"></td>
                  </tr>
                  <tr>
                          <td><label for="lastName">Last Name:</label></td>
                          <td><input type="text" name="lastName"></td>
                  </tr>
                  <tr>
                          <td><label for="password">Password:</label></td>
                          <td><input type="password" name="password"></td>
                  </tr>
                  <tr>
                    <td><label for="confirmPassword">Confirm Password:</label></td>
                    <td><input type="password" name="confirmPassword"></td>
                  </tr>
                  <tr>
                          <td><label for="phone">Phone Number:</label></td>
                          <td><input type="text" name="phone"></td>
                  </tr>
                  <tr>
                          <td><label for="dob">Date of Birth:</label></td>
                          <td><input type="date" name="dob"></td>
                  </tr>
              </table>
              <br>
              <input type="submit" value="Save">
              <button type="button" style="height:20px;width:75px" onClick="location.href='home.jsp'">Cancel</button>
              <input type="hidden" name="submitted" value="yes">
            </form>
            <form action="delete_user.jsp" method="POST">
              <h2>Delete your account?</h2>
              <p> WARNING! This will remove your account entirely. This cannot be undone!</p> 
              <input type="submit" value="Delete Account">
            </form>
        </div>
      <%@include file="sections/foot.jsp" %>
      </body>
<%@include file="sections/footer.jsp" %>
</html>
