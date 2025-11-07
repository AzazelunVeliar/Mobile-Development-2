package ru.mirea.khudyakovma.movieproject.presentation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import ru.mirea.khudyakovma.movieproject.R;

public class MainActivity extends AppCompatActivity {
    private EditText editTextMovie;
    private TextView textViewMovie;
    private TextView textViewSaveResult;
    private TextView textViewMergedTitle;
    private Button buttonSave, buttonGet;
    private MainViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMovie = findViewById(R.id.editTextMovie);
        textViewMovie = findViewById(R.id.textViewMovie);
        textViewSaveResult = findViewById(R.id.textViewSaveResult);
        textViewMergedTitle = findViewById(R.id.textViewMergedTitle);
        buttonSave = findViewById(R.id.buttonSaveMovie);
        buttonGet = findViewById(R.id.buttonGetMovie);

        vm = new ViewModelProvider(this, new ViewModelFactory(this))
                .get(MainViewModel.class);

        vm.getFavoriteMovie().observe(this, textViewMovie::setText);
        if (textViewSaveResult != null) vm.getSaveResult().observe(this, textViewSaveResult::setText);
        if (textViewMergedTitle != null) vm.getMergedTitle().observe(this, textViewMergedTitle::setText);

        buttonSave.setOnClickListener(v -> vm.saveMovie(editTextMovie.getText().toString()));
        buttonGet.setOnClickListener(v -> vm.loadFavoriteMovie());

        vm.mockLoadFromDb("Title from DB");
        vm.mockLoadFromNet("Title from Network");
    }
}
