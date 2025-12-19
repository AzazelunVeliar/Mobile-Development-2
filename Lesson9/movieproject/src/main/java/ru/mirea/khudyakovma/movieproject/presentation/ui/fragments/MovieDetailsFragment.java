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

import com.bumptech.glide.Glide;

import ru.mirea.khudyakovma.movieproject.databinding.FragmentMovieDetailsBinding;
import ru.mirea.khudyakovma.movieproject.domain.models.Movie;
import ru.mirea.khudyakovma.movieproject.presentation.MovieViewModel;

public class MovieDetailsFragment extends Fragment {

    private FragmentMovieDetailsBinding binding;
    private MovieViewModel viewModel;
    private int movieId = -1;

    public MovieDetailsFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);

        Bundle args = getArguments();
        if (args != null) movieId = args.getInt("movieId", -1);

        binding.progress.setVisibility(View.VISIBLE);
        viewModel.selectMovie(movieId);

        viewModel.getSelectedMovie().observe(getViewLifecycleOwner(), movie -> {
            if (movie == null) return;
            bindMovie(movie);
            viewModel.checkFavorite(movie.getId());
        });

        viewModel.getLoading().observe(getViewLifecycleOwner(), isLoading -> {
            binding.progress.setVisibility(Boolean.TRUE.equals(isLoading) ? View.VISIBLE : View.GONE);
        });

        viewModel.getError().observe(getViewLifecycleOwner(), err -> {
            if (err != null && !err.isEmpty()) Toast.makeText(requireContext(), err, Toast.LENGTH_LONG).show();
        });

        viewModel.getAuthorized().observe(getViewLifecycleOwner(), a -> {
            viewModel.checkFavorite(movieId);
            updateFavoriteButton();
        });

        viewModel.getFavoriteState().observe(getViewLifecycleOwner(), fav -> updateFavoriteButton());

        binding.favoriteButton.setOnClickListener(v -> {
            Movie movie = viewModel.getSelectedMovie().getValue();
            if (movie == null) return;

            if (!Boolean.TRUE.equals(viewModel.getAuthorized().getValue())) {
                Toast.makeText(requireContext(), "Только для авторизованного пользователя", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean fav = Boolean.TRUE.equals(viewModel.getFavoriteState().getValue());
            if (fav) {
                viewModel.removeFromFavorites(movie.getId());
                Toast.makeText(requireContext(), "Удалено из избранного", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.addToFavorites(movie);
                Toast.makeText(requireContext(), "Добавлено в избранное", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindMovie(Movie movie) {
        binding.title.setText(movie.getTitle());
        binding.description.setText(movie.getDescription());
        binding.price.setText("Оценка " + movie.getPrice());
        Glide.with(binding.poster.getContext())
                .load(movie.getImageUrl())
                .centerCrop()
                .into(binding.poster);
    }

    private void updateFavoriteButton() {
        boolean auth = Boolean.TRUE.equals(viewModel.getAuthorized().getValue());
        boolean fav = Boolean.TRUE.equals(viewModel.getFavoriteState().getValue());

        binding.favoriteButton.setEnabled(auth);
        binding.favoriteButton.setText(auth ? (fav ? "Убрать из избранного" : "В избранное") : "Требуется вход");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
