<html>
<head>
    <title>Budget Buddy - Expenses</title>
</head>
<body>
    <h2>Expenses Feature</h2>

    <!-- Form to Add New Expenses -->
    <h3>Add New Expense</h3>
    <form id="addExpenseForm">
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
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="expenseTableBody">
            <!-- Dynamic rows go here -->
        </tbody>
    </table>

    <!-- Optionally, you can add JavaScript to make the form and table dynamic -->
</body>
</html>