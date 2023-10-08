package controller;
import java.io.IOException;
import java.sql.Connection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Finances;
import model.User;
import database.*;

@WebServlet("/GetFinancialDataServlet")
public class GetFinancialDataServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            String userIdString = String.valueOf(user.getId());
            int userId = Integer.parseInt(userIdString); // Convert String to int
            
            Finances finances = FinancesManager.getFinancesByUserId(connection, userId);
            if (finances != null) {
                request.setAttribute("finances", finances);
                RequestDispatcher dispatcher = request.getRequestDispatcher("trends.jsp"); // Forward to your trends.jsp
                dispatcher.forward(request, response);
            } else {
                // Handle the case where finances are not found
                response.getWriter().println("Finances not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



