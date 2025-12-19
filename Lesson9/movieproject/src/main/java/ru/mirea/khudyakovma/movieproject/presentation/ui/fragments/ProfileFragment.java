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

import ru.mirea.khudyakovma.movieproject.R;
import ru.mirea.khudyakovma.movieproject.databinding.FragmentProfileBinding;
import ru.mirea.khudyakovma.movieproject.presentation.MovieViewModel;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private MovieViewModel viewModel;

    public ProfileFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);

        viewModel.getAuthorized().observe(getViewLifecycleOwner(), auth -> render());
        viewModel.getUserName().observe(getViewLifecycleOwner(), name -> render());

        binding.loginButton.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.loginFragment));
        binding.logoutButton.setOnClickListener(v -> viewModel.logout());
    }

    private void render() {
        boolean auth = Boolean.TRUE.equals(viewModel.getAuthorized().getValue());
        String name = viewModel.getUserName().getValue();
        binding.status.setText(auth ? "Статус: Авторизован" : "Статус: Гость");
        binding.student.setText("Студент: №26 (БСБО-09-22)");
        binding.userName.setText(auth ? ("Пользователь: " + (name == null ? "" : name)) : "Пользователь: -");
        binding.loginButton.setVisibility(auth ? View.GONE : View.VISIBLE);
        binding.logoutButton.setVisibility(auth ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (viewModel != null) viewModel.refreshAuth();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
