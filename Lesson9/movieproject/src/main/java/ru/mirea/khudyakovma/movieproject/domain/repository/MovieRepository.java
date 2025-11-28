package ru.mirea.khudyakovma.movieproject.domain.repository;

import java.util.List;

import ru.mirea.khudyakovma.movieproject.domain.models.Movie;

public interface MovieRepository {
    boolean saveMovie(Movie movie);
    Movie getMovie();
    List<Movie> getMovies();
}
