package ru.mirea.khudyakovma.data.datasource;

import ru.mirea.khudyakovma.data.api.WeatherApi;
import ru.mirea.khudyakovma.data.api.WeatherResponse;
import ru.mirea.khudyakovma.domain.repository.WeatherDataSource;
import ru.mirea.khudyakovma.domain.models.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteWeatherDataSource implements WeatherDataSource {
    private final WeatherApi weatherApi;
    private final String apiKey;

    public RemoteWeatherDataSource(WeatherApi weatherApi, String apiKey) {
        this.weatherApi = weatherApi;
        this.apiKey = apiKey;
    }

    @Override
    public Weather getCurrentWeather() {
        try {
            Call<WeatherResponse> call = weatherApi.getWeather(
                    "Moscow", apiKey, "metric", "ru"
            );
            Response<WeatherResponse> response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                return mapToDomain(response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Weather("Неизвестно", 0.0);
    }

    @Override
    public Weather getWeatherForCity(String cityName) {
        try {
            Call<WeatherResponse> call = weatherApi.getWeather(
                    cityName, apiKey, "metric", "ru"
            );
            Response<WeatherResponse> response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                return mapToDomain(response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Weather("Неизвестно", 0.0);
    }

    @Override
    public Weather getWeatherForLocation(double latitude, double longitude) {
        try {
            Call<WeatherResponse> call = weatherApi.getWeatherByCoordinates(
                    latitude, longitude, apiKey, "metric", "ru"
            );
            Response<WeatherResponse> response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                return mapToDomain(response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Weather("Неизвестно", 0.0);
    }

    @Override
    public void getCurrentWeatherAsync(WeatherCallback callback) {
        Call<WeatherResponse> call = weatherApi.getWeather(
                "Moscow", apiKey, "metric", "ru"
        );

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Weather weather = mapToDomain(response.body());
                    callback.onSuccess(weather);
                } else {
                    callback.onError("Ошибка получения данных о погоде");
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    private Weather mapToDomain(WeatherResponse response) {
        String condition = response.weather != null && !response.weather.isEmpty()
                ? response.weather.get(0).description
                : "Неизвестно";

        double temperature = response.main != null ? response.main.temp : 0.0;

        return new Weather(condition, temperature);
    }
}