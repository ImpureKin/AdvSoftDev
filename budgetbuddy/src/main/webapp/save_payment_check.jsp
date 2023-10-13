<%@ page import="java.sql.*" %>
<%@ page import="controller.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>


<%
    PaymentController paymentController = new PaymentController();
    String name = request.getParameter("name");
    String amount = request.getParameter("amount");
    String dob = request.getParameter("dob");

    List<String> status_message = new ArrayList<String>();
    List<Payment> payments = paymentController.getListPayments();
    int total = 0;
    for (Payment payment : payments) {
        if (name.equals(payment.getName()) && dob.equals(payment.getDob())) {
            total += Integer.parseInt(payment.getAmount());
        }
    }

    if (total + Integer.parseInt(amount) >= 300) {
        status_message.add("The amount cannot exceed $300");
        session.setAttribute("StatusMessage", status_message);
        response.sendRedirect("save_payment_fail.jsp");
    } else {
        status_message = paymentController.savePayment(name, amount, dob);
        if (status_message == null || status_message.isEmpty()) {
            response.sendRedirect("payment.jsp");
        } else {
            session.setAttribute("paymentName", name);
            session.setAttribute("paymentAmount", amount);
            session.setAttribute("paymentDob", dob);
            session.setAttribute("StatusMessage", status_message);
            response.sendRedirect("save_payment_fail.jsp");
        }
    }

    session.setAttribute("payments", paymentController.getListPayments());
%>

<script>
    window.location.reload();
</script>