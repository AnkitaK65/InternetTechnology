package unit3.consoleBased;

import java.sql.*;
import java.sql.Date;

public class BookDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/book_catalog";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void insertBook(Book book) {
        String sql = "INSERT INTO books VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getId());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getTitle());
            stmt.setString(4, book.getGenre());
            stmt.setDouble(5, book.getPrice());
            stmt.setDate(6, Date.valueOf(book.getPublishDate()));
            stmt.setString(7, book.getDescription());
            stmt.setDouble(8, book.getRating());
            stmt.setInt(9, book.getPages());
            stmt.setString(10, book.getLanguage());

            stmt.executeUpdate();
            System.out.println("Book inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    public void getAllBooks() {
        String sql = "SELECT * FROM books";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.printf("ID: %s | Title: %s | Author: %s | Genre: %s | Price: %.2f | Rating: %.1f%n",
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getDouble("price"),
                        rs.getDouble("rating"));
            }
        } catch (SQLException e) {
            System.out.println("Error reading books: " + e.getMessage());
        }
    }

    public void updateBookTitle(String id, String newTitle) {
        String sql = "UPDATE books SET title = ? WHERE id = ?";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newTitle);
            stmt.setString(2, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book title updated successfully.");
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    public void deleteBook(String id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }
}
