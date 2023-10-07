package controller;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import database.*;

@WebServlet("/UpdateGoalServlet")
public class UpdateGoalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int goalId = Integer.parseInt(request.getParameter("goalId"));
            String goalName = request.getParameter("goalName");
            String goalDescription = request.getParameter("goalDescription");
            int goalAmount = Integer.parseInt(request.getParameter("goalAmount"));

            System.out.println("goalId: " + goalId);
            System.out.println("goalName: " + goalName);
            System.out.println("goalDescription: " + goalDescription);
            System.out.println("goalAmountString: " + goalAmount);
    
        
                // Assuming you have a function to update the goal
                GoalsManager.updateGoal(goalId, goalName, goalDescription, goalAmount);
    
                // Redirect back to the goal detail page
                response.sendRedirect("GetGoalDetailServlet?goalId=" + goalId);
                System.out.println("Redirected to detail_goal.jsp");
           
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions or redirect to an error page
        }
    }
}   

