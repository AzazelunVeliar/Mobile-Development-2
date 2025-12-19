package ru.mirea.khudyakovma.movieproject.domain.usecases;

import java.util.List;
import ru.mirea.khudyakovma.movieproject.domain.models.Movie;
import ru.mirea.khudyakovma.movieproject.domain.repository.MovieRepository;

public class GetMoviesUseCase {
    private final MovieRepository repository;

    public GetMoviesUseCase(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> execute() throws Exception {
        return repository.fetchMovies();
    }
}
