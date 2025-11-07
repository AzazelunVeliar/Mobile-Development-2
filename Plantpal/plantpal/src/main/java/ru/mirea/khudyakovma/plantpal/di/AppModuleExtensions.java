package ru.mirea.khudyakovma.plantpal.di;

import ru.mirea.khudyakovma.domain.usecases.AddNoteToPlantUseCase;
import ru.mirea.khudyakovma.domain.usecases.GetMyPlantsUseCase;
import ru.mirea.khudyakovma.domain.usecases.GetWeatherUseCase;
import ru.mirea.khudyakovma.plantpal.ui.PlantVMFactory;
import ru.mirea.khudyakovma.plantpal.ui.WeatherVMFactory;

public class AppModuleExtensions {
    private final AppModule module;

    public AppModuleExtensions(AppModule module) {
        this.module = module;
    }

    public PlantVMFactory providePlantVMFactory() {
        GetMyPlantsUseCase getMy = new GetMyPlantsUseCase(module.providePlantRepository());
        AddNoteToPlantUseCase addNote = new AddNoteToPlantUseCase(module.providePlantRepository());
        return new PlantVMFactory(getMy, addNote, module.provideExecutor());
    }

    public WeatherVMFactory provideWeatherVMFactory() {
        GetWeatherUseCase getWeather = new GetWeatherUseCase(module.provideWeatherRepository());
        return new WeatherVMFactory(getWeather, module.provideExecutor());
    }
}
