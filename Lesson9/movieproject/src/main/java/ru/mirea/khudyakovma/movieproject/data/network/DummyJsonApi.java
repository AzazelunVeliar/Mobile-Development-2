package ru.mirea.khudyakovma.movieproject.data.network;

import java.util.List;
import ru.mirea.khudyakovma.movieproject.domain.models.Movie;

public interface DummyJsonApi {
    List<Movie> getMovies();
    Movie getMovieById(int id);
}
