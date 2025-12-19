package ru.mirea.khudyakovma.movieproject.data.network.dto;

import com.google.gson.annotations.SerializedName;

public class ProductResponseDto {
    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("thumbnail")
    public String thumbnail;

    @SerializedName("price")
    public double price;
}
