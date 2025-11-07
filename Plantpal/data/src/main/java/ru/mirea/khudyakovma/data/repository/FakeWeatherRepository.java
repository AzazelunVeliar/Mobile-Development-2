package ru.mirea.khudyakovma.data.repository;

import ru.mirea.khudyakovma.domain.models.Weather;
import ru.mirea.khudyakovma.domain.repository.WeatherRepository;

public class FakeWeatherRepository implements WeatherRepository {
    @Override
    public Weather getWeather() {
        return new Weather("Солнечно", 22.0);
    }
}
