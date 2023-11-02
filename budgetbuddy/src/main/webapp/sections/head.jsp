<meta name="viewport" content="width=device-width, initial-scale=1"> 
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<link href='https://fonts.googleapis.com/css?family=Bebas Neue' rel='stylesheet'>
<%
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
    request.setAttribute("currencyFormatter", currencyFormatter);
%>