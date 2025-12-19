package ru.mirea.khudyakovma.movieproject.domain.repository;

import java.util.List;
import ru.mirea.khudyakovma.movieproject.domain.models.Movie;

public interface MovieRepository {
    List<Movie> fetchMovies() throws Exception;
    Movie getMovieById(int id) throws Exception;

    void addToFavorites(Movie movie);
    void removeFromFavorites(int id);
    List<Movie> getFavorites();

    boolean isAuthorized();
    String getUserName();
    void login(String userName);
    void logout();
}
