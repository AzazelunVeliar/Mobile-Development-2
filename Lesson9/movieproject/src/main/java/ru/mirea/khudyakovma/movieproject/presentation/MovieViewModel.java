package ru.mirea.khudyakovma.movieproject.presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.mirea.khudyakovma.movieproject.data.repository.MovieRepositoryImpl;
import ru.mirea.khudyakovma.movieproject.domain.models.Movie;
import ru.mirea.khudyakovma.movieproject.domain.repository.MovieRepository;
import ru.mirea.khudyakovma.movieproject.domain.usecases.AddFavoriteUseCase;
import ru.mirea.khudyakovma.movieproject.domain.usecases.GetFavoritesUseCase;
import ru.mirea.khudyakovma.movieproject.domain.usecases.GetMovieDetailsUseCase;
import ru.mirea.khudyakovma.movieproject.domain.usecases.GetMoviesUseCase;
import ru.mirea.khudyakovma.movieproject.domain.usecases.LoginUseCase;
import ru.mirea.khudyakovma.movieproject.domain.usecases.LogoutUseCase;
import ru.mirea.khudyakovma.movieproject.domain.usecases.RemoveFavoriteUseCase;

public class MovieViewModel extends AndroidViewModel {

    private final MovieRepositoryImpl repository;
    private final GetMoviesUseCase getMoviesUseCase;
    private final GetMovieDetailsUseCase getMovieDetailsUseCase;
    private final GetFavoritesUseCase getFavoritesUseCase;
    private final AddFavoriteUseCase addFavoriteUseCase;
    private final RemoveFavoriteUseCase removeFavoriteUseCase;
    private final LoginUseCase loginUseCase;
    private final LogoutUseCase logoutUseCase;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private final MutableLiveData<List<Movie>> favorites = new MutableLiveData<>();
    private final MutableLiveData<Movie> selectedMovie = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);
    private final MutableLiveData<String> error = new MutableLiveData<>(null);
    private final MutableLiveData<Boolean> authorized = new MutableLiveData<>(false);
    private final MutableLiveData<String> userName = new MutableLiveData<>("");
    private final MutableLiveData<Boolean> favoriteState = new MutableLiveData<>(false);

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepositoryImpl(application.getApplicationContext());
        MovieRepository repo = repository;

        getMoviesUseCase = new GetMoviesUseCase(repo);
        getMovieDetailsUseCase = new GetMovieDetailsUseCase(repo);
        getFavoritesUseCase = new GetFavoritesUseCase(repo);
        addFavoriteUseCase = new AddFavoriteUseCase(repo);
        removeFavoriteUseCase = new RemoveFavoriteUseCase(repo);
        loginUseCase = new LoginUseCase(repo);
        logoutUseCase = new LogoutUseCase(repo);

        refreshAuth();
        loadMovies();
        loadFavorites();
    }

    public LiveData<List<Movie>> getMovies() { return movies; }
    public LiveData<List<Movie>> getFavorites() { return favorites; }
    public LiveData<Movie> getSelectedMovie() { return selectedMovie; }
    public LiveData<Boolean> getLoading() { return loading; }
    public LiveData<String> getError() { return error; }
    public LiveData<Boolean> getAuthorized() { return authorized; }
    public LiveData<String> getUserName() { return userName; }
    public LiveData<Boolean> getFavoriteState() { return favoriteState; }

    public void loadMovies() {
        loading.postValue(true);
        error.postValue(null);
        executor.execute(() -> {
            try {
                movies.postValue(getMoviesUseCase.execute());
            } catch (Exception e) {
                error.postValue(e.getMessage());
            } finally {
                loading.postValue(false);
            }
        });
    }

    public void loadFavorites() {
        executor.execute(() -> favorites.postValue(getFavoritesUseCase.execute()));
    }

    public void selectMovie(int id) {
        loading.postValue(true);
        error.postValue(null);
        executor.execute(() -> {
            try {
                selectedMovie.postValue(getMovieDetailsUseCase.execute(id));
            } catch (Exception e) {
                error.postValue(e.getMessage());
            } finally {
                loading.postValue(false);
            }
        });
    }

    public void addToFavorites(Movie movie) {
        executor.execute(() -> {
            addFavoriteUseCase.execute(movie);
            loadFavorites();
            checkFavorite(movie.getId());
        });
    }

    public void removeFromFavorites(int id) {
        executor.execute(() -> {
            removeFavoriteUseCase.execute(id);
            loadFavorites();
            checkFavorite(id);
        });
    }

    public void checkFavorite(int id) {
        boolean auth = Boolean.TRUE.equals(authorized.getValue());
        if (!auth) {
            favoriteState.postValue(false);
            return;
        }
        repository.isFavoriteAsync(id, favoriteState::postValue);
    }

    public void login(String name) {
        executor.execute(() -> {
            loginUseCase.execute(name);
            refreshAuth();
        });
    }

    public void logout() {
        executor.execute(() -> {
            logoutUseCase.execute();
            refreshAuth();
        });
    }

    public void refreshAuth() {
        boolean a = repository.isAuthorized();
        authorized.postValue(a);
        userName.postValue(repository.getUserName());
    }
}
