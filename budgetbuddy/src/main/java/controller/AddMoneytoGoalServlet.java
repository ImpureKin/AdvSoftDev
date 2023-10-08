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
        String goalIdParameter = request.getParameter("goalId");
        String amountParameter = request.getParameter("amount");

        System.out.println("goalIdParameter: " + goalIdParameter);
        System.out.println("amountParameter: " + amountParameter);


        if (goalIdParameter != null && amountParameter != null) {
            int goalId = Integer.parseInt(goalIdParameter);
            int amountToAdd = Integer.parseInt(amountParameter);

            // Retrieve the goal and its saved amount
            Goals goal = GoalsManager.getGoalById(goalId);
            int savedAmount = goal.getSavedAmount();
            int goalAmount = goal.getGoalAmount();

            System.out.println("Goal ID: " + goal.getId());
            System.out.println("Saved Amount: " + savedAmount);
            System.out.println("Goal Amount: " + goalAmount);

            String message;
            if (savedAmount + amountToAdd > goalAmount) {
                // The total saved amount would exceed the goal amount
                message = "Error: Total saved amount exceeds the goal amount.";
            } else {
                // Add the money to the goal
                GoalsManager.updateSavedAmount(goalId, amountToAdd);
                message = "Money successfully added to goal!";
            }

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(message);
        } else {
            response.getWriter().println("Error: Invalid parameters.");
        }
    }
}

