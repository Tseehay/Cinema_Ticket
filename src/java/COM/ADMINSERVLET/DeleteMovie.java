 package COM.ADMINSERVLET;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteMovie")
public class DeleteMovie extends HttpServlet {
private static final long serialVersionUID = 1L;
// Database credentials
private static final String DB_URL = "jdbc:mysql://localhost:3306/ticketing_system";
private static final String DB_USERNAME = "root";
private static final String DB_PASSWORD = "Hope@251330";

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String movieName = request.getParameter("movieName");

    

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        String sql = "DELETE FROM movie WHERE movieName = ?";
         PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, movieName);
        stmt.executeUpdate();

        response.sendRedirect("succMovie.html");
    } catch (ClassNotFoundException | SQLException e) {
       response.sendRedirect("failedMovie.html");
   
    }
}
}