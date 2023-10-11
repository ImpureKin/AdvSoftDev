package controller;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Goals;
import database.*;
import jakarta.servlet.annotation.WebServlet;


//This Servlet adds money to an existing goal
@WebServlet("/AddMoneytoGoalServlet")
public class AddMoneytoGoalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Retrive the goal id and amount from the webpage.
        String goalIdParameter = request.getParameter("goalId");
        String amountParameter = request.getParameter("amount");

        //checks to make sure is not null 
        if (goalIdParameter != null && amountParameter != null) {

            //sets both values to integer to match the database 
            int goalId = Integer.parseInt(goalIdParameter);
            int amountToAdd = Integer.parseInt(amountParameter);

            // Retrieve the goal and its associated saved amount
            Goals goal = GoalsManager.getGoalById(goalId);
            int savedAmount = goal.getSavedAmount();
            int goalAmount = goal.getGoalAmount();

            //messages that will be displayed depending if the goal can be addedw or not 
            String message;
            if (savedAmount + amountToAdd > goalAmount) {

                // The total saved amount would exceed the goal amount it will display a message 
                message = "Error: Total saved amount exceeds the goal amount.";

            } else {

                // Add the money to the goal
                GoalsManager.updateSavedAmount(goalId, amountToAdd);
                message = "Money successfully added to goal!";
            }

            //setting up for the notification and the reload of page 
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(message);

        } else {
            //Shows error that the parameters being passed throght are wrong 
            response.getWriter().println("Error: Invalid parameters.");
        }
    }
}

