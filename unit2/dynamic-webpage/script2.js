document.addEventListener('DOMContentLoaded', function() {
    const loadXmlBtn = document.getElementById('loadXml');
    const loadJsonBtn = document.getElementById('loadJson');
    const searchInput = document.getElementById('searchInput');
    const filterSelect = document.getElementById('filterSelect');
    const bookContainer = document.getElementById('bookContainer');
    const loadingIndicator = document.getElementById('loadingIndicator');
    const errorContainer = document.getElementById('errorContainer');
    
    let currentBooks = [];
    
    // Show/hide helpers
    function showLoading() {
        loadingIndicator.style.display = 'block';
        errorContainer.style.display = 'none';
        bookContainer.innerHTML = '';
    }
    
    function hideLoading() {
        loadingIndicator.style.display = 'none';
    }
    
    function showError(message) {
        errorContainer.textContent = message;
        errorContainer.style.display = 'block';
    }
    
    // Load JSON data (non-AJAX approach)
    function loadJsonData() {
        showLoading();
        
        // Simulate loading delay for better UX
        setTimeout(() => {
            try {
                // In a real non-AJAX implementation, JSON would be embedded in the page
                // For this example, we'll use the same fetch approach but synchronous
                const xhr = new XMLHttpRequest();
                xhr.open('GET', 'data.json', false); // Synchronous request
                xhr.send();
                
                if (xhr.status === 200) {
                    const data = JSON.parse(xhr.responseText);
                    currentBooks = data.books;
                    populateGenreFilter(currentBooks);
                    displayBooks(currentBooks);
                } else {
                    throw new Error(`HTTP error! status: ${xhr.status}`);
                }
            } catch (error) {
                showError('Error loading JSON: ' + error.message);
            } finally {
                hideLoading();
            }
        }, 500); // Small delay to show loading indicator
    }
    
    // Load XML data (synchronous load)
    function loadXmlData() {
        showLoading();
        
        setTimeout(() => {
            try {
                const xhr = new XMLHttpRequest();
                xhr.open('GET', 'data.xml', false); // Synchronous request
                xhr.send();
                
                if (xhr.status === 200) {
                    const xmlDoc = xhr.responseXML;
                    const bookNodes = xmlDoc.querySelectorAll('book');
                    
                    currentBooks = Array.from(bookNodes).map(book => ({
                        id: book.getAttribute('id'),
                        author: book.querySelector('author').textContent,
                        title: book.querySelector('title').textContent,
                        genre: book.querySelector('genre').textContent,
                        price: parseFloat(book.querySelector('price').textContent),
                        publish_date: book.querySelector('publish_date').textContent,
                        description: book.querySelector('description').textContent,
                        rating: parseFloat(book.querySelector('rating').textContent),
                        pages: parseInt(book.querySelector('pages').textContent),
                        language: book.querySelector('language').textContent
                    }));
                    
                    populateGenreFilter(currentBooks);
                    displayBooks(currentBooks);
                } else {
                    throw new Error(`HTTP error! status: ${xhr.status}`);
                }
            } catch (error) {
                showError('Error loading XML: ' + error.message);
            } finally {
                hideLoading();
            }
        }, 500); // Small delay to show loading indicator
    }
    
    // Populate genre filter
    function populateGenreFilter(books) {
        // Clear existing options except "All Genres"
        while (filterSelect.options.length > 1) {
            filterSelect.remove(1);
        }
        
        // Get all unique genres from books
        const genres = [...new Set(books.map(book => book.genre))];
        
        // Sort genres alphabetically
        genres.sort();
        
        // Add each genre to the dropdown
        genres.forEach(genre => {
            const option = document.createElement('option');
            option.value = genre;
            option.textContent = genre;
            filterSelect.appendChild(option);
        });
    }
    
    // Display books
    function displayBooks(booksToDisplay) {
        bookContainer.innerHTML = '';
        
        if (!booksToDisplay || booksToDisplay.length === 0) {
            bookContainer.innerHTML = '<p>No books found.</p>';
            return;
        }
        
        booksToDisplay.forEach(book => {
            const bookElement = document.createElement('div');
            bookElement.className = 'book';
            bookElement.innerHTML = `
                <h3>${book.title}</h3>
                <p class="author">Author: ${book.author}</p>
                <span class="genre">${book.genre}</span>
                <div class="meta">
                    <span class="rating">Rating: ${book.rating}/5</span>
                    <span class="pages">Pages: ${book.pages}</span>
                    <span class="language">Language: ${book.language}</span>
                </div>
                <p class="price">Price: $${book.price.toFixed(2)}</p>
                <p class="publish-date">Published: ${book.publish_date}</p>
                <p class="description">${book.description}</p>
            `;
            bookContainer.appendChild(bookElement);
        });
    }
    
    // Search and filter functionality
    function handleSearchAndFilter() {
        const searchTerm = searchInput.value.toLowerCase();
        const genre = filterSelect.value;
        
        let filteredBooks = currentBooks;
        
        // Apply genre filter
        if (genre !== 'all') {
            filteredBooks = filteredBooks.filter(book => book.genre === genre);
        }
        
        // Apply search term
        if (searchTerm) {
            filteredBooks = filteredBooks.filter(book => 
                book.title.toLowerCase().includes(searchTerm) || 
                book.author.toLowerCase().includes(searchTerm) ||
                book.description.toLowerCase().includes(searchTerm)
            );
        }
        
        displayBooks(filteredBooks);
    }
    
    // Event listeners
    loadJsonBtn.addEventListener('click', loadJsonData);
    loadXmlBtn.addEventListener('click', loadXmlData);
    searchInput.addEventListener('input', handleSearchAndFilter);
    filterSelect.addEventListener('change', handleSearchAndFilter);
    
    // Load JSON by default
    loadJsonData();
});