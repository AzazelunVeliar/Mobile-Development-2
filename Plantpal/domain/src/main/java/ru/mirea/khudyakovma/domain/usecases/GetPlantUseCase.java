package ru.mirea.khudyakovma.domain.usecases;

import ru.mirea.khudyakovma.domain.models.Plant;
import ru.mirea.khudyakovma.domain.repository.PlantRepository;

public class GetPlantUseCase {
    private PlantRepository plantRepository;

    public GetPlantUseCase(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public Plant execute(int id) {
        return plantRepository.getPlantById(id);
    }
}
