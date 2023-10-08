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

<!-- login_check.jsp -->
<%@ page import="java.sql.*" %>
<%@ page import="controller.*" %>
<%@ page import="model.*" %>


<%
LoginController lc = new LoginController();
UserController uc = new UserController();
// check if email and password are set in session
String email = request.getParameter("email");
String password = request.getParameter("password");

if (lc.isValidLogin(email, password) == null) {
    // Save user details in session
    User user = uc.getUser(email);
    session.setAttribute("User", user);
    response.sendRedirect("home.jsp");
}
else {
    response.sendRedirect("login_fail.jsp");
}
%>