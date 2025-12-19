package ru.mirea.khudyakovma.movieproject.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(FavoriteMovieEntity entity);

    @Query("DELETE FROM favorite_movies WHERE id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM favorite_movies ORDER BY title ASC")
    List<FavoriteMovieEntity> getAll();

    @Query("SELECT * FROM favorite_movies WHERE id = :id LIMIT 1")
    FavoriteMovieEntity getById(int id);
}
