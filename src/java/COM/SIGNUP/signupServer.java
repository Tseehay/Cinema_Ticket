  package COM.SIGNUP;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class signupServer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        PrintWriter out = response.getWriter();
        out.print(fullName);
        out.print(username);
        out.print(email);
        out.print(password);
        

        try {
            // JDBC connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/ticketing_system";
            String dbUser = "root";
            String dbPassword = "Hope@251330";
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Insert data into the database
            String sql = "INSERT INTO users (FullName, UserName, Email, Password) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, fullName);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);

                preparedStatement.executeUpdate();
            }

            // Close the connection
            connection.close();

            // Redirect to a success page or do something else
            response.sendRedirect("success.html");
        } catch (IOException | ClassNotFoundException | SQLException e) {
            // Handle exceptions and redirect to an error page
            response.sendRedirect("failed.html");
        }
    }
}
