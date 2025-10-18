package ru.mirea.khudyakovma.domain.models;

public class Weather {
    private String forecast;
    private double temperature;

    public Weather(String forecast, double temperature) {
        this.forecast = forecast;
        this.temperature = temperature;
    }

    public String getForecast() { return forecast; }
    public double getTemperature() { return temperature; }
}
