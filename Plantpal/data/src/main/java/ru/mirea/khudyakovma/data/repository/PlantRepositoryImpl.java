package ru.mirea.khudyakovma.data.repository;

import android.content.Context;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.mirea.khudyakovma.data.database.AppDatabase;
import ru.mirea.khudyakovma.data.datasource.LocalPlantDataSource;
import ru.mirea.khudyakovma.domain.repository.PlantRepository;

public class PlantRepositoryImpl implements PlantRepository {
    private final LocalPlantDataSource localDataSource;
    private final ExecutorService executor;

    public PlantRepositoryImpl(LocalPlantDataSource localDataSource, ExecutorService executor) {
        this.localDataSource = localDataSource;
        this.executor = executor;
    }

    public static PlantRepositoryImpl create(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        LocalPlantDataSource localDataSource = new LocalPlantDataSource(database.plantDao(), executor);
        return new PlantRepositoryImpl(localDataSource, executor);
    }

    @Override
    public ru.mirea.khudyakovma.domain.models.Plant getPlantById(int id) {
        try {
            return localDataSource.getPlantById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ru.mirea.khudyakovma.domain.models.Plant searchPlantByName(String name) {
        try {
            List<ru.mirea.khudyakovma.domain.models.Plant> plants = localDataSource.searchPlantsByName(name);
            return plants.isEmpty() ? null : plants.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addPlant(ru.mirea.khudyakovma.domain.models.Plant domainPlant) {
        try {
            return localDataSource.savePlant(domainPlant);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePlant(ru.mirea.khudyakovma.domain.models.Plant domainPlant) {
        try {
            return localDataSource.updatePlant(domainPlant);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePlant(int id) {
        try {
            return localDataSource.deletePlant(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ru.mirea.khudyakovma.domain.models.Plant> getMyPlants() {
        try {
            return localDataSource.getAllPlants();
        } catch (Exception e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    @Override
    public List<ru.mirea.khudyakovma.domain.models.Plant> filterPlants(String query) {
        try {
            return localDataSource.searchPlantsByName(query);
        } catch (Exception e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    public int getPlantsCount() {
        try {
            return localDataSource.getPlantsCount();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean plantExists(int plantId) {
        try {
            return localDataSource.plantExists(plantId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}