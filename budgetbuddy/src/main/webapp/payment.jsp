<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
    <style>
        .center-container {
             display: flex;
              justify-content: center;
             align-items: center;
           }
   
           .center {
                   text-align: center;
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
   </style>
</head>
<body>
     <!-- NavBar-->
    <div class="center-container">
        <div class="topnav">
        <a href="home.jsp">Home</a>
            <a href="income.jsp">Income</a>
            <a href="expenses.jsp">Expenses</a>
            <a href="wip.jsp">Deductions</a>
            <a href="saving_goals.jsp">Savings</a>
            <a href="trends.jsp">Trends</a>
            <a href="tips_and_knowledge.jsp">Tips & Knowledge</a>
            <a href="payment.jsp">Bill Reminders</a>
            <a href="wip.jsp">Financial Support</a>
            <a href="index.jsp">Logout</a>
        </div>
    </div>
    <h2>Payment Management</h2>
    <h3>Add Next Payment(Fanction)</h3>

    <div style="display: flex">
        <div style="width: 33%">
            <input type="text" placeholder="Name">
            <p>Join</p>
        </div>
        <div style="width: 33%">
            <input type="text" placeholder="Amount">
            <p>20$</p>
        </div>
        <div style="width: 33%">
            <input type="date" placeholder="Date">
            <p>10/10/2023</p>
        </div>
    </div>
</body>
</html>
