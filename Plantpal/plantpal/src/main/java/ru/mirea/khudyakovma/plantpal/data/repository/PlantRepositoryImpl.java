package ru.mirea.khudyakovma.plantpal.data.repository;

import java.util.ArrayList;
import java.util.List;
import ru.mirea.khudyakovma.plantpal.domain.models.Plant;
import ru.mirea.khudyakovma.plantpal.domain.repository.PlantRepository;

public class PlantRepositoryImpl implements PlantRepository {
    private List<Plant> plants = new ArrayList<>();

    public PlantRepositoryImpl() {
        plants.add(new Plant(1, "Фикус"));
        plants.add(new Plant(2, "Кактус"));
        plants.add(new Plant(3, "Орхидея"));
    }

    @Override
    public Plant getPlantById(int id) {
        return plants.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Plant searchPlantByName(String name) {
        return plants.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    @Override
    public boolean addPlant(Plant plant) {
        return plants.add(plant);
    }

    @Override
    public boolean updatePlant(Plant plant) {
        for (int i = 0; i < plants.size(); i++) {
            if (plants.get(i).getId() == plant.getId()) {
                plants.set(i, plant);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletePlant(int id) {
        return plants.removeIf(p -> p.getId() == id);
    }

    @Override
    public List<Plant> getMyPlants() {
        return plants;
    }

    @Override
    public List<Plant> filterPlants(String query) {
        List<Plant> filtered = new ArrayList<>();
        for (Plant p : plants) {
            if (p.getName().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(p);
            }
        }
        return filtered;
    }
}
