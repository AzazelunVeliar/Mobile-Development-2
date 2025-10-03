package ru.mirea.khudyakovma.movieproject.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.khudyakovma.movieproject.R;
import ru.mirea.khudyakovma.movieproject.domain.models.Movie;
import ru.mirea.khudyakovma.movieproject.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.khudyakovma.movieproject.domain.usecases.SaveMovieToFavoriteUseCase;
import ru.mirea.khudyakovma.movieproject.data.repository.MovieRepositoryImpl;

public class MainActivity extends AppCompatActivity {
    private EditText editTextMovie;
    private TextView textViewMovie;
    private Button buttonSave, buttonGet;

    private MovieRepositoryImpl movieRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMovie = findViewById(R.id.editTextMovie);
        textViewMovie = findViewById(R.id.textViewMovie);
        buttonSave = findViewById(R.id.buttonSaveMovie);
        buttonGet = findViewById(R.id.buttonGetMovie);

        movieRepository = new MovieRepositoryImpl(this);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = new SaveMovieToFavoriteUseCase(movieRepository)
                        .execute(new Movie(1, editTextMovie.getText().toString()));
                textViewMovie.setText("Save result: " + result);
            }
        });

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie = new GetFavoriteFilmUseCase(movieRepository).execute();
                textViewMovie.setText("Favorite: " + movie.getName());
            }
        });
    }
}
