<html>
<taglib prefix="c" uri="https://jakarta.apache.org/taglibs/standard_2_0-rt/core"></taglib>

<head>
    <title>Budget Buddy - Expenses</title>
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

    <h2>Expenses Feature</h2>

    <!-- Form to Add New Expenses -->
    <h3>Add New Expense</h3>
    <form id="addExpenseForm" method="post" action="/app/ExpensesController">
        <label for="expenseName">Expense Name:</label>
        <input type="text" id="expenseName" name="expenseName" required>
        
        <label for="amount">Amount:</label>
        <input type="number" id="amount" name="amount" required>
        
        <label for="category">Category:</label>
        <select id="category" name="category">
            <option value="food">Food</option>
            <option value="transport">Transport</option>
            <option value="utilities">Utilities</option>
            <option value="entertainment">Entertainment</option>
        </select>

        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required>

        <input type="submit" value="Add Expense">
    </form>

    <!-- Table to Display Expenses -->
    <h3>Current Expenses</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Expense Name</th>
                <th>Amount</th>
                <th>Category</th>
                <th>Date</th>
            </tr>
        </thead>
        <tbody id="expenseTableBody">
            <!-- Dynamic rows go here -->

            <c:forEach items="${expensesList}" var="expense">
                <tr>
                    <td>${expense.expenseName}</td>
                    <td>${expense.amount}</td>
                    <td>${expense.category}</td>
                    <td>${expense.date}</td>
                </tr>
            </c:forEach>
        </tbody>

    </table>




    <!-- Optionally, you can add JavaScript to make the form and table dynamic -->
</body>
</html>