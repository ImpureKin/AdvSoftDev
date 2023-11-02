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
    session.setAttribute("currentEmail", email);
    System.out.println("Save email details in session in login, "+ email);
    String mfa_status = uc.getValue(user, "mfa");
    ConnectionManager.closeConnection(connection);

    if (mfa_status.equals("Disabled")) {
        session.setAttribute("User", user);
        response.sendRedirect("home.jsp");
    }
    else {
        response.sendRedirect("send_mfa.jsp");
    }
}
else {
    ConnectionManager.closeConnection(connection);
    response.sendRedirect("login_fail.jsp");
}
%>