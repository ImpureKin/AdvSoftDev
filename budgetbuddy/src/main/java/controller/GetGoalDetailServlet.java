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
import database.*;

@WebServlet("/GetGoalDetailServlet")
public class GetGoalDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int goalId = Integer.parseInt(request.getParameter("goalId"));

            // Retrieve the goal by ID
            Goals goal = GoalsManager.getGoalById(goalId);

            int savedAmount = goal.getSavedAmount();
            int goalAmount = goal.getGoalAmount();
            int percentage = (int)((savedAmount * 100.0) / goalAmount);

            request.setAttribute("goal", goal);
            request.setAttribute("percentage", percentage); // Add percentage to request attributes

            RequestDispatcher dispatcher = request.getRequestDispatcher("detail_goal.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions or redirect to an error page
        }
    }
}

