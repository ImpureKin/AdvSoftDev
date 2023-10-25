<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<%@include file="sections/navbar.jsp" %>
<%@include file="sections/head.jsp" %>
<script>
    $( function() {
    $( "#date, #invoice_date" ).datepicker({
        dateFormat: "dd-mm-yy"
    });
    } );

    </script>

<!DOCTYPE html>
<html>
<!-- <taglib prefix="c" uri="https://jakarta.apache.org/taglibs/standard_2_0-rt/core"></taglib> -->

<head>
    <title>Budget Buddy - Deductions</title>
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
<body class="d-flex flex-column h-100">
        <h2>Deductions Feature</h2>

        <h3>Add New Deduction</h3>
        <form id="addDeductionForm" method="post" action="/app/Deductions">
    
            <label for="name">Deduction Name:</label>
            <input type="text" id="name" name="name" required>
        
            <label for="amount">Amount:</label>
            <input type="number" id="amount" name="amount" required>
        
                <label for="category">Category:</label>
                <select id="category" name="category">
                    <option value="Tax">Tax</option>
                    <option value="Insurance">Insurance</option>
                    <option value="Loan">Loan</option>
                    <option value="Work Related Purchase">Work Related Purchase</option>
                    <option value="Other">Other</option>
                </select>
        
            <label for="date">Date:</label>
            <input type="text" id="date" name="date" required>
        
   
            <label for="frequency">Frequency:</label>
            <input type="text" id="frequency" name="frequency" required>

            <label for="invoice_date">Invoice Date:</label>
            <input type="text" id="invoice_date" name="invoice_date" class="datepicker">
        
            <input type="submit" value="Add Deduction">
        </form>
        
        <!-- Table to Display Deductions -->
        <h3>Current Deductions</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>Deduction Name</th>
                    <th>Amount</th>
                    <th>Category</th>
                    <th>Date</th>
                    <th>Frequency</th> 
                    <th>Invoice Date</th> 
                    <th>Action</th>
                </tr>
            </thead>
            <tbody id="deductionTableBody">
                <c:forEach items="${deductionsList}" var="deduction">
                    <tr>
                        <td>${deduction.name}</td>
                        <td>${deduction.amount}</td>
                        <td>${deduction.category}</td>
                        <td>${deduction.formattedDate}</td>
                        <td>${deduction.frequency}</td> 
                        <td>${deduction.invoiceDate}</td> 
                        <td><a href="/app/Deductions?action=delete&id=${deduction.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        

    
        <!-- Optionally, you can add JavaScript to make the form and table dynamic -->
    <%@include file="sections/foot.jsp" %>
    <%@include file="sections/footer.jsp" %>
    </body>
    
    </html>