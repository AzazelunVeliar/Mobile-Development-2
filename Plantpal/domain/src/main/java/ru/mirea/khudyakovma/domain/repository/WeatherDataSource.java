package ru.mirea.khudyakovma.domain.repository;

import ru.mirea.khudyakovma.domain.models.Weather;

public interface WeatherDataSource {
    Weather getCurrentWeather();
    Weather getWeatherForCity(String cityName);
    Weather getWeatherForLocation(double latitude, double longitude);

    interface WeatherCallback {
        void onSuccess(Weather weather);
        void onError(String errorMessage);
    }

    void getCurrentWeatherAsync(WeatherCallback callback);
}