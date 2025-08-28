<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Registration</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        form { max-width: 400px; margin: 0 auto; padding: 20px; border: 1px solid #ccc; border-radius: 10px; }
        label { display: block; margin-top: 10px; }
        input[type="text"], input[type="email"], input[type="number"] {
            width: 100%; padding: 8px; margin-top: 5px; border: 1px solid #aaa; border-radius: 5px;
        }
        input[type="submit"] {
            margin-top: 20px; background-color: #4CAF50; color: white; padding: 10px 20px;
            border: none; border-radius: 5px; cursor: pointer;
        }
        input[type="submit"]:hover { background-color: #45a049; }
        .error { color: red; text-align: center; }
    </style>
</head>
<body>
    <h2 style="text-align:center;">Student Registration Form</h2>

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <form action="register" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="age">Age:</label>
        <input type="number" id="age" name="age" min="1" required>

        <input type="submit" value="Register">
    </form>
</body>
</html>
