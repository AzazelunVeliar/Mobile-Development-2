package ru.mirea.khudyakovma.data.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherResponse {
    @SerializedName("main")
    public Main main;

    @SerializedName("weather")
    public List<Weather> weather;

    @SerializedName("name")
    public String cityName;

    public static class Main {
        @SerializedName("temp")
        public double temp;

        @SerializedName("humidity")
        public int humidity;

        @SerializedName("pressure")
        public int pressure;
    }

    public static class Weather {
        @SerializedName("main")
        public String main;

        @SerializedName("description")
        public String description;

        @SerializedName("icon")
        public String icon;
    }
}