<%@ page import="java.sql.*" %>
<%@ page import="database.*" %>

<%

Connection conn = DB.getConnection(request);

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