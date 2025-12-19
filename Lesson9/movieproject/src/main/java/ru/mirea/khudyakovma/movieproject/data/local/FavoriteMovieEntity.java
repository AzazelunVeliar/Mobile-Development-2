package ru.mirea.khudyakovma.movieproject.data.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_movies")
public class FavoriteMovieEntity {
    @PrimaryKey
    public int id;

    public String title;
    public String description;
    public String imageUrl;
    public double price;

    public FavoriteMovieEntity(int id, String title, String description, String imageUrl, double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }
}
