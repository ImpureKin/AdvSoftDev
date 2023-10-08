package controller;

import model.Expenses;
import database.ExpenseManager;
import database.ConnectionManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/Expenses")
public class ExpensesController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = ConnectionManager.getConnection()) {
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
            String expenseName = req.getParameter("expenseName");
            double amount = Double.parseDouble(req.getParameter("amount"));
            String category = req.getParameter("category");
            Date date = new SimpleDateFormat("DD-MM-YYYY").parse(req.getParameter("date"));

            Expenses newExpense = new Expenses();
            newExpense.setExpenseName(expenseName);
            newExpense.setAmount(amount);
            newExpense.setCategory(category);
            newExpense.setDate(date);

            ExpenseManager.addExpense(connection, newExpense);

            resp.sendRedirect("Expenses");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}