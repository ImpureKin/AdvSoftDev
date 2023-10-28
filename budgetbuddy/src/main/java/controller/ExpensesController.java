package controller;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import database.ConnectionManager;
import database.ExpenseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Expenses;
import model.User;

@WebServlet("/Expenses")
public class ExpensesController extends HttpServlet {

    Connection connection;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        String userId = user.getId();

        if (userId == null) {
            resp.sendRedirect("index.jsp");
        }
        
        
        try {
            connection = ConnectionManager.getConnection();

            //'Delete' feature
            String deleteId = req.getParameter("deleteId");
            if (deleteId != null) {
              ExpenseManager.deleteExpense(connection, Integer.parseInt(deleteId));
            }
            ExpenseManager.initializeDatabase(connection);
            List<Expenses> expensesList = ExpenseManager.getAllExpenses(connection);

            // Close connection
            ConnectionManager.closeConnection(connection);

            req.setAttribute("expensesList", expensesList);
            req.getRequestDispatcher("expenses.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

            
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            connection = ConnectionManager.getConnection();

            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("User");
            String userId = user.getId();
            int userId2 = Integer.valueOf(userId);
            if (userId == null) {
                resp.sendRedirect("index.jsp");
            }
            
            String expenseName = req.getParameter("expenseName");
            double amount = Double.parseDouble(req.getParameter("amount"));
            String category = req.getParameter("category");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = sdf.parse(req.getParameter("date"));

            Expenses newExpense = new Expenses();
            newExpense.setUserId(userId2);
            newExpense.setExpenseName(expenseName);
            newExpense.setAmount(amount);
            newExpense.setCategory(category);
            newExpense.setDate(date);

            ExpenseManager.addExpense(connection, newExpense, userId2);
            
            // Close connection
            ConnectionManager.closeConnection(connection);

            resp.sendRedirect("Expenses");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}