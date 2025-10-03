package ru.mirea.khudyakovma.lesson9.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import ru.mirea.khudyakovma.lesson9.domain.models.Movie;
import ru.mirea.khudyakovma.lesson9.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {
    private static final String PREF_NAME = "movies_pref";
    private static final String KEY_ID = "movie_id";
    private static final String KEY_NAME = "movie_name";

    private final SharedPreferences sharedPreferences;

    public MovieRepositoryImpl(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean saveMovie(Movie movie) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, movie.getId());
        editor.putString(KEY_NAME, movie.getName());
        return editor.commit();
    }

    @Override
    public Movie getMovie() {
        int id = sharedPreferences.getInt(KEY_ID, -1);
        String name = sharedPreferences.getString(KEY_NAME, "No movie saved");
        return new Movie(id, name);
    }
}
