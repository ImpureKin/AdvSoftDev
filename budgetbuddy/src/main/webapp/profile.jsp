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
    <body class="d-flex flex-column h-100">
    <div class="container">
        <div class="row">
            <div class="col-md-6 mt-5">
                <form action="edit_user.jsp" method="POST">
                    <h3 class="mb-3">Your current account details:</h3>
                    <% 
                    User user = (User) session.getAttribute("User");
                    UserController uc = user.getUserController(); 
                    %>
                    <div class="mb-3">
                        <label for="currentEmail" class="form-label"><strong>Email:</strong></label>
                        <p class="form-control-static"><%= uc.getValue(user, "email") %></p>
                    </div>
                    <div class="mb-3">
                        <label for="currentFirstName" class="form-label"><strong>First Name:</strong></label>
                        <p class="form-control-static"><%= uc.getValue(user, "firstName") %></p>
                    </div>
                    <div class="mb-3">
                        <label for="currentLastName" class="form-label"><strong>Last Name:</strong></label>
                        <p class="form-control-static"><%= uc.getValue(user, "lastName") %></p>
                    </div>
                    <div class="mb-3">
                        <label for="currentPassword" class="form-label"><strong>Password:</strong></label>
                        <p class="form-control-static"><%= uc.getValue(user, "password") %></p>
                    </div>
                    <div class="mb-3">
                        <label for="currentPhoneNumber" class="form-label"><strong>Phone Number:</strong></label>
                        <p class="form-control-static"><%= uc.getValue(user, "phoneNumber") %></p>
                    </div>
                    <div class="mb-3">
                        <label for="currentDob" class="form-label"><strong>Date of Birth:</strong></label>
                        <p class="form-control-static"><%= uc.getValue(user, "dob") %></p>
                    </div>
                    <div class="mb-3">
                        <label for="currentGender" class="form-label"><strong>Gender</strong></label>
                        <p class="form-control-static"><%= uc.getValue(user, "gender") %></p>
                    </div>

                    <% 
                    String mfa_status = uc.getValue(user, "mfa");
                    %>
                    <div class="mb-3">
                        <label class="form-label"><strong>MFA Status:</strong></label>
                        <p class="form-control-static"><%= mfa_status %></p>
                    </div>
                </form>
            </div>
            <div class="col-md-6 mt-5">
                <form action="edit_user.jsp" method="POST">
                    <h3 class="mb-3">Edit your account details below:</h3>
                    <div class="mb-3">
                        <label for="email" class="form-label"><strong>Email:</strong></label>
                        <input type="email" class="form-control" id="email" name="email">
                    </div>
                    <div class="mb-3">
                        <label for="firstName" class="form-label"><strong>First Name:</strong></label>
                        <input type="text" class="form-control" id="firstName" name="firstName">
                    </div>
                    <div class="mb-3">
                        <label for="lastName" class="form-label"><strong>Last Name:</strong></label>
                        <input type="text" class="form-control" id="lastName" name="lastName">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label"><strong>Password:</strong></label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <div class="mb-3">
                        <label for="confirmPassword" class="form-label"><strong>Confirm Password:</strong></label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label"><strong>Phone Number:</strong></label>
                        <input type="text" class="form-control" id="phone" name="phone">
                    </div>
                    <div class="mb-3">
                        <label for="dob" class="form-label"><strong>Date of Birth:</strong></label>
                        <input type="date" class="form-control" id="dob" name="dob">
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><strong>MFA:</strong></label>
                        <select class="form-select" id="mfa" name="mfa">
                            <option value="Enabled" <%= mfa_status.equals("Enabled") ? "selected" : "" %>>Enabled</option>
                            <option value="Disabled" <%= mfa_status.equals("Disabled") ? "selected" : "" %>>Disabled</option>
                        </select>
                    </div>
                    <div class="mb-3 d-flex justify-content-center">
                        <button type="submit" class="btn btn-orange-custom mx-2">Save</button>
                        <button type="button" class="btn btn-secondary mx-2" onClick="location.href='home.jsp'">Cancel</button>
                        <input type="hidden" name="submitted" value="yes">
                    </div>
                </form>
            </div>
        </div>

        <div class="text-center mb-5 mt-5">
            <form class="mt-3 d-inline-block" action="delete_user.jsp" method="POST">
                <h2>Delete your account?</h2>
                <p> WARNING! This will remove your account entirely. This cannot be undone!</p> 
                <button type="submit" class="btn btn-danger-custom">Delete Account</button>
            </form>
        </div>
    </div>

    <%@include file="sections/foot.jsp" %>
</body>

<%@include file="sections/footer.jsp" %>
</html>
