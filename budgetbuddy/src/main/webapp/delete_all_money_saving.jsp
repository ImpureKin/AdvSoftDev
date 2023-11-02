<%@ page import="controller.MoneySavingController" %>
<%@ page import="java.util.ArrayList" %>

<%
    MoneySavingController moneySavingController = new MoneySavingController();
    moneySavingController.deleteAll(session.getAttribute("currentEmail").toString());

    session.setAttribute("moneySavings", null);

    response.sendRedirect("financial_support.jsp");
%>
