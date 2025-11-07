package ru.mirea.khudyakovma.data.storage;

import android.content.Context;
import android.content.SharedPreferences;
import ru.mirea.khudyakovma.data.storage.models.Movie;
import java.time.LocalDate;

public class SharedPrefMovieStorage implements MovieStorage {
    private static final String PREF_NAME = "movies_pref";
    private static final String KEY_ID = "movie_id";
    private static final String KEY_NAME = "movie_name";
    private static final String KEY_DATE = "movie_date";

    private final SharedPreferences sharedPreferences;

    public SharedPrefMovieStorage(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean save(Movie movie) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, movie.getId());
        editor.putString(KEY_NAME, movie.getName());
        editor.putString(KEY_DATE, movie.getLocalDate());
        return editor.commit();
    }

    @Override
    public Movie get() {
        int id = sharedPreferences.getInt(KEY_ID, -1);
        String name = sharedPreferences.getString(KEY_NAME, "No movie saved");
        String localDate = sharedPreferences.getString(KEY_DATE, LocalDate.now().toString());
        return new Movie(id, name, localDate);
    }
}