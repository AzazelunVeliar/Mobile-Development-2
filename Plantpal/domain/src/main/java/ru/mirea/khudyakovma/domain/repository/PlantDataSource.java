package ru.mirea.khudyakovma.domain.repository;

import ru.mirea.khudyakovma.domain.models.Plant;
import java.util.List;

public interface PlantDataSource {

    Plant getPlantById(int id);
    List<Plant> getAllPlants();
    List<Plant> searchPlantsByName(String name);
    boolean savePlant(Plant plant);
    boolean updatePlant(Plant plant);
    boolean deletePlant(int id);

    List<Plant> getPlantsByCareLevel(String careLevel);
    List<Plant> getFavoritePlants();
    boolean togglePlantFavorite(int plantId);

    int getPlantsCount();
    boolean plantExists(int plantId);
}