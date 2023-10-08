package controller;

import model.Deductions;
import database.DeductionManager;
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

@WebServlet("/Deductions")
public class DeductionsController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = ConnectionManager.getConnection()) {
            DeductionManager.initializeDatabase(connection);
            List<Deductions> deductionsList = DeductionManager.getAllDeductions(connection);
            req.setAttribute("deductionsList", deductionsList);
            req.getRequestDispatcher("deductions.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = ConnectionManager.getConnection()) {
            String name = req.getParameter("name");
            double amount = Double.parseDouble(req.getParameter("amount"));
            String type = req.getParameter("type");
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("date"));

            Deductions newDeduction = new Deductions();
            newDeduction.setname(name);
            newDeduction.setAmount(amount);
            newDeduction.setCategory(type);
            newDeduction.setDate(date);

            DeductionManager.addDeduction(connection, newDeduction);

            resp.sendRedirect("Deductions");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Additional methods for DELETE, UPDATE operations can be added here if needed.
}