<!-- delete_user.jsp -->
<%@ page import="java.sql.*" %>
<%@ page import="controller.*" %>
<%@ page import="model.*" %>


<%
User user = (User) session.getAttribute("User");
UserController uc = user.getUserController(); 
String userId = uc.getValue(user, "id");

uc.deleteUser(userId);
session.invalidate();
response.sendRedirect("index.jsp");
%>

