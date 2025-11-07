Отчёт по практической работе №3.


Проект, реализованный ранее, был переработан по принципам MVVM:

MainActivity содержала и UI-логику, и бизнес-логику	Логика перенесена во MainViewModel.
UseCase-ы вызывались напрямую из Activity	UseCase-ы вызываются только ViewModel.
Состояние не сохранялось при повороте экрана	ViewModel хранит состояние, поворот экрана не влияет.
UI обновлялся вручную	UI подписан на LiveData и обновляется автоматически.

Также был создан ViewModelFactory, чтобы передавать зависимости (например, экземпляр MovieRepository) корректно и без утечек контекста.

В рамках итоговой части задания была выполнена мини-система для отображения списка растений и данных о погоде.

Выполнено:
Взаимодействие Activity через ViewModel	(PlantListViewModel, WeatherViewModel)
Обновление интерфейса через LiveData	(LiveData<List<Plant>>, LiveData<Weather>)
Использовать MediatorLiveData	(MediatorLiveData объединяет список и строку поиска)
Замокать источники данных (БД и API) (FakePlantRepository, FakeWeatherRepository)

В результате:

Растения загружаются из замоканного локального хранилища.

Погода отображается из фиктивного API.

UI обновляется автоматически при изменении данных.

Активити не содержит логики работы с данными, только подписку на LiveData.

<img width="547" height="1213" alt="PlantPalMain" src="https://github.com/user-attachments/assets/186071fb-533e-4835-875c-7fb1c58eb95a" />

    public FakePlantRepository() {
        Plant a = new Plant(1, "Ficus elastica");
        a.setNote("Пересадить весной");
        Plant b = new Plant(2, "Monstera deliciosa");
        b.setNote("Полив 1р/нед");
        Plant c = new Plant(3, "Sansevieria");
        c.setNote("Неприхотлива");
        data.add(a);
        data.add(b);
        data.add(c);
    }

<img width="547" height="1216" alt="PlantPAlMyPlants" src="https://github.com/user-attachments/assets/635ec4b8-6f78-416d-934a-edd03870b99b" />

    public class FakeWeatherRepository implements WeatherRepository {
        @Override
        public Weather getWeather() {
            return new Weather("Солнечно", 22.0);
        }
    }

<img width="548" height="1212" alt="PlantPalWeather" src="https://github.com/user-attachments/assets/0627c38b-804b-4afe-889a-886099f8d0a2" />
