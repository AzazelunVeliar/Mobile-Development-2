package ru.mirea.khudyakovma.domain.usecases;

import java.util.List;

import ru.mirea.khudyakovma.domain.models.Plant;
import ru.mirea.khudyakovma.domain.repository.PlantRepository;

public class GetMyPlantsUseCase {
    private final PlantRepository repository;

    public GetMyPlantsUseCase(PlantRepository repository) {
        this.repository = repository;
    }

    public List<Plant> execute() {
        return repository.getMyPlants();
    }
}
