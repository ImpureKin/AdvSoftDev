<%@ page import="model.MoneySavings" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="sections/navbar.jsp" %>
<%@include file="sections/head.jsp" %>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
<head>
    <style>
        * {
            box-sizing: border-box;
            padding: 0px;
            margin: 0px;
        }

        .container {
            background-color: #fff;
            /*border: 1px solid #ccc;*/
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
            width: 100%;
        }

        h1 {
            text-align: center;
            background-image: linear-gradient(to right, black, #ffff00);
            -webkit-background-clip: text;
            color: transparent;
            font-weight: bold;
        }

        form {
            width: 400px;
            margin: auto;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }

        button:hover {
            background-color: #0056b3;
        }

        #interest-rate {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
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
    </style>
    <title>BudgetBuddy - WIP Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="container">
       <h1>Money Savings Information</h1>
    <form action="save_money_saving_check.jsp" id="money-savings-form">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name">
        </div>
        <div class="form-group">
            <label for="totalAmount">Total Amount:</label>
            <input type="number" id="totalAmount" name="totalAmount">
        </div>
        <div class="form-group">
            <label for="interest-rate">Interest:</label>
            <select id="interest-rate" name="interest-rate">
                <option value="">Select interest rate</option>
                <option value="0.5">1 month - 0.5%</option>
                <option value="1.0">3 months - 1.0%</option>
                <option value="1.5">6 months - 1.5%</option>
                <option value="2.0">12 months - 2.0%</option>
            </select>
        </div>
        <div class="form-group">
            <label for="totalMonth">Total Month:</label>
            <input type="number" id="totalMonth" name="totalMonth">
        </div>
        <button type="submit">Submit</button>
    </form>

    <div class="table_box" style="margin: 20px auto; width: 100%">
        <table style="width: 90%; margin: auto; border: 3px solid #ffd500; box-shadow: 3px 5px 7px #838383">
            <tr>
                <th>No</th>
                <th>Name</th>
                <th>Interest</th>
                <th>Total Amount</th>
                <th>Total Month</th>
                <th>Created date</th>
                <th>Interest rate</th>
            </tr>

            <%
                int cnt = 1;
                List<MoneySavings> moneySavings = (List<MoneySavings>) session.getAttribute("moneySavings");
                if (moneySavings != null) {
                    for (MoneySavings moneySaving : moneySavings) {
            %>
            <tr>
                <td><%= cnt++ %>
                </td>
                <td><%= moneySaving.getName() %>
                </td>
                <td><%= moneySaving.getInterest() %>
                </td>
                <td><%= moneySaving.getTotalAmount() %>
                </td>
                <td><%= moneySaving.getTotalMonth() %>
                </td>
                <td><%= moneySaving.getCreatedDate() %>
                </td>
                <td><%= (Double.parseDouble(moneySaving.getTotalAmount()) * Double.parseDouble(moneySaving.getInterest())) / 100 %>
                </td>
            </tr>
            <%
                    }
                }
            %>

        </table>
    </div>

    <div>
        <%
            double totalInterest = 0;
            if (moneySavings != null) {
                for (MoneySavings moneySaving : moneySavings) {
                    totalInterest += (Double.parseDouble(moneySaving.getTotalAmount()) * Double.parseDouble(moneySaving.getInterest())) / 100;
                }
            }
        %>
        <h2 style="text-align: center; margin: 20px 0px">Total Interest: <%= totalInterest %></h2>
    </div>

    <div style="text-align: center; margin: 20px 0px">
        <form action="delete_all_money_saving.jsp" method="post">
            <button type="submit">Withdraw money</button>
        </form>
    </div>
</div>
<%@include file="sections/foot.jsp" %>
</body>
<%@include file="sections/footer.jsp" %>
</html>
