package ru.mirea.khudyakovma.movieproject.domain.usecases;

import ru.mirea.khudyakovma.movieproject.domain.repository.MovieRepository;

public class LoginUseCase {
    private final MovieRepository repository;

    public LoginUseCase(MovieRepository repository) {
        this.repository = repository;
    }

    public void execute(String userName) {
        repository.login(userName);
    }
}
