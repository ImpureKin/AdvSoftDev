package controller;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import database.DB;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/LoadGoalsAndSavingsServlet")
public class LoadGoalsAndSavingsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = DB.getConnection();
            int userId = 2;

            List<Goals> userGoals = DB.getGoalsByUserId(connection, userId);
            Finances userFinances = DB.getFinancesByUserId(connection, userId);

            // Extract total_savings from Finances
            
            int totalSavings = 0; // Set a default value

            if (userFinances != null) {
                totalSavings = userFinances.getTotalSavings();
            }



            connection.close();

            request.setAttribute("userGoals", userGoals);
            request.setAttribute("userFinances", userFinances);
            request.setAttribute("totalSavings", totalSavings); // Add totalSavings to request attributes

            RequestDispatcher dispatcher = request.getRequestDispatcher("saving_goals.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions or redirect to an error page
        }
    }
}
