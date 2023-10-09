package controller;
import java.io.IOException;
import java.sql.Connection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Finances;
import model.User;
import database.*;

//This servlet loads all the users financial data to be visualised
@WebServlet("/GetFinancialDataServlet")
public class GetFinancialDataServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
        // Retrives session attributes
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        
        //Sets up connection 
        Connection connection = null;

        try {
            // retrives the connection to the database
            connection = ConnectionManager.getConnection();
            
            // Converts the user id value 
             String userIdString = user.getId();
             int userId = Integer.parseInt(userIdString); 
            
            // Fetches the users finances
            Finances finances = FinancesManager.getFinancesByUserId(connection, userId);

            // closes connection 
            connection.close();

            // if finnance is not null
            if (finances != null) {

                //forwards the data to the trends page to be populated
                request.setAttribute("finances", finances);
                RequestDispatcher dispatcher = request.getRequestDispatcher("trends.jsp"); // Forward to your trends.jsp
                dispatcher.forward(request, response);

            } else {

                // Handle the case where finances are not found
                response.getWriter().println("Finances not found");
            }

        } catch (Exception e) {
             // Handle any excpetions and prints the trace
            e.printStackTrace();
        }
    }
}



