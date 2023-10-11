package controller;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import database.*;

// This Servlet updates the goal teh user is editing 
@WebServlet("/UpdateGoalServlet")
public class UpdateGoalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // Retreives the datat from the webpage  
            int goalId = Integer.parseInt(request.getParameter("goalId"));
            String goalName = request.getParameter("goalName");
            String goalDescription = request.getParameter("goalDescription");
            int goalAmount = Integer.parseInt(request.getParameter("goalAmount"));

            // Prints out the details to make sure data is being retreived 
            System.out.println("goalId: " + goalId);
            System.out.println("goalName: " + goalName);
            System.out.println("goalDescription: " + goalDescription);
            System.out.println("goalAmountString: " + goalAmount);
    
        
                // Send attributes to updateGoal fucntion in the database so the goal will be updated 
                GoalsManager.updateGoal(goalId, goalName, goalDescription, goalAmount);
    
                // Redirects back to the goal detail page
                response.sendRedirect("GetGoalDetailServlet?goalId=" + goalId);
           
        } catch (Exception e) {
            // Handle any excpetions and prints the trace
            e.printStackTrace();
        }
    }
}   

