package ru.mirea.khudyakovma.movieproject.data.repository;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.khudyakovma.movieproject.domain.models.Movie;
import ru.mirea.khudyakovma.movieproject.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {

    private final List<Movie> movies = new ArrayList<>();

    public MovieRepositoryImpl() {
        movies.add(new Movie(1, "Interstellar"));
        movies.add(new Movie(2, "Inception"));
        movies.add(new Movie(3, "The Matrix"));
        movies.add(new Movie(4, "The Lord of the Rings"));
        movies.add(new Movie(5, "Fight Club"));
        movies.add(new Movie(6, "Pulp Fiction"));
        movies.add(new Movie(7, "The Dark Knight"));
        movies.add(new Movie(8, "Forrest Gump"));
        movies.add(new Movie(9, "The Shawshank Redemption"));
        movies.add(new Movie(10, "Blade Runner 2049"));
    }

    @Override
    public boolean saveMovie(Movie movie) {
        return movies.add(movie);
    }

    @Override
    public Movie getMovie() {
        if (movies.isEmpty()) {
            return new Movie(-1, "No movie");
        }
        return movies.get(0);
    }

    @Override
    public List<Movie> getMovies() {
        return new ArrayList<>(movies);
    }
}
