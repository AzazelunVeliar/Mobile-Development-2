package ru.mirea.khudyakovma.movieproject.presentation;

import android.util.Log;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import ru.mirea.khudyakovma.domain.models.Movie;
import ru.mirea.khudyakovma.domain.repository.MovieRepository;
import ru.mirea.khudyakovma.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.khudyakovma.domain.usecases.SaveMovieToFavoriteUseCase;

public class MainViewModel extends ViewModel {
    private final MovieRepository movieRepository;
    private final MutableLiveData<String> favoriteMovie = new MutableLiveData<>();
    private final MutableLiveData<String> saveResult = new MutableLiveData<>();
    private final MutableLiveData<String> dbTitle = new MutableLiveData<>();
    private final MutableLiveData<String> netTitle = new MutableLiveData<>();
    private final MediatorLiveData<String> mergedTitle = new MediatorLiveData<>();

    public MainViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        Log.d("MainViewModel", "created");
        mergedTitle.addSource(dbTitle, v -> recomputeMerged());
        mergedTitle.addSource(netTitle, v -> recomputeMerged());
    }

    public MutableLiveData<String> getFavoriteMovie() { return favoriteMovie; }
    public MutableLiveData<String> getSaveResult() { return saveResult; }
    public MediatorLiveData<String> getMergedTitle() { return mergedTitle; }

    public void saveMovie(String name) {
        boolean result = new SaveMovieToFavoriteUseCase(movieRepository)
                .execute(new Movie(1, name == null ? "" : name.trim()));
        saveResult.setValue("Save result: " + result);
    }

    public void loadFavoriteMovie() {
        Movie movie = new GetFavoriteFilmUseCase(movieRepository).execute();
        String text = movie != null && movie.getName() != null && !movie.getName().isEmpty()
                ? "Favorite: " + movie.getName()
                : "Favorite: not set";
        favoriteMovie.setValue(text);
    }

    public void mockLoadFromDb(String title) { dbTitle.setValue(title); }
    public void mockLoadFromNet(String title) { netTitle.setValue(title); }

    private void recomputeMerged() {
        String db = dbTitle.getValue();
        String net = netTitle.getValue();
        if (db == null && net == null) { mergedTitle.setValue(""); return; }
        if (db != null && net != null) { mergedTitle.setValue("DB: " + db + " | NET: " + net); return; }
        if (db != null) { mergedTitle.setValue("DB: " + db); return; }
        mergedTitle.setValue("NET: " + net);
    }

    @Override
    protected void onCleared() {
        Log.d("MainViewModel", "cleared");
        super.onCleared();
    }
}
