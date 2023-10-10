<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Finances" %>
<%@include file="sections/navbar.jsp" %>
<%@include file="sections/head.jsp" %>
<!DOCTYPE html>
<html> 
<head>
<title>Financial Trends Page </title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>

    <!-- Trends page -->
    <div class="container mt-5">
    <h1>Financial Trends</h1>
    <p class="text-center">Below is your Financial data where you will see your spending and saving trends. You will be able to visualize it through graphs.</p>
        <%
            Finances finances = (Finances) request.getAttribute("finances");

            // Check if finances is not null and has non-zero values
            if (finances != null && finances.getTotalIncome() != 0 && finances.getTotalDeductions() != 0 && finances.getTotalExpenses() != 0 && finances.getTotalSavings() != 0) {
        %>
        <!-- Table to display summary of user's finances -->
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
            <%
                } else {
            %>
                <!-- Display a message if finances is zero or null -->
                <p><strong>No financial data available. Please add your data</strong></p>
            <%
                }
            %>


        <!-- Graph Area-->
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

    <!-- Script to make the Graph -->
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

<%@include file="sections/foot.jsp" %>
<%@include file="sections/footer.jsp" %>

</body>
</html>