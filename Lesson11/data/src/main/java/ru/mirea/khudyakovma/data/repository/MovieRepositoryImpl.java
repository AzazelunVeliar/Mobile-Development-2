package ru.mirea.khudyakovma.data.repository;

import ru.mirea.khudyakovma.data.storage.MovieStorage;
import ru.mirea.khudyakovma.data.storage.models.Movie;
import ru.mirea.khudyakovma.domain.repository.MovieRepository;
import java.time.LocalDate;

public class MovieRepositoryImpl implements MovieRepository {
    private final MovieStorage movieStorage;

    public MovieRepositoryImpl(MovieStorage movieStorage) {
        this.movieStorage = movieStorage;
    }

    @Override
    public boolean saveMovie(ru.mirea.khudyakovma.domain.models.Movie movie) {
        Movie dataMovie = mapToData(movie);
        return movieStorage.save(dataMovie);
    }

    @Override
    public ru.mirea.khudyakovma.domain.models.Movie getMovie() {
        Movie dataMovie = movieStorage.get();
        return mapToDomain(dataMovie);
    }

    private Movie mapToData(ru.mirea.khudyakovma.domain.models.Movie domainMovie) {
        return new Movie(domainMovie.getId(), domainMovie.getName(), LocalDate.now().toString());
    }

    private ru.mirea.khudyakovma.domain.models.Movie mapToDomain(Movie dataMovie) {
        return new ru.mirea.khudyakovma.domain.models.Movie(dataMovie.getId(), dataMovie.getName());
    }
}