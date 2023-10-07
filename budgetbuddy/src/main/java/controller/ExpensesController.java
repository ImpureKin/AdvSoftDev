package controller;

import model.Expenses;
import database.expenses;
import database.ConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/ExpensesController")
public class ExpensesController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = ConnectionManager.getConnection()) {
            req.setAttribute("expensesList", expenses.getAllExpenses(connection));
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
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date"));

            Expenses newExpense = new Expenses();
            newExpense.setExpenseName(expenseName);
            newExpense.setAmount(amount);
            newExpense.setCategory(category);
            newExpense.setDate(date);

            expenses.addExpense(connection, newExpense);

            resp.sendRedirect("expenses.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}