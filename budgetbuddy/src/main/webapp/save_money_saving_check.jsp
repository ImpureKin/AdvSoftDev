<%@ page import="java.sql.*" %>
<%@ page import="controller.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>


<%
    MoneySavingController moneySavingController = new MoneySavingController();
    String name = request.getParameter("name");
    String totalAmount = request.getParameter("totalAmount");
    String interestRate = request.getParameter("interest-rate");
    String totalMonth = request.getParameter("totalMonth");
    String userId = session.getAttribute("currentEmail").toString();

    List<String> status_message = new ArrayList<String>();

    if (interestRate.isEmpty()) {
        status_message.add("Required interest rate");
        session.setAttribute("StatusMessage", status_message);
        response.sendRedirect("save_money_saving_fail.jsp");
    } else {
        status_message = moneySavingController.saveMoneySaving(name, totalAmount, interestRate, totalMonth, userId);
        if (status_message == null || status_message.isEmpty()) {
            response.sendRedirect("financial_support.jsp");
        } else {
            session.setAttribute("name", name);
            session.setAttribute("totalAmount", totalAmount);
            session.setAttribute("interest-rate", interestRate);
            session.setAttribute("totalMonth", totalMonth);
            session.setAttribute("StatusMessage", status_message);
            response.sendRedirect("save_money_saving_fail.jsp");
        }
    }

    session.setAttribute("moneySavings", moneySavingController.getListMoneySaving(session.getAttribute("currentEmail").toString()));
%>

<script>
    window.location.reload();
</script>