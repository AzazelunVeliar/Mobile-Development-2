package ru.mirea.khudyakovma.domain.usecases;

import ru.mirea.khudyakovma.domain.repository.MovieRepository;
import ru.mirea.khudyakovma.domain.models.Movie;


public class SaveMovieToFavoriteUseCase {
    private MovieRepository movieRepository;

    public SaveMovieToFavoriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public boolean execute(Movie movie) {
        return movieRepository.saveMovie(movie);
    }
}