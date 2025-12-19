package ru.mirea.khudyakovma.movieproject.data.network.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MoviesResponseDto {
    @SerializedName("movies")
    public List<MovieResponseDto> movies;
}
