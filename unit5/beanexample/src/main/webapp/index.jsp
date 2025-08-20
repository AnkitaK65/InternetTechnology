<%@ page import="java.util.*, java.sql.*, com.javabean.bookapp.model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="bookBean" class="com.javabean.bookapp.model.Book" scope="request" />

<%
    // MySQL connection
    String url = "jdbc:mysql://localhost:3306/book_catalog?useSSL=false&allowPublicKeyRetrieval=true";
    String user = "root"; // your DB user
    String password = "password"; // your DB password

    List<Book> books = new ArrayList<>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);

        String action = request.getParameter("action");

        if("insert".equals(action)) {
            ps = conn.prepareStatement(
                "INSERT INTO books(id,title,author,genre,price,publish_date,rating,pages,language) VALUES(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, request.getParameter("id"));
            ps.setString(2, request.getParameter("title"));
            ps.setString(3, request.getParameter("author"));
            ps.setString(4, request.getParameter("genre"));
            ps.setDouble(5, Double.parseDouble(request.getParameter("price")));
            ps.setString(6, request.getParameter("publishDate"));
            ps.setDouble(7, Double.parseDouble(request.getParameter("rating")));
            ps.setInt(8, Integer.parseInt(request.getParameter("pages")));
            ps.setString(9, request.getParameter("language"));
            ps.executeUpdate();
        }

        if("update".equals(action)) {
            ps = conn.prepareStatement(
                "UPDATE books SET title=?, author=?, genre=?, price=?, publish_date=?, rating=?, pages=?, language=? WHERE id=?");
            ps.setString(1, request.getParameter("title"));
            ps.setString(2, request.getParameter("author"));
            ps.setString(3, request.getParameter("genre"));
            ps.setDouble(4, Double.parseDouble(request.getParameter("price")));
            ps.setString(5, request.getParameter("publishDate"));
            ps.setDouble(6, Double.parseDouble(request.getParameter("rating")));
            ps.setInt(7, Integer.parseInt(request.getParameter("pages")));
            ps.setString(8, request.getParameter("language"));
            ps.setString(9, request.getParameter("id"));
            ps.executeUpdate();
        }

        if("delete".equals(action)) {
            ps = conn.prepareStatement("DELETE FROM books WHERE id=?");
            ps.setString(1, request.getParameter("id"));
            ps.executeUpdate();
        }

        // Fetch all books
        ps = conn.prepareStatement("SELECT * FROM books");
        rs = ps.executeQuery();
        while(rs.next()) {
            Book b = new Book();
            b.setId(rs.getString("id"));
            b.setTitle(rs.getString("title"));
            b.setAuthor(rs.getString("author"));
            b.setGenre(rs.getString("genre"));
            b.setPrice(rs.getDouble("price"));
            b.setPublishDate(rs.getString("publish_date"));
            b.setRating(rs.getDouble("rating"));
            b.setPages(rs.getInt("pages"));
            b.setLanguage(rs.getString("language"));
            books.add(b);
        }

        request.setAttribute("books", books);

    } catch(Exception e){ e.printStackTrace(); }
    finally {
        try { if(rs!=null) rs.close(); if(ps!=null) ps.close(); if(conn!=null) conn.close(); } catch(Exception e){}
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>📚 My Book Collection</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<header>
    <h1>📚 My Book Collection</h1>
</header>

<div class="controls">
    <input type="text" id="filterInput" placeholder="Search by title, author, or genre...">
    <button id="addBookBtn">➕ Add New Book</button>
</div>

<table id="booksTable">
    <thead>
        <tr>
            <th>ID</th><th>Title</th><th>Author</th><th>Genre</th>
            <th>Price</th><th>Publish Date</th><th>Rating</th><th>Pages</th><th>Language</th><th>Actions</th>
        </tr>
    </thead>
    <tbody>
    <%
        for(Book b : books) {
    %>
        <tr>
            <td><%= b.getId() %></td>
            <td><%= b.getTitle() %></td>
            <td><%= b.getAuthor() %></td>
            <td><%= b.getGenre() %></td>
            <td><%= b.getPrice() %></td>
            <td><%= b.getPublishDate() %></td>
            <td><%= b.getRating() %></td>
            <td><%= b.getPages() %></td>
            <td><%= b.getLanguage() %></td>
            <td>
                <button class="edit-btn" onclick='openEditModal("<%= b.getId() %>")'>Edit</button>
                <form method="post" style="display:inline;" onsubmit="return confirmDelete();">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<%= b.getId() %>">
                    <button type="submit" class="action-btn delete-btn">Delete</button>
                </form>
            </td>
        </tr>
    <%
        }
    %>
    </tbody>
</table>

<!-- Add/Edit Modal -->
<div id="bookModal" class="modal">
    <div class="modal-content">
        <span class="close-btn" id="closeModal">&times;</span>
        <h2 id="modalTitle">Add Book</h2>
        <form id="bookForm" method="post">
            <!-- ID field, visible when adding, hidden when editing -->
            <div id="idFieldWrapper">
                ID: <input type="text" id="bookId" name="id" required>
            </div>
            <input type="hidden" id="actionType" name="action" value="insert">
            Title:<input type="text" id="title" name="title" required>
            Author:<input type="text" id="author" name="author" required>
            Genre:<input type="text" id="genre" name="genre" required>
            Price:<input type="number" step="0.01" id="price" name="price" required>
            Publish Date:<input type="date" id="publishDate" name="publishDate" required>
            Rating:<input type="number" step="0.1" min="0" max="5" id="rating" name="rating" required>
            Pages:<input type="number" id="pages" name="pages" required>
            Language:<input type="text" id="language" name="language" required>
            <button type="submit" id="saveBtn">Save</button>
        </form>
    </div>
</div>

<script src="script.js"></script>
</body>
</html>
