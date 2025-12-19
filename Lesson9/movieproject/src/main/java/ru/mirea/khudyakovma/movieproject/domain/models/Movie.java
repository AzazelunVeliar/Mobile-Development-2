package ru.mirea.khudyakovma.movieproject.domain.models;

public class Movie {
    private final int id;
    private final String title;
    private final String description;
    private final String imageUrl;
    private final double price;

    public Movie(int id, String title, String description, String imageUrl, double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public double getPrice() { return price; }
}
