package com.example.bookapp.controller;

import com.example.bookapp.dao.BookDAO;
import com.example.bookapp.model.Book;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

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
            List<Book> books = bookDAO.getAllBooks();
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
                String id = request.getParameter("id");
                bookDAO.deleteBook(id);
            } else {
                Book book = new Book();
                book.setId(request.getParameter("id"));
                book.setAuthor(request.getParameter("author"));
                book.setTitle(request.getParameter("title"));
                book.setGenre(request.getParameter("genre"));
                book.setPrice(Double.parseDouble(request.getParameter("price")));
                book.setPublishDate(request.getParameter("publishDate"));
                book.setRating(Double.parseDouble(request.getParameter("rating")));
                book.setPages(Integer.parseInt(request.getParameter("pages")));
                book.setLanguage(request.getParameter("language"));

                if ("add".equals(action)) {
                    bookDAO.insertBook(book);
                } else if ("update".equals(action)) {
                    bookDAO.updateBook(book);
                }
            }

            response.sendRedirect("books");
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
        }
    }
}

