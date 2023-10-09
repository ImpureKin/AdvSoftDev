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

//This is Servlet retrives the goal for the purpose of editing 
@WebServlet("/GetGoalForEditServlet")
public class GetGoalForEditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
            // Retrieves the goal id from the webpage 
            int goalId = Integer.parseInt(request.getParameter("goalId"));

            // Retrieve the goal details 
            Goals goal = GoalsManager.getGoalById(goalId);

            // Forward goal's attributes to edit_goal.jsp
            request.setAttribute("goal", goal);
            RequestDispatcher dispatcher = request.getRequestDispatcher("edit_goal.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
             // Handle any excpetions and prints the trace
            e.printStackTrace();
        }
    }
}
