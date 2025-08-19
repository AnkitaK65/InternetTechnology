<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book Details</title>
</head>
<body>
    <h1>📚 Book Details</h1>

    <%
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/book_catalog?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "password";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection(url, user, password);

            // Execute query
            stmt = conn.createStatement();
            String sql = "SELECT id, title, author, genre, price FROM books LIMIT 5";
            rs = stmt.executeQuery(sql);

            // Display table
            out.println("<table border='1' cellpadding='8'>");
            out.println("<tr><th>ID</th><th>Title</th><th>Author</th><th>Genre</th><th>Price ($)</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("id") + "</td>");
                out.println("<td>" + rs.getString("title") + "</td>");
                out.println("<td>" + rs.getString("author") + "</td>");
                out.println("<td>" + rs.getString("genre") + "</td>");
                out.println("<td>" + rs.getDouble("price") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

        } catch (Exception e) {
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    %>

</body>
</html>
