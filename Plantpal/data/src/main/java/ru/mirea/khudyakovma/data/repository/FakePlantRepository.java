package ru.mirea.khudyakovma.data.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.mirea.khudyakovma.domain.models.Plant;
import ru.mirea.khudyakovma.domain.repository.PlantRepository;

public class FakePlantRepository implements PlantRepository {
    private final List<Plant> data = new ArrayList<>();

    public FakePlantRepository() {
        Plant a = new Plant(1, "Ficus elastica");
        a.setNote("Пересадить весной");
        Plant b = new Plant(2, "Monstera deliciosa");
        b.setNote("Полив 1р/нед");
        Plant c = new Plant(3, "Sansevieria");
        c.setNote("Неприхотлива");
        data.add(a);
        data.add(b);
        data.add(c);
    }

    @Override
    public Plant getPlantById(int id) {
        for (Plant p : data) if (p.getId() == id) return p;
        return null;
    }

    @Override
    public Plant searchPlantByName(String name) {
        if (name == null) return null;
        String q = name.toLowerCase();
        for (Plant p : data) if (p.getName().toLowerCase().contains(q)) return p;
        return null;
    }

    @Override
    public boolean addPlant(Plant domainPlant) {
        if (domainPlant == null) return false;
        for (Plant p : data) if (p.getId() == domainPlant.getId()) return false;
        data.add(domainPlant);
        return true;
    }

    @Override
    public boolean updatePlant(Plant domainPlant) {
        if (domainPlant == null) return false;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == domainPlant.getId()) {
                data.set(i, domainPlant);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletePlant(int id) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == id) {
                data.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Plant> getMyPlants() {
        return new ArrayList<>(data);
    }

    @Override
    public List<Plant> filterPlants(String query) {
        if (query == null || query.trim().isEmpty()) return getMyPlants();
        String q = query.toLowerCase();
        List<Plant> out = new ArrayList<>();
        for (Plant p : data) if (p.getName().toLowerCase().contains(q)) out.add(p);
        return out;
    }
}
