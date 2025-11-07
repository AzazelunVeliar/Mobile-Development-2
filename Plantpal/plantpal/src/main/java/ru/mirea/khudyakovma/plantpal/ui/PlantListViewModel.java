package ru.mirea.khudyakovma.plantpal.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import ru.mirea.khudyakovma.domain.models.Plant;
import ru.mirea.khudyakovma.domain.usecases.AddNoteToPlantUseCase;
import ru.mirea.khudyakovma.domain.usecases.GetMyPlantsUseCase;

public class PlantListViewModel extends ViewModel {
    private final GetMyPlantsUseCase getMyPlantsUseCase;
    private final AddNoteToPlantUseCase addNoteToPlantUseCase;
    private final ExecutorService executor;

    private final MutableLiveData<List<Plant>> sourcePlants = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<String> query = new MutableLiveData<>("");
    private final MediatorLiveData<List<Plant>> plants = new MediatorLiveData<>();

    public PlantListViewModel(GetMyPlantsUseCase getMyPlantsUseCase,
                              AddNoteToPlantUseCase addNoteToPlantUseCase,
                              ExecutorService executor) {
        this.getMyPlantsUseCase = getMyPlantsUseCase;
        this.addNoteToPlantUseCase = addNoteToPlantUseCase;
        this.executor = executor;

        plants.addSource(sourcePlants, v -> recompute());
        plants.addSource(query, v -> recompute());
        load();
    }

    public LiveData<List<Plant>> getPlants() { return plants; }

    public void load() {
        executor.execute(() -> {
            List<Plant> data = getMyPlantsUseCase.execute();
            sourcePlants.postValue(data);
        });
    }

    public void setQuery(String q) {
        query.setValue(q == null ? "" : q);
    }

    public void addNote(int plantId, String note) {
        executor.execute(() -> {
            boolean ok = addNoteToPlantUseCase.execute(plantId, note);
            if (ok) load();
        });
    }

    private void recompute() {
        List<Plant> base = sourcePlants.getValue() == null ? new ArrayList<>() : sourcePlants.getValue();
        String q = query.getValue() == null ? "" : query.getValue().trim().toLowerCase();
        if (q.isEmpty()) {
            plants.setValue(base);
            return;
        }
        List<Plant> filtered = new ArrayList<>();
        for (Plant p : base) {
            if (p.getName().toLowerCase().contains(q)) filtered.add(p);
        }
        plants.setValue(filtered);
    }
}
