package ru.mirea.khudyakovma.plantpal.di;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.mirea.khudyakovma.data.repository.FakePlantRepository;
import ru.mirea.khudyakovma.data.repository.FakeWeatherRepository;
import ru.mirea.khudyakovma.data.repository.UserRepositoryImpl;
import ru.mirea.khudyakovma.domain.repository.PlantRepository;
import ru.mirea.khudyakovma.domain.repository.UserRepository;
import ru.mirea.khudyakovma.domain.repository.WeatherRepository;

public class AppModule {
    private final Context context;
    private final ExecutorService executor;

    public AppModule(Context context) {
        this.context = context;
        this.executor = Executors.newFixedThreadPool(2);
    }

    public PlantRepository providePlantRepository() {
        return new FakePlantRepository();
    }

    public UserRepository provideUserRepository() {
        return new UserRepositoryImpl(context);
    }

    public WeatherRepository provideWeatherRepository() {
        return new FakeWeatherRepository();
    }

    public ExecutorService provideExecutor() {
        return executor;
    }

    public void shutdown() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
        }
    }
}
