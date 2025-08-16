package com.example.bookapp.controller;

import com.example.bookapp.dao.BookDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class BookController extends HttpServlet {
    private BookDAO bookDAO;

    @Override
    public void init() {
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Map<String, Object>> books = bookDAO.getAllBooks();
            request.setAttribute("books", books);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error fetching books", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String action = request.getParameter("action");
        String methodOverride = request.getParameter("_method");

        try {
            if ("delete".equalsIgnoreCase(methodOverride)) {
                // Handle delete via form submission
                String id = request.getParameter("id");
                bookDAO.deleteBook(id);
            } else {
                // Handle add/update
                Map<String, Object> book = new HashMap<>();
                book.put("id", request.getParameter("id"));
                book.put("author", request.getParameter("author"));
                book.put("title", request.getParameter("title"));
                book.put("genre", request.getParameter("genre"));
                book.put("price", Double.parseDouble(request.getParameter("price")));
                book.put("publishDate", request.getParameter("publishDate"));
                book.put("rating", Double.parseDouble(request.getParameter("rating")));
                book.put("pages", Integer.parseInt(request.getParameter("pages")));
                book.put("language", request.getParameter("language"));

                if ("add".equals(action)) {
                    bookDAO.insertBook(book);
                } else if ("update".equals(action)) {
                    bookDAO.updateBook(book);
                }
            }

            // Refresh the page with updated book list
            response.sendRedirect("books");
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
        }
    }
}
