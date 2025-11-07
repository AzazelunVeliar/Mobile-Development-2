package ru.mirea.khudyakovma.domain.usecases;

import ru.mirea.khudyakovma.domain.models.Plant;
import ru.mirea.khudyakovma.domain.repository.PlantRepository;

public class AddNoteToPlantUseCase {
    private final PlantRepository repository;

    public AddNoteToPlantUseCase(PlantRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int plantId, String note) {
        Plant p = repository.getPlantById(plantId);
        if (p == null) return false;
        p.setNote(note);
        return repository.updatePlant(p);
    }
}
