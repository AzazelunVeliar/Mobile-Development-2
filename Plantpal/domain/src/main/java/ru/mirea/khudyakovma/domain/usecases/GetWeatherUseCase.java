package ru.mirea.khudyakovma.domain.usecases;

import ru.mirea.khudyakovma.domain.models.Weather;
import ru.mirea.khudyakovma.domain.repository.WeatherRepository;

public class GetWeatherUseCase {
    private final WeatherRepository repository;

    public GetWeatherUseCase(WeatherRepository repository) {
        this.repository = repository;
    }

    public Weather execute() {
        return repository.getWeather();
    }
}
