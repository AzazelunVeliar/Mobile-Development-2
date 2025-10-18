Отчёт по практической работе №2.



Были созданы модули data и domain, в них были перенесены все файлы из соответствующих директорий.

<img width="638" height="1112" alt="image" src="https://github.com/user-attachments/assets/e7ce76ea-2827-4d39-846f-06380aec547c" />

Так же были разделены модели для слоя data и domain, были созданы интерфейс MovieStorage, его реализация MovieRepositoryImpl и SharedPrefMovieStorage.
Блок SharedStorage (MovieStorage, SharedPrefMovieStorage) независим и использует свою модель Movie.

Далее для приложения PlantPal был создан дизайн основных экранов.

<img width="979" height="1103" alt="image" src="https://github.com/user-attachments/assets/3286620d-e99b-466a-9d35-1db2b9ccca65" />


Были созданы отдельные модули data и domain (как в первом задании) и перенесены все соответствующие файлы в соответствующие модули и были созданы отдельные модели для слоёв data и domain.

<img width="551" height="1165" alt="image" src="https://github.com/user-attachments/assets/95a00e05-be01-48b0-b22d-ed933ed029ef" />

Далее были созданы новые активити для регистрации и входа в приложение с использованием Firebase.

Экран входа:

<img width="543" height="1213" alt="image" src="https://github.com/user-attachments/assets/de4c57be-1cf7-4c2e-83c8-631f20be5b65" />

Экран регистрации:

<img width="542" height="1173" alt="image" src="https://github.com/user-attachments/assets/f0ebca1c-54c5-4112-ac9b-97783ee0880c" />

Каждый экран полностью функционален и позволяет зарегистрировать пользователя и войти в приложение.

<img width="1858" height="733" alt="image" src="https://github.com/user-attachments/assets/928b8d0c-439a-47d7-936d-1f1816e25a0b" />

Были разработаны три метода обработки данных:
1. SharedPreferences
2. Room
3. NetworkApi

Например, SharedPreferences был использован для хранения информации о пользователе.

    // UserRepositoryImpl.java
      public class UserRepositoryImpl implements UserRepository {
        private final SharedPreferences sharedPreferences;
        public UserRepositoryImpl(Context context) {
          this.sharedPreferences = context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE);
        }
        private void saveUserToPreferences(User user) {
          SharedPreferences.Editor editor = sharedPreferences.edit();
          editor.putString("user_email", user.getEmail());
          editor.putString("user_name", user.getName());
          editor.apply();
        }
      }

Далее, Room.

Был использован для хранения данных о растениях.

      // PlantEntity.java
      @Entity(tableName = "plants")
      public class PlantEntity {
        @PrimaryKey
        public int id;
        public String name;
        public String note;
      }

      // LocalPlantDataSource.java
      public class LocalPlantDataSource implements PlantDataSource {
      private final PlantDao plantDao;
    
      @Override
      public Plant getPlantById(int id) {
          PlantEntity entity = plantDao.getById(id);
          return mapToDomain(entity);
        }
      }

    // PlantRepositoryImpl.java
    public class PlantRepositoryImpl implements PlantRepository {
      private final LocalPlantDataSource localDataSource;
    
      public static PlantRepositoryImpl create(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        LocalPlantDataSource dataSource = new LocalPlantDataSource(database.plantDao(), executor);
        return new PlantRepositoryImpl(dataSource, executor);
      }
    }

Network API

Получает данные погоды из внешнего источника. 

    // WeatherApi.java
    public interface WeatherApi {
        @GET("weather")
        Call<WeatherResponse> getWeather(@Query("q") String city, @Query("appid") String apiKey);
    }

    // RemoteWeatherDataSource.java
    public class RemoteWeatherDataSource implements WeatherDataSource {
      @Override
      public Weather getCurrentWeather() {
        Call<WeatherResponse> call = weatherApi.getWeather("Moscow", apiKey);
        Response<WeatherResponse> response = call.execute();
        return mapToDomain(response.body());
      }
    }
