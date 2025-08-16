package com.example.bookapp.dao;

import java.sql.*;
import java.util.*;

public class BookDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/book_catalog?useSSL=false";
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

    public List<Map<String, Object>> getAllBooks() throws SQLException {
        List<Map<String, Object>> books = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books");
             ResultSet rs = stmt.executeQuery()) {
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
        }
        return books;
    }

    public void insertBook(Map<String, Object> book) throws SQLException {
        String sql = "INSERT INTO books (id, author, title, genre, price, publish_date, rating, pages, language) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, (String) book.get("id"));
            stmt.setString(2, (String) book.get("author"));
            stmt.setString(3, (String) book.get("title"));
            stmt.setString(4, (String) book.get("genre"));
            stmt.setDouble(5, (Double) book.get("price"));
            stmt.setString(6, (String) book.get("publishDate"));
            stmt.setDouble(7, (Double) book.get("rating"));
            stmt.setInt(8, (Integer) book.get("pages"));
            stmt.setString(9, (String) book.get("language"));
            stmt.executeUpdate();
        }
    }

    public void updateBook(Map<String, Object> book) throws SQLException {
        String sql = "UPDATE books SET author=?, title=?, genre=?, price=?, publish_date=?, rating=?, pages=?, language=? WHERE id=?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, (String) book.get("author"));
            stmt.setString(2, (String) book.get("title"));
            stmt.setString(3, (String) book.get("genre"));
            stmt.setDouble(4, (Double) book.get("price"));
            stmt.setString(5, (String) book.get("publishDate"));
            stmt.setDouble(6, (Double) book.get("rating"));
            stmt.setInt(7, (Integer) book.get("pages"));
            stmt.setString(8, (String) book.get("language"));
            stmt.setString(9, (String) book.get("id"));
            stmt.executeUpdate();
        }
    }

    public void deleteBook(String id) throws SQLException {
        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM books WHERE id=?")) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }
}
