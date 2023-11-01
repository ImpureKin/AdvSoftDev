<!-- register.jsp -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="sections/logged_out_navbar.jsp" %>
<%@include file="sections/head.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BudgetBuddy Register</title>
    </head>
    <body class="d-flex flex-column h-100">
    <div class="container">
        <div class="text-center mt-5">
            <h1>Register an Account</h1><br>
        </div>
        <div class="text-center">
    <form action="register_check.jsp">
        <div class="form-row">
            <div class="form-group col mb-2">
                <label class ="mb-2" for="email"><strong>Email:</strong></label>
                 <div class="d-flex justify-content-center"> 
                    <input type="email" id="email" name="email" class="form-control custom-input" required value="${sessionScope.registrationEmail}">
                </div>
            </div>
            <div class="form-group col mb-2">
                <label class ="mb-2" for="password"><strong>Password:</strong></label>
                 <div class="d-flex justify-content-center"> 
                    <input type="password" id="password" name="password" class="form-control custom-input" required>
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col mb-2">
                <label class ="mb-2" for="confirmPassword"><strong>Confirm Password:</strong></label>
                 <div class="d-flex justify-content-center"> 
                    <input type="password" id="confirmPassword" name="confirmPassword" class="form-control custom-input" required>
                </div>
            </div>
            <div class="form-group col mb-2">
                <label class ="mb-2" for="firstName"><strong>First Name:</strong></label>
                 <div class="d-flex justify-content-center"> 
                    <input type="text" id="firstName" name="firstName" class="form-control custom-input" required value="${sessionScope.registrationFirstName}">
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col mb-2">
                <label class ="mb-2" for="lastName"><strong>Last Name:</strong></label>
                 <div class="d-flex justify-content-center"> 
                    <input type="text" id="lastName" name="lastName" class="form-control custom-input" required value="${sessionScope.registrationLastName}">
                </div>
            </div>
            <div class="form-group col mb-2">
                <label class ="mb-2" for="phone"><strong>Phone Number:</strong></label>
                 <div class="d-flex justify-content-center"> 
                    <input type="text" id="phone" name="phone" class="form-control custom-input" required value="${sessionScope.registrationPhone}">
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col mb-2">
                <label class ="mb-2" for="dob"><strong>Date of Birth:</strong></label>
                 <div class="d-flex justify-content-center"> 
                    <input type="date" id="dob" name="dob" class="form-control custom-input" required value="${sessionScope.registrationDob}">
                </div>
            </div>
            <div class="form-group col mb-2">
                <label class ="mb-2"><strong>Gender:</strong></label><br>
                <div class="form-check form-check-inline">
                    <input class="form-check-input mb-2" type="radio" name="gender" id="genderMale" value="Male" 
                        ${sessionScope.registrationGender == 'Male' ? 'checked' : ''}>
                    <label class="form-check-label" for="genderMale">Male</label>
                </div>
                <div class="form-check form-check-inline mb-2">
                    <input class="form-check-input mb-2" type="radio" name="gender" id="genderFemale" value="Female" 
                        ${sessionScope.registrationGender == 'Female' ? 'checked' : ''}>
                    <label class="form-check-label mb-2" for="genderFemale">Female</label>
                </div>
            </div>
        </div>

        <input type="submit" id="submit" class="btn btn-primary mb-2" value="Submit">
        </form>
    </div>

    </div>
    <%@include file="sections/foot.jsp" %>
    </body>
<%@include file="sections/footer.jsp" %>
</html>
