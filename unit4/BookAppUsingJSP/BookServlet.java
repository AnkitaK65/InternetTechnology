package unit4.BookAppUsingJSP;

import java.io.*;
import java.sql.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.json.JSONObject;

public class BookServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/book_catalog";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    private Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try (Connection conn = connect();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books");
                ResultSet rs = stmt.executeQuery()) {
            
            List<Map<String, Object>> books = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> book = new HashMap<>();
                book.put("id", rs.getString("id"));
                book.put("author", rs.getString("author"));
                book.put("title", rs.getString("title"));
                book.put("genre", rs.getString("genre"));
                book.put("price", rs.getDouble("price"));
                book.put("publishDate", rs.getString("publish_date"));
                book.put("rating", rs.getDouble("rating"));
                book.put("pages", rs.getInt("pages"));
                book.put("language", rs.getString("language"));
                books.add(book);
            }
            
            request.setAttribute("books", books);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            
        } catch (SQLException e) {
            handleError(response, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processBookRequest(request, response, "INSERT INTO books (id, author, title, genre, price, publish_date, rating, pages, language) VALUES (?,?,?,?,?,?,?,?,?)");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processBookRequest(request, response, "UPDATE books SET author=?, title=?, genre=?, price=?, publish_date=?, rating=?, pages=?, language=? WHERE id=?");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        try (Connection conn = connect();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM books WHERE id=?")) {
            
            stmt.setString(1, id);
            stmt.executeUpdate();
            sendSuccessResponse(response);
            
        } catch (SQLException e) {
            handleError(response, e);
        }
    }

    private void processBookRequest(HttpServletRequest request, HttpServletResponse response, String sql) throws IOException {
        try {
            JSONObject bookJson = new JSONObject(readBody(request));
            try (Connection conn = connect();
                    PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                if (sql.startsWith("INSERT")) {
                    stmt.setString(1, bookJson.getString("id"));
                    stmt.setString(2, bookJson.getString("author"));
                    stmt.setString(3, bookJson.getString("title"));
                    stmt.setString(4, bookJson.getString("genre"));
                    stmt.setDouble(5, bookJson.getDouble("price"));
                    stmt.setString(6, bookJson.getString("publish_date"));
                    stmt.setDouble(7, bookJson.getDouble("rating"));
                    stmt.setInt(8, bookJson.getInt("pages"));
                    stmt.setString(9, bookJson.getString("language"));
                } else {
                    stmt.setString(1, bookJson.getString("author"));
                    stmt.setString(2, bookJson.getString("title"));
                    stmt.setString(3, bookJson.getString("genre"));
                    stmt.setDouble(4, bookJson.getDouble("price"));
                    stmt.setString(5, bookJson.getString("publish_date"));
                    stmt.setDouble(6, bookJson.getDouble("rating"));
                    stmt.setInt(7, bookJson.getInt("pages"));
                    stmt.setString(8, bookJson.getString("language"));
                    stmt.setString(9, bookJson.getString("id"));
                }
                
                stmt.executeUpdate();
                sendSuccessResponse(response);
            }
        } catch (SQLException e) {
            handleError(response, e);
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

    private void sendSuccessResponse(HttpServletResponse response) throws IOException {
        response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write("{\"status\":\"success\"}");
    }

    private void handleError(HttpServletResponse response, Exception e) throws IOException {
        e.printStackTrace();
        response.setStatus(500);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
    }
}