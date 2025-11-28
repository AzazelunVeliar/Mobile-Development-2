package ru.mirea.khudyakovma.movieproject.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.mirea.khudyakovma.movieproject.R;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel viewModel;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovieAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        viewModel.getMovies().observe(this, movies -> adapter.setItems(movies));
    }
}
