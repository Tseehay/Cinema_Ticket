package COM.SEARCH;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class searchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String searchTerm = request.getParameter("search");

        String jdbcUrl = "jdbc:mysql://localhost:3306/ticketing_system";
        String username = "root";
        String password = "Hope@251330";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "SELECT movieName FROM movie WHERE movieName = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, searchTerm);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String movieName = rs.getString("movieName");
                out.println("<p>Movie found: " + movieName + "</p>");
            } else {
                out.println("<p>No movie found.</p>");
            }

            rs.close();
            statement.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
}