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

//This servlet retrives the specific goal details 
@WebServlet("/GoalDetails")
public class GetGoalDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            // Retrives the goalid from the webpage button press
            int goalId = Integer.parseInt(request.getParameter("goalId"));

            // Retrieve the goal by goalid
            Goals goal = GoalsManager.getGoalById(goalId);

            // Retrive these amounts to create the percentage of the goal completion 
            int savedAmount = goal.getSavedAmount();
            int goalAmount = goal.getGoalAmount();
            int percentage = (int)((savedAmount * 100.0) / goalAmount);

            // Sends the goal details as well as the calculated percentage to the goal details page
            request.setAttribute("goal", goal);
            request.setAttribute("percentage", percentage); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("detail_goal.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // Handle any excpetions and prints the trace
            e.printStackTrace();
        }
    }
}

