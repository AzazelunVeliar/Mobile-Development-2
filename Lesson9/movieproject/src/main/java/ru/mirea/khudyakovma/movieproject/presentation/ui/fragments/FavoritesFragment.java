package ru.mirea.khudyakovma.movieproject.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import ru.mirea.khudyakovma.movieproject.R;
import ru.mirea.khudyakovma.movieproject.databinding.FragmentFavoritesBinding;
import ru.mirea.khudyakovma.movieproject.presentation.MovieAdapter;
import ru.mirea.khudyakovma.movieproject.presentation.MovieViewModel;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    private MovieViewModel viewModel;
    private MovieAdapter adapter;

    public FavoritesFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);

        adapter = new MovieAdapter(movie -> {
            Bundle args = new Bundle();
            args.putInt("movieId", movie.getId());
            Navigation.findNavController(view).navigate(R.id.action_favoritesFragment_to_movieDetailsFragment, args);
        });

        binding.recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recycler.setAdapter(adapter);

        binding.swipeRefresh.setOnRefreshListener(() -> viewModel.loadFavorites());

        viewModel.getFavorites().observe(getViewLifecycleOwner(), adapter::submitList);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (viewModel != null) viewModel.loadFavorites();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
