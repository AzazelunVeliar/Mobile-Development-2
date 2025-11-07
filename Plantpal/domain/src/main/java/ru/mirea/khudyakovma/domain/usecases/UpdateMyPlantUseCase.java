package ru.mirea.khudyakovma.domain.usecases;

import ru.mirea.khudyakovma.domain.models.Plant;
import ru.mirea.khudyakovma.domain.repository.PlantRepository;

public class UpdateMyPlantUseCase {
    private final PlantRepository repository;

    public UpdateMyPlantUseCase(PlantRepository repository) {
        this.repository = repository;
    }

    public boolean execute(Plant plant) {
        return repository.updatePlant(plant);
    }
}
