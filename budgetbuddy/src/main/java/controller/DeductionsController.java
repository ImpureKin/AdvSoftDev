package controller;

import model.Deductions;
import database.DeductionManager;
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

@WebServlet("/Deductions")
public class DeductionsController extends HttpServlet {
    
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
            //Delete feature
            String action = req.getParameter("action");
            if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                DeductionManager.removeDeduction(connection, id);
            }
            
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

            HttpSession session = req.getSession();
            Integer userId = (Integer) session.getAttribute("userId");

            if(userId == null) {
                userId = 1; // for testing purposes only, change later
                // Handle error. E.g., redirect to login page.
                
                //resp.sendRedirect("index.jsp");
                //return;
            }

            String name = req.getParameter("name");
            double amount = Double.parseDouble(req.getParameter("amount"));
            String category = req.getParameter("category");
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(req.getParameter("date"));
            String frequency = req.getParameter("frequency");  // capture frequency from request
            String invoiceDate = req.getParameter("invoice_date");  // capture invoice_date from request

            Deductions newDeduction = new Deductions();
            newDeduction.setUserId(userId);  // set the userId
            newDeduction.setname(name);
            newDeduction.setAmount(amount);
            newDeduction.setCategory(category);
            newDeduction.setDate(date);
            newDeduction.setFrequency(frequency);  // set the frequency. Make sure to add this method in Deductions model.
            newDeduction.setInvoiceDate(invoiceDate);  // set the invoice date. Make sure to add this method in Deductions model.


            DeductionManager.addDeduction(connection, newDeduction, userId);

            resp.sendRedirect("Deductions");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Additional methods for DELETE, UPDATE operations can be added here if needed.
}