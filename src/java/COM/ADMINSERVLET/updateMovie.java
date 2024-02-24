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

@WebServlet("/updateMovie")
public class updateMovie extends HttpServlet {
private static final long serialVersionUID = 1L;
// Database credentials
private static final String DB_URL = "jdbc:mysql://localhost:3306/ticketing_system";
private static final String DB_USERNAME = "root";
private static final String DB_PASSWORD = "Hope@251330";

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String movieName = request.getParameter("movieName");
    int price = Integer.parseInt(request.getParameter("price"));
    String ReleaseDate = request.getParameter("DateAndTime");
    int MovieID = Integer.parseInt(request.getParameter("MovieID"));

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        String sql = "UPDATE movie SET movieName = ?, price = ?,ReleaseDate = ? WHERE MovieID = ?";
         PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, movieName);
        stmt.setInt(2, price);
        stmt.setString(3, ReleaseDate);
         stmt.setInt(4, MovieID);
        stmt.executeUpdate();

        response.sendRedirect("updatesuc.html");
    } catch (ClassNotFoundException | SQLException e) {
       response.sendRedirect("updatefail.html");
    }
}
}