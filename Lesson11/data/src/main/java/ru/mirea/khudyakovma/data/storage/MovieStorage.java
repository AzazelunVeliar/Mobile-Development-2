package ru.mirea.khudyakovma.data.storage;

import ru.mirea.khudyakovma.data.storage.models.Movie;

public interface MovieStorage {
    public Movie get();
    public boolean save(Movie movie);
}