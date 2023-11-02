<!-- delete_user.jsp -->
<%@ page import="java.sql.*" %>
<%@ page import="controller.*" %>
<%@ page import="database.*" %>
<%@ page import="model.*" %>


<%
Connection connection = ConnectionManager.getConnection();
User user = (User) session.getAttribute("User");
UserController uc = user.getUserController(); 
String userId = uc.getValue(user, "id");

uc.deleteUser(connection, userId);
ConnectionManager.closeConnection(connection);
session.invalidate();
response.sendRedirect("index.jsp");
%>

