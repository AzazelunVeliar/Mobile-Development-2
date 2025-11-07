package ru.mirea.khudyakovma.plantpal.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.ExecutorService;

import ru.mirea.khudyakovma.domain.models.Weather;
import ru.mirea.khudyakovma.domain.usecases.GetWeatherUseCase;

public class WeatherViewModel extends ViewModel {
    private final GetWeatherUseCase getWeatherUseCase;
    private final ExecutorService executor;

    private final MutableLiveData<Long> refreshTrigger = new MutableLiveData<>(0L);
    private final MutableLiveData<Weather> data = new MutableLiveData<>();
    private final MediatorLiveData<Weather> weather = new MediatorLiveData<>();

    public WeatherViewModel(GetWeatherUseCase getWeatherUseCase, ExecutorService executor) {
        this.getWeatherUseCase = getWeatherUseCase;
        this.executor = executor;
        weather.addSource(data, weather::setValue);
        weather.addSource(refreshTrigger, t -> fetch());
        fetch();
    }

    public LiveData<Weather> getWeather() { return weather; }

    public void refresh() {
        refreshTrigger.setValue(System.currentTimeMillis());
    }

    private void fetch() {
        executor.execute(() -> {
            Weather w = getWeatherUseCase.execute();
            data.postValue(w);
        });
    }
}
