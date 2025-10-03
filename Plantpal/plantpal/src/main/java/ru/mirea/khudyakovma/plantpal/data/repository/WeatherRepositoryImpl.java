package ru.mirea.khudyakovma.plantpal.data.repository;

import ru.mirea.khudyakovma.plantpal.domain.models.Weather;
import ru.mirea.khudyakovma.plantpal.domain.repository.WeatherRepository;

public class WeatherRepositoryImpl implements WeatherRepository {
    @Override
    public Weather getWeather() {
        return new Weather("Солнечно", 22.5);
    }
}
