package controller;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import database.*;

@WebServlet("/GetGoalForEditServlet")
public class GetGoalForEditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int goalId = Integer.parseInt(request.getParameter("goalId"));

            // Retrieve the goal details with percentage
            Goals goal = GoalsManager.getGoalById(goalId);

            request.setAttribute("goal", goal);

            // Forward to edit_goal.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("edit_goal.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions or redirect to an error page
        }
    }
}
