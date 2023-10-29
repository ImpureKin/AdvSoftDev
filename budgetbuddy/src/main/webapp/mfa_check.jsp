<!-- mfa_check.jsp -->
<%@ page import="java.sql.*" %>
<%@ page import="controller.*" %>
<%@ page import="model.*" %>
<%@ page import="database.*" %>

<%
String enteredMFA = request.getParameter("mfaCode");
String expectedMfa = (String) request.getParameter("expectedMfa");

if (enteredMFA != null && enteredMFA.equals(expectedMfa)) {
    // MFA code is correct
    Connection connection = ConnectionManager.getConnection();
    UserController uc = new UserController();
    User user = uc.getUser(connection, (String) session.getAttribute("currentEmail"));
    session.setAttribute("User", user);
    ConnectionManager.closeConnection(connection);
    response.sendRedirect("home.jsp");
} else { %>
    <%@include file="sections/logged_out_navbar.jsp" %>
    <%@include file="sections/head.jsp" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>MFA Verification</title>
    </head>
    <body>
        <h1>MFA Verification</h1>
        <p>MFA code is incorrect. Please try again.</p>
        <form action="mfa_check.jsp" method="post">
            Enter your MFA code: <input type="text" name="mfaCode">
            <input type="submit" value="Submit">
            <input type="hidden" name="expectedMfa" value="<%= expectedMfa %>">
        </form>
    <%@include file="sections/foot.jsp" %>
    </body>
    <%@include file="sections/footer.jsp" %>
    </html>
<%
}
%>



