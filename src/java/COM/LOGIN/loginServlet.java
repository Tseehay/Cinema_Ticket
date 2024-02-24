 package COM.LOGIN;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class loginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/ticketing_system";
            String dbUser = "root";
            String dbPassword = "Hope@251330";
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            String query = "SELECT * FROM Users WHERE username = ? AND password = ?";

            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                      if (username.equals("admin") && password.equals("admin")) {
                            session.setAttribute("name", rs.getString("username"));
                            response.sendRedirect("adminpage.html"); // Redirect to adminpage.html
                        } else {
                            session.setAttribute("name", rs.getString("username"));
                            response.sendRedirect("landingpage.html"); // Redirect to landingpage.html
                        }

//                    session.setAttribute("name", rs.getString("username"));
//                    response.sendRedirect("landingpage.html"); // Redirect to landingpage.html
                } else {
                    request.setAttribute("status", "failed");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("loginfail.html");
                    dispatcher.forward(request, response);
                }
                
            } catch (IOException | SQLException | ServletException e) {
            }

        } catch (ClassNotFoundException | SQLException e) {
        }
    }
    
}



