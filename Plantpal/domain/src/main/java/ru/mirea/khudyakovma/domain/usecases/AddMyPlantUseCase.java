package ru.mirea.khudyakovma.domain.usecases;

import ru.mirea.khudyakovma.domain.models.Plant;
import ru.mirea.khudyakovma.domain.repository.PlantRepository;

public class AddMyPlantUseCase {
    private final PlantRepository repository;

    public AddMyPlantUseCase(PlantRepository repository) {
        this.repository = repository;
    }

    public boolean execute(Plant plant) {
        return repository.addPlant(plant);
    }
}
