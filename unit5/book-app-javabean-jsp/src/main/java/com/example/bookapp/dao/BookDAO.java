package com.example.bookapp.dao;

import com.example.bookapp.model.Book;
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

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getString("id"));
                book.setAuthor(rs.getString("author"));
                book.setTitle(rs.getString("title"));
                book.setGenre(rs.getString("genre"));
                book.setPrice(rs.getDouble("price"));
                book.setPublishDate(rs.getString("publish_date"));
                book.setRating(rs.getDouble("rating"));
                book.setPages(rs.getInt("pages"));
                book.setLanguage(rs.getString("language"));
                books.add(book);
            }
        }
        return books;
    }

    public void insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (id, author, title, genre, price, publish_date, rating, pages, language) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getId());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getTitle());
            stmt.setString(4, book.getGenre());
            stmt.setDouble(5, book.getPrice());
            stmt.setString(6, book.getPublishDate());
            stmt.setDouble(7, book.getRating());
            stmt.setInt(8, book.getPages());
            stmt.setString(9, book.getLanguage());
            stmt.executeUpdate();
        }
    }

    public void updateBook(Book book) throws SQLException {
        String sql = "UPDATE books SET author=?, title=?, genre=?, price=?, publish_date=?, rating=?, pages=?, language=? WHERE id=?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getAuthor());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getGenre());
            stmt.setDouble(4, book.getPrice());
            stmt.setString(5, book.getPublishDate());
            stmt.setDouble(6, book.getRating());
            stmt.setInt(7, book.getPages());
            stmt.setString(8, book.getLanguage());
            stmt.setString(9, book.getId());
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
