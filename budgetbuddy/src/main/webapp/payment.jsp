<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="sections/navbar.jsp" %>
<%@include file="sections/head.jsp" %>

<%@ page import="java.sql.*" %>
<%@ page import="controller.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>

<%
    session.setAttribute("payments", new PaymentController().getListPayments());
    System.out.println("Email = " + session.getAttribute("currentEmail"));
%>
<html>
<head>
    <title>Payment</title>
    <style>
        .center-container {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .page-header {
            text-align: center;
            background: linear-gradient(to right, #ffae00, #80b300);
            color: #fff;
            font-size: 24px;
        }


        .topnav input[type=text] {
            padding: 6px;
            border: none;
            margin-top: 8px;
            font-size: 17px;
            background-color: #e9e9e9;
        }

        /* Style the links inside the navigation bar */
        .topnav a {
            text-align: center;
            padding: 10px 15px;
        }

        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: center;
            padding: 8px;
        }

        td:hover {
            background-color: rgba(255, 213, 0, 0.91);
        }

        tr:nth-child(even) {
            background-color: rgba(255, 213, 0, 0.71);
        }

        h2 {
            text-align: center;
        }

        form {
            width: 35%;
            margin: auto;
        }

        .form-group {
            width: 100%;
            margin-bottom: 15px;
        }

        label {
            width: 100%;
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="number"],
        input[type="date"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .button-container {
            text-align: center; /* Canh giữa nút */
        }

        button[type="submit"] {
            width: 60%;
            background-color: #007BFF;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 20px;
            cursor: pointer;
            background: linear-gradient(to left, #ffd500, #252510);
            transition: ease-in-out 2s;
        }

        button[type="submit"]:hover {
            /*background-color: #0056b3;*/
            background: linear-gradient(to right, #ffd500, #252510);
            transition: 2s ease-in-out;
        }

        .form-box {
            padding: 15px;
            border: 1px solid #ffd500;
            border-radius: 5px;
            box-shadow: 5px 5px 10px #ffd500;
        }
    </style>
</head>
<body>
<div class="page-header">
    <h1>Payment Management System</h1>
</div>

<div style="width: 100%; margin: auto; margin-bottom: 20px  ">
    <form action="save_payment_check.jsp" class="form-box">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${sessionScope.paymentName}" required>
        </div>
        <div class="form-group">
            <label for="amount">Amount:</label>
            <input type="number" id="amount" name="amount" value="${sessionScope.paymentAmount}" required>
        </div>
        <div class="form-group">
            <label for="dob">Date:</label>
            <input type="date" id="dob" name="dob" value="${sessionScope.paymentDob}" required>
        </div>
        <div class="button-container">
            <button type="submit">Submit</button>
        </div>
    </form>
</div>

<div class="table_box" style="margin: auto; width: 100%">
    <table style="width: 90%; margin: auto; border: 3px solid #ffd500; box-shadow: 3px 5px 7px #838383">
        <tr>
            <th>Name</th>
            <th>Amount</th>
            <th>Date</th>
        </tr>

        <%
            List<Payment> payments = (List<Payment>) session.getAttribute("payments");
            if (payments != null) {
                for (Payment payment : payments) {
        %>
        <tr>
            <td><%= payment.getName() %>
            </td>
            <td><%= payment.getAmount() %>
            </td>
            <td><%= payment.getDob() %>
            </td>
        </tr>
        <%
                }
            }
        %>

    </table>
</div>
<%@include file="sections/foot.jsp" %>
</body>
<%@include file="sections/footer.jsp" %>
</html>
