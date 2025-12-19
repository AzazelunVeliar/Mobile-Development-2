package ru.mirea.khudyakovma.movieproject.domain.usecases;

import ru.mirea.khudyakovma.movieproject.domain.repository.MovieRepository;

public class LogoutUseCase {
    private final MovieRepository repository;

    public LogoutUseCase(MovieRepository repository) {
        this.repository = repository;
    }

    public void execute() {
        repository.logout();
    }
}
