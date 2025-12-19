package ru.mirea.khudyakovma.movieproject.data.network.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieResponseDto {
    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("plot")
    public String plot;

    @SerializedName("poster")
    public String poster;

    @SerializedName("rating")
    public double rating;

    @SerializedName("year")
    public int year;

    @SerializedName("genre")
    public List<String> genre;
}
