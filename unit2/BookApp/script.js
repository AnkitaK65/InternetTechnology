let books = []; // will store loaded books

// Load books from data.json
fetch("data.json")
    .then(response => response.json())
    .then(data => {
        books = data.books;
        renderTable();
    })
    .catch(err => console.error("Error loading data.json:", err));

// Render books table
function renderTable(filterText = "") {
    const tbody = document.querySelector("#booksTable tbody");
    tbody.innerHTML = "";

    books
        .filter(book => {
            const search = filterText.toLowerCase();
            return (
                book.title.toLowerCase().includes(search) ||
                book.author.toLowerCase().includes(search) ||
                book.genre.toLowerCase().includes(search)
            );
        })
        .forEach(book => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${book.id}</td>
                <td>${book.author}</td>
                <td>${book.title}</td>
                <td>${book.genre}</td>
                <td>${book.price}</td>
                <td>${book.publish_date}</td>
                <td>${book.rating}</td>
                <td>${book.pages}</td>
                <td>${book.language}</td>
                <td>—</td> <!-- no actions in read-only mode -->
            `;
            tbody.appendChild(row);
        });
}

// Search filter
document.getElementById("filterInput").addEventListener("input", function() {
    renderTable(this.value);
});
