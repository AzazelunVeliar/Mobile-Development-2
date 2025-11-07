package ru.mirea.khudyakovma.plantpal.ui;

import android.content.Context;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.mirea.khudyakovma.data.dao.PlantDao;
import ru.mirea.khudyakovma.data.database.AppDatabase;
import ru.mirea.khudyakovma.data.datasource.LocalPlantDataSource;
import ru.mirea.khudyakovma.data.repository.PlantRepositoryImpl;
import ru.mirea.khudyakovma.data.repository.WeatherRepositoryImpl;
import ru.mirea.khudyakovma.domain.models.Plant;
import ru.mirea.khudyakovma.domain.models.Weather;
import ru.mirea.khudyakovma.domain.repository.PlantRepository;
import ru.mirea.khudyakovma.domain.repository.WeatherRepository;
import ru.mirea.khudyakovma.domain.usecases.GetMyPlantsUseCase;
import ru.mirea.khudyakovma.domain.usecases.GetWeatherUseCase;

public class MainViewModel extends ViewModel {

    private final GetMyPlantsUseCase getMyPlantsUseCase;
    private final GetWeatherUseCase getWeatherUseCase;

    private final MutableLiveData<List<Plant>> plants = new MutableLiveData<>();
    private final MutableLiveData<String> weatherLocal = new MutableLiveData<>();
    private final MutableLiveData<String> weatherRemote = new MutableLiveData<>();
    private final MediatorLiveData<String> weatherMerged = new MediatorLiveData<>();

    public MainViewModel(GetMyPlantsUseCase getMyPlantsUseCase, GetWeatherUseCase getWeatherUseCase) {
        this.getMyPlantsUseCase = getMyPlantsUseCase;
        this.getWeatherUseCase = getWeatherUseCase;
        weatherMerged.addSource(weatherLocal, v -> recomputeWeather());
        weatherMerged.addSource(weatherRemote, v -> recomputeWeather());
    }

    public MutableLiveData<List<Plant>> getPlants() {
        return plants;
    }

    public MediatorLiveData<String> getWeatherMerged() {
        return weatherMerged;
    }

    public void loadPlants() {
        List<Plant> list = getMyPlantsUseCase.execute();
        plants.setValue(list);
    }

    public void loadWeather() {
        Weather w = getWeatherUseCase.execute();
        if (w != null) weatherRemote.setValue("NET: " + format(w));
    }

    public void loadWeatherFromApi(double lat, double lon) {
        loadWeather();
    }

    public void setWeatherLocalCache(String text) {
        weatherLocal.setValue("DB: " + text);
    }

    private void recomputeWeather() {
        String l = weatherLocal.getValue();
        String r = weatherRemote.getValue();
        if (l == null && r == null) {
            weatherMerged.setValue("");
            return;
        }
        if (l != null && r != null) {
            weatherMerged.setValue(l + " | " + r);
            return;
        }
        if (l != null) {
            weatherMerged.setValue(l);
            return;
        }
        weatherMerged.setValue(r);
    }

    private String format(Weather w) {
        return w.getForecast() + ", " + w.getTemperature() + "°C";
    }

    public static MainViewModel create(android.content.Context context) {
        android.content.Context app = context.getApplicationContext();

        ru.mirea.khudyakovma.domain.repository.PlantRepository plantRepo =
                ru.mirea.khudyakovma.data.repository.PlantRepositoryImpl.create(app);
        ru.mirea.khudyakovma.domain.usecases.GetMyPlantsUseCase plants =
                new ru.mirea.khudyakovma.domain.usecases.GetMyPlantsUseCase(plantRepo);

        ru.mirea.khudyakovma.domain.repository.WeatherRepository weatherRepo =
                new ru.mirea.khudyakovma.data.repository.WeatherRepositoryImpl(app);
        ru.mirea.khudyakovma.domain.usecases.GetWeatherUseCase weather =
                new ru.mirea.khudyakovma.domain.usecases.GetWeatherUseCase(weatherRepo);

        return new MainViewModel(plants, weather);
    }

}
