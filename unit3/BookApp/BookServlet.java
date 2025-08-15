package unit3.BookApp;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.json.JSONArray;
import org.json.JSONObject;


public class BookServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/book_catalog";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    private Connection connect() throws SQLException {
        try {
            // Explicitly load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

            throw new SQLException("MySQL Driver not found", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        JSONArray booksArray = new JSONArray();

        try (Connection conn = connect();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books");
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                JSONObject book = new JSONObject();
                book.put("id", rs.getString("id"));
                book.put("author", rs.getString("author"));
                book.put("title", rs.getString("title"));
                book.put("genre", rs.getString("genre"));
                book.put("price", rs.getDouble("price"));
                book.put("publish_date", rs.getString("publish_date"));
                book.put("rating", rs.getDouble("rating"));
                book.put("pages", rs.getInt("pages"));
                book.put("language", rs.getString("language"));
                booksArray.put(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(500);

            // Send error text to browser
            response.setContentType("text/plain");
            response.getWriter().write("Error: " + e.getMessage());
        }

        JSONObject result = new JSONObject();
        result.put("books", booksArray);
        response.getWriter().write(result.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Add a new book
        JSONObject book = new JSONObject(readBody(request));

        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO books (id, author, title, genre, price, publish_date, rating, pages, language) VALUES (?,?,?,?,?,?,?,?,?)")) {

            stmt.setString(1, book.getString("id"));
            stmt.setString(2, book.getString("author"));
            stmt.setString(3, book.getString("title"));
            stmt.setString(4, book.getString("genre"));
            stmt.setDouble(5, book.getDouble("price"));
            stmt.setString(6, book.getString("publish_date"));
            stmt.setDouble(7, book.getDouble("rating"));
            stmt.setInt(8, book.getInt("pages"));
            stmt.setString(9, book.getString("language"));

            stmt.executeUpdate();
            response.setStatus(200);

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(500);

            // Send error text to browser
            response.setContentType("text/plain");
            response.getWriter().write("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Update book
        JSONObject book = new JSONObject(readBody(request));

        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE books SET author=?, title=?, genre=?, price=?, publish_date=?, rating=?, pages=?, language=? WHERE id=?")) {

            stmt.setString(1, book.getString("author"));
            stmt.setString(2, book.getString("title"));
            stmt.setString(3, book.getString("genre"));
            stmt.setDouble(4, book.getDouble("price"));
            stmt.setString(5, book.getString("publish_date"));
            stmt.setDouble(6, book.getDouble("rating"));
            stmt.setInt(7, book.getInt("pages"));
            stmt.setString(8, book.getString("language"));
            stmt.setString(9, book.getString("id"));

            stmt.executeUpdate();
            response.setStatus(200);

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(500);

            // Send error text to browser
            response.setContentType("text/plain");
            response.getWriter().write("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM books WHERE id=?")) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            response.setStatus(200);

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(500);

            // Send error text to browser
            response.setContentType("text/plain");
            response.getWriter().write("Error: " + e.getMessage());
        }
    }

    private String readBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) sb.append(line);
        }
        return sb.toString();
    }
}