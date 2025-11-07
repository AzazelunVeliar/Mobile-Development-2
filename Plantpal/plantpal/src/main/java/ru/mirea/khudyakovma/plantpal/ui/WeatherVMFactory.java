package ru.mirea.khudyakovma.plantpal.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.ExecutorService;

import ru.mirea.khudyakovma.domain.usecases.GetWeatherUseCase;

public class WeatherVMFactory implements ViewModelProvider.Factory {
    private final GetWeatherUseCase getWeatherUseCase;
    private final ExecutorService executor;

    public WeatherVMFactory(GetWeatherUseCase getWeatherUseCase, ExecutorService executor) {
        this.getWeatherUseCase = getWeatherUseCase;
        this.executor = executor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WeatherViewModel(getWeatherUseCase, executor);
    }
}
