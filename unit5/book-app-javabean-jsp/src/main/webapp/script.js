let currentEditBookId = null;

function filterTable() {
    const filter = document.getElementById("filterInput").value.toLowerCase();
    const rows = document.querySelectorAll("#booksTable tbody tr");
    rows.forEach(row => {
        const text = row.textContent.toLowerCase();
        row.style.display = text.includes(filter) ? "" : "none";
    });
}

function openModalEdit(bookId) {
    currentEditBookId = bookId;
    const modal = document.getElementById("bookModal");
    const title = document.getElementById("modalTitle");
    const formAction = document.getElementById("formAction");
    
    // find row
    const rows = document.querySelectorAll("#booksTable tbody tr");
    for (const row of rows) {
        if (row.cells[0].textContent === bookId) {
            title.textContent = "Edit Book";
            formAction.value = "update";
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
    document.getElementById("formAction").value = "add";
}

document.getElementById("addBookBtn").addEventListener("click", () => {
    currentEditBookId = null;
    document.getElementById("modalTitle").textContent = "Add New Book";
    document.getElementById("formAction").value = "add";
    document.getElementById("bookForm").reset();
    document.getElementById("bookModal").style.display = "block";
});

document.getElementById("closeModal").addEventListener("click", closeModal);

document.getElementById("filterInput").addEventListener("input", filterTable);

window.onclick = function(event) {
    const modal = document.getElementById("bookModal");
    if (event.target === modal) {
        closeModal();
    }
};

function confirmDelete(form) {
    return confirm("Are you sure you want to delete this book?");
}
