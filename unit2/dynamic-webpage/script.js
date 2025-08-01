document.addEventListener('DOMContentLoaded', function() {
    const loadXmlBtn = document.getElementById('loadXml');
    const loadJsonBtn = document.getElementById('loadJson');
    const loadFetchBtn = document.getElementById('loadFetch');
    const searchInput = document.getElementById('searchInput');
    const filterSelect = document.getElementById('filterSelect');
    const bookContainer = document.getElementById('bookContainer');
    const loadingIndicator = document.getElementById('loadingIndicator');
    const errorContainer = document.getElementById('errorContainer');
    
    let books = [];
    
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
    
    // 1. Traditional AJAX for XML
    loadXmlBtn.addEventListener('click', function() {
        showLoading();
        
        const xhr = new XMLHttpRequest();
        xhr.open('GET', 'data.xml', true);
        
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                hideLoading();
                
                if (xhr.status === 200) {
                    try {
                        const xmlDoc = xhr.responseXML;
                        const bookNodes = xmlDoc.querySelectorAll('book');
                        
                        books = Array.from(bookNodes).map(book => ({
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
                        populateGenreFilter(books);
                        displayBooks(books);
                    } catch (error) {
                        showError('Error parsing XML data: ' + error.message);
                    }
                } else {
                    showError('Error loading XML: ' + xhr.statusText);
                }
            }
        };
        
        xhr.onerror = function() {
            hideLoading();
            showError('Network error while loading XML');
        };
        
        xhr.send();
    });
    
    // 2. Traditional AJAX for JSON
    loadJsonBtn.addEventListener('click', function() {
        showLoading();
        
        const xhr = new XMLHttpRequest();
        xhr.open('GET', 'data.json', true);
        xhr.setRequestHeader('Accept', 'application/json');
        
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                hideLoading();
                
                if (xhr.status === 200) {
                    try {
                        const data = JSON.parse(xhr.responseText);
                        books = data.books;
                        populateGenreFilter(books);
                        displayBooks(books);
                    } catch (error) {
                        showError('Error parsing JSON data: ' + error.message);
                    }
                } else {
                    showError('Error loading JSON: ' + xhr.statusText);
                }
            }
        };
        
        xhr.onerror = function() {
            hideLoading();
            showError('Network error while loading JSON');
        };
        
        xhr.send();
    });
    
    // 3. Modern Fetch API implementation (for comparison)
    loadFetchBtn.addEventListener('click', function() {
        showLoading();
        
        fetch('data.json')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                books = data.books;
                populateGenreFilter(books);
                displayBooks(books);
            })
            .catch(error => {
                showError('Error with Fetch API: ' + error.message);
            })
            .finally(() => {
                hideLoading();
            });
    });
    
    // Search functionality
    searchInput.addEventListener('input', function() {
        const searchTerm = this.value.toLowerCase();
        const filteredBooks = books.filter(book => 
            book.title.toLowerCase().includes(searchTerm) || 
            book.author.toLowerCase().includes(searchTerm) ||
            book.description.toLowerCase().includes(searchTerm)
        );
        displayBooks(filteredBooks);
    });
    
    // Filter by genre
    filterSelect.addEventListener('change', function() {
        const genre = this.value;
        if (genre === 'all') {
            displayBooks(books);
        } else {
            const filteredBooks = books.filter(book => book.genre === genre);
            displayBooks(filteredBooks);
        }
    });
    
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

function populateGenreFilter(books) {
    const filterSelect = document.getElementById('filterSelect');
    
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

});