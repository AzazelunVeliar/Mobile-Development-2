package ru.mirea.khudyakovma.data.datasource;

import ru.mirea.khudyakovma.data.dao.PlantDao;
import ru.mirea.khudyakovma.data.dao.PlantEntity;
import ru.mirea.khudyakovma.domain.repository.PlantDataSource;
import ru.mirea.khudyakovma.domain.models.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class LocalPlantDataSource implements PlantDataSource {
    private final PlantDao plantDao;
    private final ExecutorService executor;

    public LocalPlantDataSource(PlantDao plantDao, ExecutorService executor) {
        this.plantDao = plantDao;
        this.executor = executor;
    }

    @Override
    public Plant getPlantById(int id) {
        try {
            Future<PlantEntity> future = executor.submit(() -> plantDao.getById(id));
            PlantEntity entity = future.get();
            return entity != null ? mapToDomain(entity) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Plant> getAllPlants() {
        try {
            Future<List<PlantEntity>> future = executor.submit(() -> plantDao.getAll());
            List<PlantEntity> entities = future.get();
            return entities.stream()
                    .map(this::mapToDomain)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Plant> searchPlantsByName(String name) {
        try {
            Future<List<PlantEntity>> future = executor.submit(() -> plantDao.searchByName(name));
            List<PlantEntity> entities = future.get();
            return entities.stream()
                    .map(this::mapToDomain)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean savePlant(Plant plant) {
        try {
            Future<Boolean> future = executor.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    try {
                        PlantEntity entity = mapToEntity(plant);
                        plantDao.insert(entity);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
            });
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePlant(Plant plant) {
        try {
            Future<Boolean> future = executor.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    try {
                        PlantEntity entity = mapToEntity(plant);
                        plantDao.update(entity);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
            });
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePlant(int id) {
        try {
            Future<Boolean> future = executor.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    try {
                        plantDao.deleteById(id);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
            });
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Plant> getPlantsByCareLevel(String careLevel) {
        try {
            Future<List<PlantEntity>> future = executor.submit(() -> {
                List<PlantEntity> allPlants = plantDao.getAll();
                return allPlants.stream()
                        .filter(plant -> plant.careInstructions != null &&
                                plant.careInstructions.toLowerCase().contains(careLevel.toLowerCase()))
                        .collect(Collectors.toList());
            });
            List<PlantEntity> entities = future.get();
            return entities.stream()
                    .map(this::mapToDomain)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Plant> getFavoritePlants() {
        try {
            Future<List<PlantEntity>> future = executor.submit(() -> {
                List<PlantEntity> allPlants = plantDao.getAll();
                return allPlants.stream()
                        .limit(3)
                        .collect(Collectors.toList());
            });
            List<PlantEntity> entities = future.get();
            return entities.stream()
                    .map(this::mapToDomain)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean togglePlantFavorite(int plantId) {
        try {
            Future<Boolean> future = executor.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    try {
                        PlantEntity entity = plantDao.getById(plantId);
                        if (entity != null) {
                            plantDao.update(entity);
                            return true;
                        }
                        return false;
                    } catch (Exception e) {
                        return false;
                    }
                }
            });
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getPlantsCount() {
        try {
            Future<Integer> future = executor.submit(() -> plantDao.getCount());
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean plantExists(int plantId) {
        try {
            Future<Boolean> future = executor.submit(() -> {
                PlantEntity entity = plantDao.getById(plantId);
                return entity != null;
            });
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private Plant mapToDomain(PlantEntity entity) {
        Plant plant = new Plant(entity.id, entity.name);
        plant.setNote(entity.note);
        return plant;
    }

    private PlantEntity mapToEntity(Plant plant) {
        return new PlantEntity(
                plant.getId(),
                plant.getName(),
                plant.getNote(),
                "",
                "",
                System.currentTimeMillis()
        );
    }
}