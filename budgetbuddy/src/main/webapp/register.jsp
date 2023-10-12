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
    <body>
        <div class="container">
            <div class="text-center">
                <h1>Register an Account</h1><br><br><br><br><br>
            </div>
            <div class="text-center">
                <form action="register_check.jsp">
                    <label for="email">Email:</label><br><br>
                    <input type="email" id="email" name="email" required value="${sessionScope.registrationEmail}"><br><br>

                    <label for="password">Password:</label><br><br>
                    <input type="password" id="password" name="password" required><br><br><br>

                    <label for="confirmPassword">Confirm Password:</label><br><br>
                    <input type="password" id="confirmPassword" name="confirmPassword" required><br><br><br>

                    <label for="firstName">First Name:</label><br><br>
                    <input type="text" id="firstName" name="firstName" required value="${sessionScope.registrationFirstName}"><br><br>

                    <label for="lastName">Last Name:</label><br><br>
                    <input type="text" id="lastName" name="lastName" required value="${sessionScope.registrationLastName}"><br><br>

                    <label for="phone">Phone Number:</label><br><br>
                    <input type="text" id="phone" name="phone" required value="${sessionScope.registrationPhone}"><br><br>

                    <label for="dob">Date of Birth:</label><br><br>
                    <input type="date" id="dob" name="dob" required value="${sessionScope.registrationDob}"><br><br>

                    <label for="gender">Gender:</label><br>
                    <input type="radio" id="genderMale" name="gender" value="Male" 
                        ${sessionScope.registrationGender == 'Male' ? 'checked' : ''}>
                    <label for="genderMale">Male</label><br>
                    
                    <input type="radio" id="genderFemale" name="gender" value="Female" 
                        ${sessionScope.registrationGender == 'Female' ? 'checked' : ''}>
                    <label for="genderFemale">Female</label><br><br>
                    

                    <input type="submit" id="submit" value="Submit">
                </form>
            </div>
        </div>
    <%@include file="sections/foot.jsp" %>
    </body>
<%@include file="sections/footer.jsp" %>
</html>
