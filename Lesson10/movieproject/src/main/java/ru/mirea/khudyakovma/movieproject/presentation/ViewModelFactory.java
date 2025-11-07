package ru.mirea.khudyakovma.movieproject.presentation;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import ru.mirea.khudyakovma.data.repository.MovieRepositoryImpl;
import ru.mirea.khudyakovma.data.storage.MovieStorage;
import ru.mirea.khudyakovma.data.storage.SharedPrefMovieStorage;
import ru.mirea.khudyakovma.domain.repository.MovieRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Context appContext;

    public ViewModelFactory(Context context) {
        this.appContext = context.getApplicationContext();
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        MovieStorage storage = new SharedPrefMovieStorage(appContext);
        MovieRepository repository = new MovieRepositoryImpl(storage);
        return (T) new MainViewModel(repository);
    }
}
