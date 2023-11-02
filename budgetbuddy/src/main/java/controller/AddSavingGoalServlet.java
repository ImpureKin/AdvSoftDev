package controller;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;
import database.*;

// This servlet is used to create a new saving goal 
@WebServlet("/AddingSavingGoalServlet")
public class AddSavingGoalServlet extends HttpServlet {

    Connection connection;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // Retrives session attributes
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("User");
            
            // Checks to make sure is not null 
            if (user != null) {

                try {
                    connection = ConnectionManager.getConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Converts the user id value 
                String userIdString = user.getId();
                int userId = Integer.parseInt(userIdString); 

                // Retrieve form data from the webpage 
                String goalName = request.getParameter("goalName");
                String goalDescription = request.getParameter("goalDescription");
                String category = request.getParameter("category");
                int goalAmount = Integer.parseInt(request.getParameter("goalAmount"));
                int savedAmount = 0; // ensures that the saved amount will start at 0
        
                // Format the current date value as "dd/MM/yyyy" to match the database 
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateCreatedStr = dateFormat.format(new Date());
                Date dateCreated = dateFormat.parse(dateCreatedStr);
        
                // Create a new Goals object
                Goals newGoal = new Goals(0, userId, goalName, goalAmount, savedAmount, category, goalDescription, dateCreated);
        
                // Insert the new Goal into the database 
                GoalsManager.createGoal(connection, newGoal);

                // Close connection
                ConnectionManager.closeConnection(connection);
        
                // Redirect back to the original page
                response.sendRedirect("GoalsAndSavings");

            } else {
                // Handle if there is no user logged-in 
                response.sendRedirect("index.jsp"); // Redirect to a index page,
            }
    
        } catch (Exception e) {
            // Handle any excpetions and prints the trace
            e.printStackTrace();
        }
    }
}    
