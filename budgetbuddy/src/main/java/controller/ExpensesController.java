package controller;

import model.Expenses;
import database.ExpenseManager;
import database.ConnectionManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/Expenses")
public class ExpensesController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            userId = 1; // For testing purposes
            //resp.sendRedirect("index.jsp"); // Redirect to login page if user is not authenticated
            //return;
        }
        
        
        try (Connection connection = ConnectionManager.getConnection()) {
            //'Delete' feature
            String deleteId = req.getParameter("deleteId");
            if (deleteId != null) {
              ExpenseManager.deleteExpense(connection, Integer.parseInt(deleteId));
            }
            ExpenseManager.initializeDatabase(connection);
            List<Expenses> expensesList = ExpenseManager.getAllExpenses(connection);
            req.setAttribute("expensesList", expensesList);
            req.getRequestDispatcher("expenses.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

            
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = ConnectionManager.getConnection()) {
            
            HttpSession session = req.getSession();
            Integer userId = (Integer) session.getAttribute("userId");

            // TODO: Implement proper user session handling
            if(userId == null) {
                userId = 1; // For testing purposes
                //resp.sendRedirect("index.jsp");
                //return;
            }
            
            String expenseName = req.getParameter("expenseName");
            double amount = Double.parseDouble(req.getParameter("amount"));
            String category = req.getParameter("category");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = sdf.parse(req.getParameter("date"));

            Expenses newExpense = new Expenses();
            newExpense.setUserId(userId);
            newExpense.setExpenseName(expenseName);
            newExpense.setAmount(amount);
            newExpense.setCategory(category);
            newExpense.setDate(date);

            ExpenseManager.addExpense(connection, newExpense, userId);

            resp.sendRedirect("Expenses");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}