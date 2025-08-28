<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Success</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; }
        .container { max-width: 500px; margin: 0 auto; padding: 20px; border: 1px solid #4CAF50; border-radius: 10px; background-color: #f9f9f9; }
        h2 { color: #4CAF50; }
        .info { margin: 20px 0; }
        a { color: #0066cc; text-decoration: none; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <div class="container">
        <h2>✅ Registration Successful!</h2>
        <div class="info">
            <p><strong>Name:</strong> <c:out value="${student.name}"/></p>
            <p><strong>Email:</strong> <c:out value="${student.email}"/></p>
            <p><strong>Age:</strong> <c:out value="${student.age}"/></p>
        </div>
        <p>Thank you for registering.</p>
        <a href="register.jsp">Register Another Student</a>
    </div>
</body>
</html>
