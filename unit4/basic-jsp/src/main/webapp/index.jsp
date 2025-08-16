<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>📚 My Book Collection</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<header>
    <img src="https://cdn-icons-png.flaticon.com/512/29/29302.png" alt="Logo">
    <h1>My Book Collection</h1>
</header>

<div class="controls">
    <input type="text" id="filterInput" placeholder="Search by title, author, or genre...">
    <button id="addBookBtn">➕ Add New Book</button>
</div>

<table id="booksTable">
    <thead>
        <tr>
            <th>ID</th>
            <th>Author</th>
            <th>Title</th>
            <th>Genre</th>
            <th>Price ($)</th>
            <th>Publish Date</th>
            <th>Rating</th>
            <th>Pages</th>
            <th>Language</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.id}</td>
                <td>${book.author}</td>
                <td>${book.title}</td>
                <td>${book.genre}</td>
                <td>${book.price}</td>
                <td>${book.publishDate}</td>
                <td>${book.rating}</td>
                <td>${book.pages}</td>
                <td>${book.language}</td>
                <td>
                    <button class="action-btn edit-btn" onclick="openModalEdit('${book.id}')">Edit</button>
                    <button class="action-btn delete-btn" onclick="deleteBook('${book.id}')">Delete</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- Modal -->
<div id="bookModal" class="modal">
    <div class="modal-content">
        <span class="close-btn" id="closeModal">&times;</span>
        <h2 id="modalTitle">Add New Book</h2>
        <form id="bookForm">
            <label>ID:</label>
            <input type="text" id="bookId" required>
            
            <label>Title:</label>
            <input type="text" id="bookTitle" required>
            
            <label>Author:</label>
            <input type="text" id="bookAuthor" required>
            
            <label>Genre:</label>
            <input type="text" id="bookGenre" required>
            
            <label>Price:</label>
            <input type="number" id="bookPrice" step="0.01" required>
            
            <label>Publish Date:</label>
            <input type="date" id="bookDate" required>
            
            <label>Rating:</label>
            <input type="number" id="bookRating" step="0.1" min="0" max="5" required>
            
            <label>Pages:</label>
            <input type="number" id="bookPages" required>
            
            <label>Language:</label>
            <input type="text" id="bookLanguage" required>
            
            <button type="submit">💾 Save</button>
        </form>
    </div>
</div>

<script src="script.js"></script>
</body>
</html>