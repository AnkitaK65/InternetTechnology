const booksData = {
    "books": [
        {"id":"bk101","author":"Gambardella, Matthew","title":"XML Developer's Guide","genre":"Computer","price":44.95,"publish_date":"2000-10-01","rating":4.5,"pages":350,"language":"English"},
        {"id":"bk102","author":"Ralls, Kim","title":"Midnight Rain","genre":"Fantasy","price":5.95,"publish_date":"2000-12-16","rating":3.8,"pages":280,"language":"English"},
        {"id":"bk103","author":"Corets, Eva","title":"Maeve Ascendant","genre":"Fantasy","price":5.95,"publish_date":"2000-11-17","rating":4.2,"pages":320,"language":"English"},
        {"id":"bk104","author":"Corets, Eva","title":"Oberon's Legacy","genre":"Fantasy","price":5.95,"publish_date":"2001-03-10","rating":4.0,"pages":295,"language":"English"},
        {"id":"bk105","author":"Corets, Eva","title":"The Sundered Grail","genre":"Fantasy","price":5.95,"publish_date":"2001-09-10","rating":4.3,"pages":410,"language":"English"},
        {"id":"bk106","author":"Randall, Cynthia","title":"Lover Birds","genre":"Romance","price":4.95,"publish_date":"2000-09-02","rating":3.5,"pages":220,"language":"English"},
        {"id":"bk107","author":"Thurman, Paula","title":"Splish Splash","genre":"Romance","price":4.95,"publish_date":"2000-11-02","rating":3.9,"pages":240,"language":"English"},
        {"id":"bk108","author":"Knorr, Stefan","title":"Creepy Crawlies","genre":"Horror","price":4.95,"publish_date":"2000-12-06","rating":4.1,"pages":310,"language":"English"},
        {"id":"bk109","author":"Kress, Peter","title":"Paradox Lost","genre":"Science Fiction","price":6.95,"publish_date":"2000-11-02","rating":4.4,"pages":380,"language":"English"},
        {"id":"bk110","author":"O'Brien, Tim","title":"Microsoft .NET: The Programming Bible","genre":"Computer","price":36.95,"publish_date":"2000-12-09","rating":4.7,"pages":450,"language":"English"},
        {"id":"bk111","author":"O'Brien, Tim","title":"MSXML3: A Comprehensive Guide","genre":"Computer","price":36.95,"publish_date":"2000-12-01","rating":4.3,"pages":420,"language":"English"},
        {"id":"bk112","author":"Galos, Mike","title":"Visual Studio 7: A Beginner's Guide","genre":"Computer","price":29.95,"publish_date":"2001-04-16","rating":4.0,"pages":320,"language":"English"},
        {"id":"bk113","author":"Tanaka, Yuki","title":"Whispers in the Wind","genre":"Romance","price":5.95,"publish_date":"2001-02-18","rating":4.5,"pages":260,"language":"Japanese"},
        {"id":"bk114","author":"Mendoza, Carlos","title":"The Silent Killer","genre":"Mystery","price":6.95,"publish_date":"2001-05-10","rating":4.2,"pages":340,"language":"Spanish"},
        {"id":"bk115","author":"Chen, Li","title":"The Art of War: Modern Applications","genre":"Business","price":15.95,"publish_date":"2001-03-22","rating":4.6,"pages":290,"language":"Chinese"}
    ]
};

let editIndex = null;

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
        // Editing existing book
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
        // Adding new book
        title.textContent = "Add New Book";
        form.reset();
    }

    modal.style.display = "block";
}

function closeModal() {
    document.getElementById("bookModal").style.display = "none";
}

function deleteBook(index) {
    if (confirm("Are you sure you want to delete this book?")) {
        booksData.books.splice(index, 1);
        renderTable(document.getElementById("filterInput").value);
    }
}

document.getElementById("addBookBtn").addEventListener("click", () => openModal());
document.getElementById("closeModal").addEventListener("click", closeModal);

document.getElementById("bookForm").addEventListener("submit", function(e) {
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
        booksData.books.push(book);
    } else {
        booksData.books[editIndex] = book;
    }

    closeModal();
    renderTable(document.getElementById("filterInput").value);
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

renderTable();
