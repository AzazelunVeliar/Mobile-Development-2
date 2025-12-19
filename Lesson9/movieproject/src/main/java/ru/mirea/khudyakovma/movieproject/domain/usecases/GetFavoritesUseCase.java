package ru.mirea.khudyakovma.movieproject.domain.usecases;

import java.util.List;
import ru.mirea.khudyakovma.movieproject.domain.models.Movie;
import ru.mirea.khudyakovma.movieproject.domain.repository.MovieRepository;

public class GetFavoritesUseCase {
    private final MovieRepository repository;

    public GetFavoritesUseCase(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> execute() {
        return repository.getFavorites();
    }
}
