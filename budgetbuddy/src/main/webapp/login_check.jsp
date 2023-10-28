<!-- login_check.jsp -->
<%@ page import="java.sql.*" %>
<%@ page import="controller.*" %>
<%@ page import="model.*" %>
<%@ page import="database.*" %>


<%
LoginController lc = new LoginController();
UserController uc = new UserController();
// check if email and password are set in session
String email = request.getParameter("email");
String password = request.getParameter("password");
Connection connection = ConnectionManager.getConnection();

if (lc.isValidLogin(connection, email, password) == null) {
    // Save user details in session
    User user = uc.getUser(connection, email);
    System.out.println("Save email details in session in login, "+ email);
    session.setAttribute("currentEmail", email);
    session.setAttribute("User", user);
    ConnectionManager.closeConnection(connection);
    response.sendRedirect("home.jsp");
}
else {
    response.sendRedirect("login_fail.jsp");
}
%>