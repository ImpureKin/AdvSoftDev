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
//    System.out.println("Email = " + session.getAttribute("currentEmail"));
%>
<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .page {
            border: 2px #b461cb solid;
            border-radius: 40px;
            margin: 20px 40px;
            max-height: fit-content;
        }

        .page-header {
            text-align: center;
            background: linear-gradient(to right, #ebd0f1, #e084db);
            color: #000000;
            font-size: 24px;
            font-family: 'Lora', serif;
            border-radius: 40px 40px 0px 0px;

            /*border: 2px #b461cb solid;*/
            margin-bottom: 20px;
        }
        .box {
            margin: 0px 450px;
            position: absolute;
            width: 45%;
            height: 330px;
            display: flex;
            justify-content: center;
            align-items: center;
            overflow: hidden;
            border-radius: 20px;
        }
        .box::before {
            content: "";
            position: absolute;
            width: 45%;
            height: 130%;
            background-color: white;
            box-shadow: 0 0 20px rgb(162, 22, 201);
            animation: animate 5s linear infinite;
        }
        .box::after {
            content: "";
            position: absolute;
            inset: 20px;
            background-color: #ffffff;
            border-radius: 16px;
        }

        @keyframes animate {
            0% {
                transform: rotate(0deg);
            }
            100% {
                transform: rotate(360deg);
            }
        }

        .box .container-wip{
            position: absolute;
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
            font-size: 20px;
            font-family: 'Lora', serif;

        }
        th {
            background-color: #e084db;
            font-style: oblique;

        }

        td:hover {
            background-color: rgb(224, 132, 219);
        }

        tr:nth-child(odd) {
            background-color: rgb(224, 198, 231);
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
            /*margin-bottom: 15px;*/
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
            margin-top: 20px;
        }

        @keyframes color-change {
            0% {
                background: linear-gradient(to right, #ff59f6, #d29fde);
            }
            50% {
                background: linear-gradient(to right, #d29fde, #ff59f6);
            }
            100% {
                background: linear-gradient(to right, #ff59f6, #d29fde);
            }
        }

        button[type="submit"] {
            width: 60%;
            color: #000000;
            padding: 10px 20px;
            border: none;
            border-radius: 20px;
            cursor: pointer;
            background: linear-gradient(to right, #ff59f6, #d29fde);
            transition: background-color ease-in-out 2s;
            font-size: 20px;
            font-family: 'Lora', serif;
            font-style: initial;
            font-weight: bold;
            animation: color-change 2s infinite alternate;
        }

        button[type="submit"]:hover {
            /*background-color: #0056b3;*/
            background: linear-gradient(to right, #ebd0f1, #c978e0);
            transition: 2s ease-in-out;
        }



        .form-box {
            position: relative;
            padding: 15px;
            border: 2px solid #c978e0;
            box-shadow: 5px 5px 10px #d29fde;
            justify-content: center;
            align-items: center;
            overflow: hidden;
            border-radius: 20px;
        }


        h1 {
            background: linear-gradient(45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
            -webkit-background-clip: text;
            background-clip: text;
            -webkit-text-fill-color: transparent;
            animation: hhe 5s ease-in-out infinite;
            /*text-align: center;*/
            /*line-height: 0.9;*/
        }


    </style>
</head>
<body class="d-flex flex-column h-100">
<div class="page">
    <div class="page-header">
        <h1 class=" w3-container w3-center w3-animate-top" style="font-weight: bold; margin-top: 0; padding-bottom: 15px">Payment Management System</h1>
    </div>

    <div class="box">

    </div>
    <div style="width: 100%; margin: auto; margin-bottom: 20px" class="form-container">
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

    <div class="table_box" style="margin: 20px auto; width: 70%; border-radius: 20px;  border:2px solid #b461cb">
        <table style="width: 100%; margin: auto; box-shadow: 3px 5px 7px #838383; border-radius: 20px; overflow: hidden">
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
</div>
<%@include file="sections/foot.jsp" %>
</body>
<%@include file="sections/footer.jsp" %>
</html>
