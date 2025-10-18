package ru.mirea.khudyakovma.data.dao;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plants")
public class PlantEntity {
    @PrimaryKey
    public int id;

    public String name;
    public String note;
    public String careInstructions;
    public String imageUrl;
    public long createdAt;

    public PlantEntity(int id, String name, String note, String careInstructions, String imageUrl, long createdAt) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.careInstructions = careInstructions;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }
}