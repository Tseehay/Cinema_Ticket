package COM.BOOKING;

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

@WebServlet("/booking")
public class bookingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String movie = request.getParameter("movie");
        int amount = Integer.parseInt(request.getParameter("amount"));
        String name = request.getParameter("name");
        String place = request.getParameter("place");

        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/ticketing_system";
            String username = "root";
            String password = "Hope@251330";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            String sql = "INSERT INTO bookings (movie, amount, name, place) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(Integer.parseInt(movie) * amount));
            statement.setInt(2, amount);
            statement.setString(3, name);
            statement.setString(4, place);

            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }

        response.sendRedirect("bookingsuc.html");
    }
}