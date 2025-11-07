package ru.mirea.khudyakovma.domain.usecases;

import ru.mirea.khudyakovma.domain.models.Movie;
import ru.mirea.khudyakovma.domain.repository.MovieRepository;

public class GetFavoriteFilmUseCase {
    private MovieRepository movieRepository;

    public GetFavoriteFilmUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie execute() {
        return movieRepository.getMovie();
    }
}
