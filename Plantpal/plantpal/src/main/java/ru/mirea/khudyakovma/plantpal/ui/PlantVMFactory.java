package ru.mirea.khudyakovma.plantpal.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.ExecutorService;

import ru.mirea.khudyakovma.domain.usecases.AddNoteToPlantUseCase;
import ru.mirea.khudyakovma.domain.usecases.GetMyPlantsUseCase;

public class PlantVMFactory implements ViewModelProvider.Factory {
    private final GetMyPlantsUseCase getMyPlantsUseCase;
    private final AddNoteToPlantUseCase addNoteToPlantUseCase;
    private final ExecutorService executor;

    public PlantVMFactory(GetMyPlantsUseCase getMyPlantsUseCase,
                          AddNoteToPlantUseCase addNoteToPlantUseCase,
                          ExecutorService executor) {
        this.getMyPlantsUseCase = getMyPlantsUseCase;
        this.addNoteToPlantUseCase = addNoteToPlantUseCase;
        this.executor = executor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlantListViewModel(getMyPlantsUseCase, addNoteToPlantUseCase, executor);
    }
}
