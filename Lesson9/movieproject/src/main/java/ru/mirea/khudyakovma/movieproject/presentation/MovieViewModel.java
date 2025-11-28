package ru.mirea.khudyakovma.movieproject.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.mirea.khudyakovma.movieproject.data.repository.MovieRepositoryImpl;
import ru.mirea.khudyakovma.movieproject.domain.models.Movie;
import ru.mirea.khudyakovma.movieproject.domain.repository.MovieRepository;

public class MovieViewModel extends ViewModel {

    private final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();

    public MovieViewModel() {
        MovieRepository repository = new MovieRepositoryImpl();
        movies.setValue(repository.getMovies());
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }
}
