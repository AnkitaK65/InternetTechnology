package unit3.consoleBased;

public class Book {
    private String id;
    private String author;
    private String title;
    private String genre;
    private double price;
    private String publishDate;
    private String description;
    private double rating;
    private int pages;
    private String language;

    // Constructor
    public Book(String id, String author, String title, String genre, double price,
                String publishDate, String description, double rating, int pages, String language) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publishDate = publishDate;
        this.description = description;
        this.rating = rating;
        this.pages = pages;
        this.language = language;
    }

    // Getters
    public String getId() { return id; }
    public String getAuthor() { return author; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public double getPrice() { return price; }
    public String getPublishDate() { return publishDate; }
    public String getDescription() { return description; }
    public double getRating() { return rating; }
    public int getPages() { return pages; }
    public String getLanguage() { return language; }
}

