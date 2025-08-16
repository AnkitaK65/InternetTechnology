// Store the book being edited
let currentEditBookId = null;

// Function to filter table rows
function filterTable() {
    const filter = document.getElementById("filterInput").value.toLowerCase();
    const rows = document.querySelectorAll("#booksTable tbody tr");
    
    rows.forEach(row => {
        const text = row.textContent.toLowerCase();
        row.style.display = text.includes(filter) ? "" : "none";
    });
}

// Open modal for editing a book
function openModalEdit(bookId) {
    currentEditBookId = bookId;
    const modal = document.getElementById("bookModal");
    const title = document.getElementById("modalTitle");
    
    // Find the book row in the table
    const rows = document.querySelectorAll("#booksTable tbody tr");
    for (const row of rows) {
        if (row.cells[0].textContent === bookId) {
            title.textContent = "Edit Book";
            document.getElementById("bookId").value = row.cells[0].textContent;
            document.getElementById("bookTitle").value = row.cells[2].textContent;
            document.getElementById("bookAuthor").value = row.cells[1].textContent;
            document.getElementById("bookGenre").value = row.cells[3].textContent;
            document.getElementById("bookPrice").value = row.cells[4].textContent;
            document.getElementById("bookDate").value = formatDateForInput(row.cells[5].textContent);
            document.getElementById("bookRating").value = row.cells[6].textContent;
            document.getElementById("bookPages").value = row.cells[7].textContent;
            document.getElementById("bookLanguage").value = row.cells[8].textContent;
            break;
        }
    }
    
    modal.style.display = "block";
}

// Helper function to format date for input[type=date]
function formatDateForInput(dateString) {
    if (!dateString) return "";
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

function closeModal() {
    document.getElementById("bookModal").style.display = "none";
    currentEditBookId = null;
    document.getElementById("bookForm").reset();
}

async function deleteBook(bookId) {
    if (confirm("Are you sure you want to delete this book?")) {
        try {
            const response = await fetch(`books?id=${bookId}`, { 
                method: "DELETE" 
            });
            const result = await response.json();
            if (response.ok) {
                window.location.reload();
            } else {
                alert(result.error || "Error deleting book");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("Error deleting book");
        }
    }
}

// Event Listeners
document.getElementById("addBookBtn").addEventListener("click", () => {
    currentEditBookId = null;
    document.getElementById("modalTitle").textContent = "Add New Book";
    document.getElementById("bookForm").reset();
    document.getElementById("bookModal").style.display = "block";
});

document.getElementById("closeModal").addEventListener("click", closeModal);

document.getElementById("bookForm").addEventListener("submit", async function(e) {
    e.preventDefault();
    
    const bookData = {
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

    try {
        const method = currentEditBookId ? "PUT" : "POST";
        const response = await fetch("books", {
            method: method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(bookData)
        });
        
        const result = await response.json();
        if (response.ok) {
            window.location.reload();
        } else {
            alert(result.error || "Error saving book");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Error saving book");
    }
});

document.getElementById("filterInput").addEventListener("input", filterTable);

window.onclick = function(event) {
    const modal = document.getElementById("bookModal");
    if (event.target === modal) {
        closeModal();
    }
};

// Initial filter setup
document.getElementById("filterInput").addEventListener("input", filterTable);