package controller;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;
import database.*;

//This Servlet retrives the users savings and goals 
@WebServlet("/GoalsAndSavings")
public class LoadGoalsAndSavingsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // Retreives session attributes
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("User");

            //checks to make sure that the user is not null
            if (user != null) {
            
                // Converts the user id value 
                String userIdString = user.getId();
                int userId = Integer.parseInt(userIdString);

                // Print user information for debugging purposes
                System.out.println("User ID: " + userId);

                // Get the connection 
                Connection connection = ConnectionManager.getConnection();

                // Get a list of the goals which the users have 
                List<Goals> userGoals = GoalsManager.getGoalsByUserId(connection, userId);

                // Get total savings and total saved from TotalUserSavings view
                TotalUserSavings userFinances = FinancesManager.getTotalUserSavings(connection, userId);

                int totalSavings = 0; // Sets a default value incase it is a negative
                int totalSaved = 0;   // Sets a default value incase it is a negative 

                // if the user finances is not 0 then retrieve the savogns and toal saved 
                if (userFinances != null) {
                    totalSavings = userFinances.getTotalSavings();
                    totalSaved = userFinances.getTotalSaved();
                }

                // closes connection 
                ConnectionManager.closeConnection(connection);

                //sends retrieved data to the saving goals.jsp
                request.setAttribute("userGoals", userGoals);
                request.setAttribute("userFinances", userFinances);
                request.setAttribute("totalSavings", totalSavings);
                request.setAttribute("totalSaved", totalSaved); 

                RequestDispatcher dispatcher = request.getRequestDispatcher("saving_goals.jsp");
                dispatcher.forward(request, response);

            } else {
                // Handle if there is no user attribute found 
                response.sendRedirect("index.jsp");
            }

        } catch (Exception e) {
            // Handle any excpetions and prints the trace
            e.printStackTrace();
        }
    }
}

