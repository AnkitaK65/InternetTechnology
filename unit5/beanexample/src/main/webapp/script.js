    const filterInput = document.getElementById('filterInput');
    const table = document.getElementById('booksTable').getElementsByTagName('tbody')[0];
    const modal = document.getElementById('bookModal');
    const closeModalBtn = document.getElementById('closeModal');
    const addBookBtn = document.getElementById('addBookBtn');
    const bookForm = document.getElementById('bookForm');
    const modalTitle = document.getElementById('modalTitle');
    const actionType = document.getElementById('actionType');
    const bookIdField = document.getElementById('bookId');
    const idFieldWrapper = document.getElementById('idFieldWrapper');

    filterInput.addEventListener('input', function() {
        const query = this.value.toLowerCase();
        Array.from(table.rows).forEach(row => {
            const cellsText = Array.from(row.cells).slice(1,4).map(c => c.textContent.toLowerCase()).join(' ');
            row.style.display = cellsText.includes(query) ? '' : 'none';
        });
    });

    addBookBtn.onclick = () => {
        modalTitle.textContent = 'Add Book';
        bookForm.reset();
        bookIdField.value = '';
        actionType.value = 'insert';
        idFieldWrapper.style.display = 'block';
        bookIdField.readOnly = false;
        modal.style.display = 'block';
    };

    function openEditModal(id) {
        modalTitle.textContent = 'Edit Book';
        const row = Array.from(table.rows).find(r => r.cells[0].textContent == id);
        if(row) {
            bookIdField.value = row.cells[0].textContent;
            document.getElementById('title').value = row.cells[1].textContent;
            document.getElementById('author').value = row.cells[2].textContent;
            document.getElementById('genre').value = row.cells[3].textContent;
            document.getElementById('price').value = row.cells[4].textContent;
            document.getElementById('publishDate').value = row.cells[5].textContent;
            document.getElementById('rating').value = row.cells[6].textContent;
            document.getElementById('pages').value = row.cells[7].textContent;
            document.getElementById('language').value = row.cells[8].textContent;
            actionType.value = 'update';
            idFieldWrapper.style.display = 'none';
            modal.style.display = 'block';
        }
    }

    closeModalBtn.onclick = () => modal.style.display = 'none';
    window.onclick = e => { if(e.target == modal) modal.style.display = 'none'; };

    function confirmDelete() {
        return confirm("Are you sure you want to delete this book?");
    }