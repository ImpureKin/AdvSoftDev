package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import database.GoalsManager;

@WebServlet("/DeleteGoal")
public class DeleteGoalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("DeleteGoalServlet called");
            int goalId = Integer.parseInt(request.getParameter("goalId"));
            System.out.println("Deleting goal with ID: " + goalId);
            // Call a method to delete the goal by ID from the database
            boolean deleted = GoalsManager.deleteGoalById(goalId);

            if (deleted) {
                // Goal was successfully deleted
                response.getWriter().write("Goal deleted successfully");
            } else {
                // Goal was not found or an error occurred during deletion
                response.getWriter().write("Error deleting goal");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error deleting goal");
        }
    }
}
