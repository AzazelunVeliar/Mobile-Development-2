package ru.mirea.khudyakovma.fragmentmanagerapp;

public class CountryInfo {
    public final String name;
    public final String capital;
    public final String population;
    public final String currency;
    public final String language;
    public final String area;
    public final String timeZone;
    public final String fact;

    public CountryInfo(String name, String capital, String population, String currency,
                       String language, String area, String timeZone, String fact) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.currency = currency;
        this.language = language;
        this.area = area;
        this.timeZone = timeZone;
        this.fact = fact;
    }
}
