package ru.mirea.khudyakovma.movieproject.domain.usecases;

import ru.mirea.khudyakovma.movieproject.domain.models.Movie;
import ru.mirea.khudyakovma.movieproject.domain.repository.MovieRepository;

public class AddFavoriteUseCase {
    private final MovieRepository repository;

    public AddFavoriteUseCase(MovieRepository repository) {
        this.repository = repository;
    }

    public void execute(Movie movie) {
        repository.addToFavorites(movie);
    }
}
