package ru.mirea.khudyakovma.plantpal.domain.repository;

import java.util.List;
import ru.mirea.khudyakovma.plantpal.domain.models.Plant;

public interface PlantRepository {
    Plant getPlantById(int id);
    Plant searchPlantByName(String name);
    boolean addPlant(Plant plant);
    boolean updatePlant(Plant plant);
    boolean deletePlant(int id);
    List<Plant> getMyPlants();
    List<Plant> filterPlants(String query);
}
