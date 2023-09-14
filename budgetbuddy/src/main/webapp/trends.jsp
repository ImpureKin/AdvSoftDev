<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<title>Trends Page </title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.5.1/chart.min.js"></script>
</head>
<body>
    <a href="home.jsp">Home</a>
    <a href="saving_goals.jsp">Trends</a>
    <h1>Trends Overview</h1>

    <!-- Summary table of all of the data which the user enters -->
    <h2>Summary Table</h2>
    <table border="1">
        <tr> 
            <th>Date</th> 
            <th>Income</th>
            <th>Deductions</th>
            <th>Expenses</th>
            <th>Savings</th>
        </tr>
        <tr>
            <td>2023-09-01</td>
            <td>$2000</td>
            <td>$300</td>
            <td>$1000</td>
            <td>$700</td>
        </tr>
        <tr>
            <td>2023-09-02</td>
            <td>$2500</td>
            <td>$200</td>
            <td>$1200</td>
            <td>$1100</td>
        </tr>
    </table>

    <!-- Test run of this graph set. This has dummy data entered into it  -->
    <h2>Graphical View Prototype </h2>
    <canvas id="trendsChart" width="800" height="400"></canvas>
    <script>
        var ctx = document.getElementById('trendsChart').getContext('2d');
        var trendsChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['2023-09-01', '2023-09-02'], 
                datasets: [
                    {
                        label: 'Income',
                        data: [2000, 2500], 
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 1
                    },
                    {
                        label: 'Deductions',
                        data: [300, 200], 
                        backgroundColor: 'rgba(255, 99, 132, 0.2)',
                        borderColor: 'rgba(255, 99, 132, 1)',
                        borderWidth: 1
                    },
                    {
                        label: 'Expenses',
                        data: [1000, 1200], 
                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                        borderColor: 'rgba(54, 162, 235, 1)',
                        borderWidth: 1
                    },
                    {
                        label: 'Savings',
                        data: [700, 1100], 
                        backgroundColor: 'rgba(255, 206, 86, 0.2)',
                        borderColor: 'rgba(255, 206, 86, 1)',
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
    </script>
    
</body>

</html>