package ru.mirea.khudyakovma.movieproject.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import ru.mirea.khudyakovma.movieproject.R;
import ru.mirea.khudyakovma.movieproject.databinding.FragmentMoviesBinding;
import ru.mirea.khudyakovma.movieproject.presentation.MovieAdapter;
import ru.mirea.khudyakovma.movieproject.presentation.MovieViewModel;

public class MoviesFragment extends Fragment {

    private FragmentMoviesBinding binding;
    private MovieViewModel viewModel;
    private MovieAdapter adapter;

    public MoviesFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMoviesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);

        adapter = new MovieAdapter(movie -> {
            Bundle args = new Bundle();
            args.putInt("movieId", movie.getId());
            Navigation.findNavController(view).navigate(R.id.action_moviesFragment_to_movieDetailsFragment, args);
        });

        binding.recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recycler.setAdapter(adapter);

        binding.swipeRefresh.setOnRefreshListener(() -> viewModel.loadMovies());

        viewModel.getMovies().observe(getViewLifecycleOwner(), adapter::submitList);

        viewModel.getLoading().observe(getViewLifecycleOwner(), isLoading -> {
            binding.progress.setVisibility(Boolean.TRUE.equals(isLoading) ? View.VISIBLE : View.GONE);
            binding.swipeRefresh.setRefreshing(Boolean.TRUE.equals(isLoading));
        });

        viewModel.getError().observe(getViewLifecycleOwner(), err -> {
            if (err != null && !err.isEmpty()) Toast.makeText(requireContext(), err, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
