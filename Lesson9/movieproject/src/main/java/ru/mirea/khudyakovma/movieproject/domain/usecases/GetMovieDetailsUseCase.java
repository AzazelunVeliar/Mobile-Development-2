package ru.mirea.khudyakovma.movieproject.domain.usecases;

import ru.mirea.khudyakovma.movieproject.domain.models.Movie;
import ru.mirea.khudyakovma.movieproject.domain.repository.MovieRepository;

public class GetMovieDetailsUseCase {
    private final MovieRepository repository;

    public GetMovieDetailsUseCase(MovieRepository repository) {
        this.repository = repository;
    }

    public Movie execute(int id) throws Exception {
        return repository.getMovieById(id);
    }
}
