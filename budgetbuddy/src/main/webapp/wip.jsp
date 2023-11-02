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
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        * {
            box-sizing: border-box;
            padding: 0px;
            margin: 0px;
        }

        body {
            display: flex;
            ustify-content: center;
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

            overflow: hidden;
            /*border: 2px #b461cb solid;*/
            /*margin: 20px 40px;*/
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

        h1 span {
            display: block;
        }

        @keyframes hhe {
            0% {
                background-position: 0 50%;
            }
            50% {
                background-position: 100% 50%;
            }
            100% {
                background-position: 0 50%;
            }
        }


        .container-wip {
            position: relative;
            padding: 15px;
            border: 2px solid #c978e0;
            box-shadow: 5px 5px 10px #d29fde;
            justify-content: center;
            align-items: center;
            overflow: hidden;
            border-radius: 20px;
            margin: 20px auto;
            width: 50%;

        }

        .box {
            margin: 0px 350px;
            position: absolute;
            width: 50%;
            height: 430px;
            display: flex;
            justify-content: center;
            align-items: center;
            overflow: hidden;
            border-radius: 20px;
        }

        .box::before {
            content: "";
            position: absolute;
            width: 70%;
            height: 430px;
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

        .box .container-wip {
            position: absolute;
        }

        form {
            padding: 5px;
            width: 100%;
            margin: auto;
        }

        .form-group {
            margin-bottom: 15px;
            padding: 0px;
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

        button:hover {
            background-color: #0056b3;
        }

        #interest-rate {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        /*table {*/
        /*    font-family: arial, sans-serif;*/
        /*    border-collapse: collapse;*/
        /*    width: 100%;*/
        /*}*/


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
            cursor: pointer;
        }

        tr:nth-child(odd) {
            background-color: rgb(224, 198, 231);
        }

        h2 {
            text-align: center;
            font-family: 'Lora', serif;
            font-weight: bold;
        }

        .container-father {
            margin: auto;
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

        .btn-submit {
            width: 60%;
            margin: auto;
            display: block;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 50px;
            cursor: pointer;
            background: linear-gradient(to left, #ff59f6, #d29fde);
            transition: background 1s ease-in-out;
            font-size: 20px;
            font-family: 'Lora', serif;
            font-style: initial;
            font-weight: bold;
            animation: color-change 2s infinite alternate;
        }

    </style>
    <title>BudgetBuddy</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body class="d-flex flex-column h-100">
<div class="page">
    <div class="page-header">
        <h1 class=" w3-container w3-center w3-animate-top"
            style="font-weight: bold; margin-top: 0; padding-bottom: 5px"><span>Money Savings Information</span></h1>
    </div>
    <div class="container-father">
        <div class="box">

        </div>

        <div class="container-wip">
            <form action="save_money_saving_check.jsp" id="money-savings-form ">
                <div class="form-group">
                    <label for="name">Goal:</label>
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
                <button class="btn-submit" type="submit">Submit</button>
            </form>
        </div>

        <div class="table_box" style="margin: 20px auto; width: 100%;">
            <table style="width: 90%; margin: auto; border: 3px solid #b461cb; box-shadow: 3px 5px 7px #838383;">
                <tr>
                    <th>No</th>
                    <th>Goal</th>
                    <th>Interest</th>
                    <th>Total Amount</th>
                    <th>Total Month</th>
                    <th>Created date</th>
                    <th>Interest rate</th>
                    <th>Total</th>
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
                    <td><%= ((Double.parseDouble(moneySaving.getTotalAmount()) * Double.parseDouble(moneySaving.getInterest())) / 100) + Double.parseDouble(moneySaving.getTotalAmount()) %>
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
            <h2 style="text-align: center; margin: 20px 0">Total Interest: <%= totalInterest %>
            </h2>
        </div>

        <div style="text-align: center; margin: 20px 0;">
            <form action="delete_all_money_saving.jsp" method="post">
                <button type="submit" class="btn-submit">Withdraw money</button>
            </form>
        </div>
    </div>
</div>

<%@include file="sections/foot.jsp" %>
</body>
<%@include file="sections/footer.jsp" %>
</html>
