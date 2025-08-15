let booksData = { books: [] };
let editIndex = null;

async function loadBooks() {
    const res = await fetch("books");
    booksData = await res.json();
    renderTable(document.getElementById("filterInput").value);
}

function renderTable(filterText = "") {
    const tbody = document.querySelector("#booksTable tbody");
    tbody.innerHTML = "";
    booksData.books
        .filter(book => {
            const search = filterText.toLowerCase();
            return (
                book.title.toLowerCase().includes(search) ||
                book.author.toLowerCase().includes(search) ||
                book.genre.toLowerCase().includes(search)
            );
        })
        .forEach((book, index) => {
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
                <td>
                    <button class="action-btn edit-btn" onclick="openModal(${index})">Edit</button>
                    <button class="action-btn delete-btn" onclick="deleteBook(${index})">Delete</button>
                </td>
            `;
            tbody.appendChild(row);
        });
}

function openModal(index = null) {
    editIndex = index;
    const modal = document.getElementById("bookModal");
    const title = document.getElementById("modalTitle");
    const form = document.getElementById("bookForm");

    if (index !== null) {
        title.textContent = "Edit Book";
        const book = booksData.books[index];
        document.getElementById("bookId").value = book.id;
        document.getElementById("bookTitle").value = book.title;
        document.getElementById("bookAuthor").value = book.author;
        document.getElementById("bookGenre").value = book.genre;
        document.getElementById("bookPrice").value = book.price;
        document.getElementById("bookDate").value = book.publish_date;
        document.getElementById("bookRating").value = book.rating;
        document.getElementById("bookPages").value = book.pages;
        document.getElementById("bookLanguage").value = book.language;
    } else {
        title.textContent = "Add New Book";
        form.reset();
    }

    modal.style.display = "block";
}

function closeModal() {
    document.getElementById("bookModal").style.display = "none";
}

async function deleteBook(index) {
    const book = booksData.books[index];
    if (confirm("Are you sure you want to delete this book?")) {
        await fetch(`books?id=${book.id}`, { method: "DELETE" });
        loadBooks();
    }
}

document.getElementById("addBookBtn").addEventListener("click", () => openModal());
document.getElementById("closeModal").addEventListener("click", closeModal);

document.getElementById("bookForm").addEventListener("submit", async function(e) {
    e.preventDefault();
    const book = {
        id: document.getElementById("bookId").value,
        title: document.getElementById("bookTitle").value,
        author: document.getElementById("bookAuthor").value,
        genre: document.getElementById("bookGenre").value,
        price: parseFloat(document.getElementById("bookPrice").value),
        publish_date: document.getElementById("bookDate").value,
        rating: parseFloat(document.getElementById("bookRating").value),
        pages: parseInt(document.getElementById("bookPages").value),
        language: document.getElementById("bookLanguage").value
    };

    if (editIndex === null) {
        await fetch("books", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(book)
        });
    } else {
        await fetch("books", {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(book)
        });
    }

    closeModal();
    loadBooks();
});

document.getElementById("filterInput").addEventListener("input", function() {
    renderTable(this.value);
});

window.onclick = function(event) {
    const modal = document.getElementById("bookModal");
    if (event.target === modal) {
        closeModal();
    }
};

loadBooks();