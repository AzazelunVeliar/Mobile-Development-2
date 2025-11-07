package ru.mirea.khudyakovma.domain.usecases;

import ru.mirea.khudyakovma.domain.models.Plant;
import ru.mirea.khudyakovma.domain.repository.PlantRepository;

public class SearchPlantByNameUseCase {
    private final PlantRepository repository;

    public SearchPlantByNameUseCase(PlantRepository repository) {
        this.repository = repository;
    }

    public Plant execute(String name) {
        return repository.searchPlantByName(name);
    }
}
