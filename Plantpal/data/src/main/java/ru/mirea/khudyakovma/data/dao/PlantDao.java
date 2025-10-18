package ru.mirea.khudyakovma.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlantDao {
    @Query("SELECT * FROM plants")
    List<PlantEntity> getAll();

    @Query("SELECT * FROM plants WHERE id = :id")
    PlantEntity getById(int id);

    @Query("SELECT * FROM plants WHERE name LIKE '%' || :name || '%'")
    List<PlantEntity> searchByName(String name);

    @Insert
    void insert(PlantEntity plant);

    @Update
    void update(PlantEntity plant);

    @Delete
    void delete(PlantEntity plant);

    @Query("DELETE FROM plants WHERE id = :id")
    void deleteById(int id);

    @Query("SELECT COUNT(*) FROM plants")
    int getCount();
}