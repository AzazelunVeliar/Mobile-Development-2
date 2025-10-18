package ru.mirea.khudyakovma.domain.repository;

import ru.mirea.khudyakovma.domain.models.Movie;

public interface MovieRepository {

    boolean saveMovie(Movie movie);

    Movie getMovie();
}
