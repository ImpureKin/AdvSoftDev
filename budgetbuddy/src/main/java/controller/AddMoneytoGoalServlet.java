package controller;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Goals;
import database.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/AddMoneytoGoalServlet")
public class AddMoneytoGoalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int goalId = Integer.parseInt(request.getParameter("goalId"));
            int savedAmount = Integer.parseInt(request.getParameter("amount"));
            
            // Retrieve the goal by ID
            Goals goal = GoalsManager.getGoalById(goalId);
    
            if (goal != null) {
                // Update the goal's savedAmount
                goal.setSavedAmount(goal.getSavedAmount() + savedAmount);
    
                // Save the updated goal
                GoalsManager.updateSavedAmount(goalId, savedAmount);
                
                System.out.println("Goal Updated: " + goal.getName()); // Print confirmation
                System.out.println("Saved Amount Updated: " + savedAmount);

            } 
    
            // Redirect back to the original page
            request.setAttribute("goalAdded", true);
            response.sendRedirect("LoadGoalsAndSavingsServlet");

        // Exception handling
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
