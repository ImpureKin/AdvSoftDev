<%-- 

// check if email and password are set in session
String email = request.getParameter("email");
String password = request.getParameter("password");
String sessionEmail = (String) session.getAttribute("registeredEmail");
String sessionPassword = (String) session.getAttribute("registeredPassword");

if (email.equals(sessionEmail) && password.equals(sessionPassword)) {
    response.sendRedirect("home.jsp");
}
else {
    response.sendRedirect("login_fail.jsp");
}
%>
--%>

<%@ page import="java.sql.*" %>
<%@ page import="database.DB" %>

<%

Connection conn = DB.getConnection();

// check if email and password are set in session
String email = request.getParameter("email");
String password = request.getParameter("password");

if (DB.authenticateUser(conn, email, password)) {
    response.sendRedirect("home.jsp");
}
else {
    response.sendRedirect("login_fail.jsp");
}
%>