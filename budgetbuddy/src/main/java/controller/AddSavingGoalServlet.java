package controller;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Goals;
import database.*;

@WebServlet("/AddingSavingGoalServlet")
public class AddSavingGoalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve form data
            String goalName = request.getParameter("goalName");
            String goalDescription = request.getParameter("goalDescription");
            String category = request.getParameter("category");
            int goalAmount = Integer.parseInt(request.getParameter("goalAmount"));
            int savedAmount = 0; // Assuming saved amount starts at 0
    
            // Format the current date as "dd/MM/yyyy"
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateCreatedStr = dateFormat.format(new Date());
            Date dateCreated = dateFormat.parse(dateCreatedStr);
    
            // Create a new Goals object
            Goals newGoal = new Goals(0, 1, goalName, goalAmount, savedAmount, category, goalDescription, dateCreated);
    
            // Insert the goal into the database
            GoalsManager.createGoal(newGoal);
    
            // Redirect back to the original page
            response.sendRedirect("LoadGoalsAndSavingsServlet");
    
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions or redirect to an error page
        }
    }
}    

