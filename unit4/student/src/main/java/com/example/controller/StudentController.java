package com.example.controller;

import com.example.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/register")
public class StudentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String ageStr = request.getParameter("age");

        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("error", "Name is required.");
            forwardToRegister(request, response);
            return;
        }

        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Email is required.");
            forwardToRegister(request, response);
            return;
        }

        if (ageStr == null || !ageStr.matches("\\d+") || Integer.parseInt(ageStr) <= 0) {
            request.setAttribute("error", "Please enter a valid age.");
            forwardToRegister(request, response);
            return;
        }

        int age = Integer.parseInt(ageStr);
        Student student = new Student(name, email, age);

        if (saveStudentToDatabase(student)) {
            request.setAttribute("student", student);
            request.getRequestDispatcher("success.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Registration failed. Please try again.");
            forwardToRegister(request, response);
        }
    }

    private boolean saveStudentToDatabase(Student student) {
        try {
            Class.forName(DB_DRIVER);
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO students (name, email, age) VALUES (?, ?, ?)")) {

                stmt.setString(1, student.getName());
                stmt.setString(2, student.getEmail());
                stmt.setInt(3, student.getAge());

                return stmt.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void forwardToRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
