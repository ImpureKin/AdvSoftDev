<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="sections/navbar.jsp" %>
<%@include file="sections/head.jsp" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
    $( function() {
    $( "#date" ).datepicker({
        dateFormat: "dd-mm-yy"
    });
    } );

    </script>
    
<!DOCTYPE html>
<html>
<!-- <taglib prefix="c" uri="https://jakarta.apache.org/taglibs/standard_2_0-rt/core"></taglib> -->

<head>
    <title>Budget Buddy - Expenses</title>
</head>
<body class="d-flex flex-column h-100">
    <h2>Expenses Feature</h2>

    <!-- Form to Add New Expenses -->
    <h3>Add New Expense</h3>
    <form id="addExpenseForm" method="post" action="/app/Expenses">

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
            <option value="Other">Other</option>
        </select>

        <label for="date">Date:</label>
        <input type="text" id="date" name="date" required>

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
                <th>Action</th>
            </tr>
        </thead>
        <tbody id="expenseTableBody">
            <!-- Dynamic rows go here -->

            <c:forEach items="${expensesList}" var="expense">
                <tr>
                    <td>${expense.expenseName}</td>
                    <td>${expense.amount}</td>
                    <td>${expense.category}</td>
                    <td>${expense.formattedDate}</td>
                    <td>
                        <a href="/app/Expenses?deleteId=${expense.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>

    </table>





<%@include file="sections/foot.jsp" %>
<%@include file="sections/footer.jsp" %>
</body>
</html>