package com.example.bookapp.model;

import java.io.Serializable;

public class Book implements Serializable {
    private String id;
    private String author;
    private String title;
    private String genre;
    private double price;
    private String publishDate;
    private double rating;
    private int pages;
    private String language;

    public Book() {}

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getPublishDate() { return publishDate; }
    public void setPublishDate(String publishDate) { this.publishDate = publishDate; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}
