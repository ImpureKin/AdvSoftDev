<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="model.Finances" %>
<!DOCTYPE html>
<html> 
<head>
<title>Financial Trends Page </title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
          <!-- NavBar-->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">BudgetBuddy</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-between" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="home.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="income.jsp">Income</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="expenses.jsp">Expenses</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="wip.jsp">Deductions</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="LoadGoalsAndSavingsServlet">Savings</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="trends.jsp">Trends</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="tips_and_knowledge.jsp">Tips & Knowledge</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="payment.jsp">Bill Reminders</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="wip.jsp">Financial Support</a>
                    </li>
                </ul>
                <br>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Summary table of all of the data which the user enters -->
    <div class="container mt-5">
        <h1>Financial Trends</h1>
        <%
  Finances finances = (Finances) request.getAttribute("finances");
  NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
%>

        <!-- Table -->
        <h2>Summary Table</h2>
        <h2>Summary Table</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Category</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Total Income</td>
                    <td><%= currencyFormatter.format(finances.getTotalIncome()) %></td>
                </tr>
                <tr>
                    <td>Total Deductions</td>
                    <td><%= currencyFormatter.format(finances.getTotalDeductions()) %></td>
                </tr>
                <tr>
                    <td>Total Expenses</td>
                    <td><%= currencyFormatter.format(finances.getTotalExpenses()) %></td>
                </tr>
                <tr>
                    <td>Total Savings</td>
                    <td><%= currencyFormatter.format(finances.getTotalSavings()) %></td>
                </tr>
            </tbody>
        </table> 
        
        <!-- Graph -->
        <h2>Graphical View</h2>
        <div class="form-group">
        <label for="chartType">Select Chart Type:</label>
        <select class="form-control" id="chartType" onchange="updateChartType()">
            <option value="bar">Bar Chart</option>
            <option value="line">Line Chart</option>
            <option value="pie">Pie Chart</option>
        </select>
    </div>
        <canvas id="financesChart" width="800" height="400"></canvas>
    </div>

<script>
    var chartType = 'bar'; // Default chart type
    var financesChart = null; // Reference to the chart

    function updateChartType() {
        chartType = document.getElementById('chartType').value;
        generateChart();
    }

    function generateChart() {
        if (financesChart) {
            financesChart.destroy(); // Destroy the existing chart
        }

        var ctx = document.getElementById('financesChart').getContext('2d');
        financesChart = new Chart(ctx, {
            type: chartType,
            data: {
                labels: ['Total Income', 'Total Deductions', 'Total Expenses', 'Total Savings'],
                datasets: [
                    {
                        label: 'Amount',
                        data: [
                            ${finances.totalIncome},
                            ${finances.totalDeductions},
                            ${finances.totalExpenses},
                            ${finances.totalSavings}
                        ],
                        backgroundColor: [
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)'
                        ],
                        borderColor: [
                            'rgba(75, 192, 192, 1)',
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)'
                        ],
                        borderWidth: 1
                    }
                ]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }

    // Generate the initial chart
    generateChart();
</script>

    
</body>

</html>