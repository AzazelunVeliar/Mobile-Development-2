package ru.mirea.khudyakovma.movieproject.data.repository;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.mirea.khudyakovma.movieproject.data.local.AppDatabase;
import ru.mirea.khudyakovma.movieproject.data.local.AuthPrefs;
import ru.mirea.khudyakovma.movieproject.data.local.FavoriteMovieDao;
import ru.mirea.khudyakovma.movieproject.data.local.FavoriteMovieEntity;
import ru.mirea.khudyakovma.movieproject.data.network.DummyJsonApi;
import ru.mirea.khudyakovma.movieproject.data.network.NetworkApi;
import ru.mirea.khudyakovma.movieproject.domain.models.Movie;
import ru.mirea.khudyakovma.movieproject.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {

    public interface BoolCallback {
        void onResult(boolean value);
    }

    private final DummyJsonApi api;
    private final FavoriteMovieDao favoriteDao;
    private final AuthPrefs authPrefs;
    private final ExecutorService dbExecutor = Executors.newSingleThreadExecutor();

    public MovieRepositoryImpl(Context context) {
        api = new NetworkApi();

        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "movie_db")
                .fallbackToDestructiveMigration()
                .build();
        favoriteDao = db.favoriteMovieDao();

        authPrefs = new AuthPrefs(context.getApplicationContext());
    }

    @Override
    public List<Movie> fetchMovies() {
        return api.getMovies();
    }

    @Override
    public Movie getMovieById(int id) {
        Movie m = api.getMovieById(id);
        if (m == null) throw new IllegalArgumentException("Movie not found: " + id);
        return m;
    }

    @Override
    public void addToFavorites(Movie movie) {
        dbExecutor.execute(() -> favoriteDao.upsert(new FavoriteMovieEntity(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getImageUrl(),
                movie.getPrice()
        )));
    }

    @Override
    public void removeFromFavorites(int id) {
        dbExecutor.execute(() -> favoriteDao.deleteById(id));
    }

    @Override
    public List<Movie> getFavorites() {
        List<FavoriteMovieEntity> entities = favoriteDao.getAll();
        List<Movie> result = new ArrayList<>();
        for (FavoriteMovieEntity e : entities) {
            result.add(new Movie(e.id, e.title, e.description, e.imageUrl, e.price));
        }
        return result;
    }

    public void isFavoriteAsync(int id, BoolCallback callback) {
        dbExecutor.execute(() -> callback.onResult(favoriteDao.getById(id) != null));
    }

    @Override
    public boolean isAuthorized() {
        return authPrefs.isAuthorized();
    }

    @Override
    public String getUserName() {
        return authPrefs.getUserName();
    }

    @Override
    public void login(String userName) {
        authPrefs.login(userName);
    }

    @Override
    public void logout() {
        authPrefs.logout();
    }
}
