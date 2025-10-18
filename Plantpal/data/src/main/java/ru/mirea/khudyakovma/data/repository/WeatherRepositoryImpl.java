package ru.mirea.khudyakovma.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.mirea.khudyakovma.data.api.NetworkClient;
import ru.mirea.khudyakovma.data.datasource.RemoteWeatherDataSource;
import ru.mirea.khudyakovma.domain.repository.WeatherRepository;

public class WeatherRepositoryImpl implements WeatherRepository {
    private final RemoteWeatherDataSource remoteDataSource;
    private final SharedPreferences sharedPreferences;
    private final ExecutorService executor;

    private static final String PREFS_NAME = "weather_preferences";
    private static final String KEY_LAST_WEATHER_CONDITION = "last_weather_condition";
    private static final String KEY_LAST_WEATHER_TEMP = "last_weather_temp";
    private static final String KEY_LAST_UPDATE_TIME = "last_update_time";
    private static final long CACHE_DURATION = 30 * 60 * 1000;

    public WeatherRepositoryImpl(Context context) {
        this.remoteDataSource = new RemoteWeatherDataSource(
                NetworkClient.getWeatherApi(),
                "будет какое-то апи"
        );
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public ru.mirea.khudyakovma.domain.models.Weather getWeather() {
        if (isCacheValid()) {
            ru.mirea.khudyakovma.domain.models.Weather cachedWeather = getWeatherFromCache();
            if (cachedWeather != null) {
                return cachedWeather;
            }
        }

        try {
            ru.mirea.khudyakovma.domain.models.Weather freshWeather = remoteDataSource.getCurrentWeather();
            if (freshWeather != null) {
                saveWeatherToCache(freshWeather);
                return freshWeather;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getWeatherFromCache();
    }

    public void getWeatherAsync(WeatherCallback callback) {
        executor.execute(() -> {
            ru.mirea.khudyakovma.domain.models.Weather weather = getWeather();
            callback.onWeatherLoaded(weather);
        });
    }

    public interface WeatherCallback {
        void onWeatherLoaded(ru.mirea.khudyakovma.domain.models.Weather weather);
    }


    private boolean isCacheValid() {
        long lastUpdate = sharedPreferences.getLong(KEY_LAST_UPDATE_TIME, 0);
        return (System.currentTimeMillis() - lastUpdate) < CACHE_DURATION;
    }

    private ru.mirea.khudyakovma.domain.models.Weather getWeatherFromCache() {
        String condition = sharedPreferences.getString(KEY_LAST_WEATHER_CONDITION, "Солнечно");
        float temperature = sharedPreferences.getFloat(KEY_LAST_WEATHER_TEMP, 22.5f);
        return new ru.mirea.khudyakovma.domain.models.Weather(condition, temperature);
    }

    private void saveWeatherToCache(ru.mirea.khudyakovma.domain.models.Weather weather) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LAST_WEATHER_CONDITION, weather.getForecast());
        editor.putFloat(KEY_LAST_WEATHER_TEMP, (float) weather.getTemperature());
        editor.putLong(KEY_LAST_UPDATE_TIME, System.currentTimeMillis());
        editor.apply();
    }

    public void clearCache() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_LAST_WEATHER_CONDITION);
        editor.remove(KEY_LAST_WEATHER_TEMP);
        editor.remove(KEY_LAST_UPDATE_TIME);
        editor.apply();
    }

    public long getLastUpdateTime() {
        return sharedPreferences.getLong(KEY_LAST_UPDATE_TIME, 0);
    }
}