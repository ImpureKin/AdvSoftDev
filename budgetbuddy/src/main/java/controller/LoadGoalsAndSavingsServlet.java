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
import jakarta.servlet.http.HttpSession;
import model.*;
import database.*;

@WebServlet("/LoadGoalsAndSavingsServlet")
public class LoadGoalsAndSavingsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("User");

            if (user != null) {
                String userIdString = String.valueOf(user.getId()); // Convert int to String
                int userId = Integer.parseInt(userIdString); // Convert String to int

                // Print user information
                System.out.println("User ID: " + userId);

                Connection connection = ConnectionManager.getConnection();

                List<Goals> userGoals = GoalsManager.getGoalsByUserId(connection, userId);

                // Get total savings and total saved from TotalUserSavings view
                TotalUserSavings userFinances = FinancesManager.getTotalUserSavings(connection, userId);

                int totalSavings = 0; // Set a default value
                int totalSaved = 0;   // Set a default value

                if (userFinances != null) {
                    totalSavings = userFinances.getTotalSavings();
                    totalSaved = userFinances.getTotalSaved();
                }

                connection.close();

                request.setAttribute("userGoals", userGoals);
                request.setAttribute("userFinances", userFinances);
                request.setAttribute("totalSavings", totalSavings);
                request.setAttribute("totalSaved", totalSaved); // Add totalSaved to request attributes

                RequestDispatcher dispatcher = request.getRequestDispatcher("saving_goals.jsp");
                dispatcher.forward(request, response);
            } else {
                // Handle the case where the user object is not found in the session
                response.sendRedirect("welcome_page.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions or redirect to an error page
        }
    }
}

