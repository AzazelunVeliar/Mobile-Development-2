package ru.mirea.khudyakovma.movieproject.domain.usecases;

import ru.mirea.khudyakovma.movieproject.domain.repository.MovieRepository;

public class RemoveFavoriteUseCase {
    private final MovieRepository repository;

    public RemoveFavoriteUseCase(MovieRepository repository) {
        this.repository = repository;
    }

    public void execute(int id) {
        repository.removeFromFavorites(id);
    }
}
