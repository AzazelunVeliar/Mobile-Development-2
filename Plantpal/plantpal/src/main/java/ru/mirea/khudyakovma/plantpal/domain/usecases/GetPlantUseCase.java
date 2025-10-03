package ru.mirea.khudyakovma.plantpal.domain.usecases;

import ru.mirea.khudyakovma.plantpal.domain.models.Plant;
import ru.mirea.khudyakovma.plantpal.domain.repository.PlantRepository;

public class GetPlantUseCase {
    private PlantRepository plantRepository;

    public GetPlantUseCase(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public Plant execute(int id) {
        return plantRepository.getPlantById(id);
    }
}
