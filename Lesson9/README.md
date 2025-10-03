Отчёт по практической работе №1.

Была создана use-case диаграма для приложения Plantpal. Данное приложение предоставляет следующий функционал:
1. Регистрация аккаунта
2. Просмотр информации о растениях
3. Уведомления о необходимости полива
4. Определение растения по фото
5. Получение информации о погоде
<img width="726" height="542" alt="use-case" src="https://github.com/user-attachments/assets/d52a887d-5cb0-462b-be99-5e05ed12df15" />


Так же было произведено разбиение приложения на слои и составлена подробная схема.

<img width="641" height="902" alt="СлоиПриложения" src="https://github.com/user-attachments/assets/0a437cdd-f6f7-42ec-ad80-50f034b8d481" />

Задание 1. MovieProject

Был сделан экран в соответствии с примером.

<img width="542" height="1057" alt="MovieStartScreen" src="https://github.com/user-attachments/assets/5adf2002-2ea1-4509-8742-736af89147f0" />

Далее были созданы все необходимые директории и скрипты.

<img width="437" height="535" alt="files" src="https://github.com/user-attachments/assets/8dab4d95-14dd-4606-8bbc-83b010bf14ea" />

В соответствии с заданием в MovieRepositoryImpl данные фильма сохраняются и читаются именно через SharedPreferences. 
Context используется только в data-слое (MovieRepositoryImpl), куда он передаётся из MainActivity. В domain-слое (use cases, Movie, MovieRepository) никакого Context нет.
UseCase’ы (SaveMovieToFavoriteUseCase, GetFavoriteFilmUseCase) работают через интерфейс MovieRepository и не знают ничего о SharedPreferences.

Далее представлены скриншоты работы получившегося приложения.

<img width="542" height="1052" alt="NewFilm" src="https://github.com/user-attachments/assets/dfd265a9-d3b4-44de-8dcf-037418bbab83" />
<img width="540" height="1050" alt="SaveNewFilm" src="https://github.com/user-attachments/assets/8d5d3d0c-dbc0-4c83-8b8d-87bf21c293a5" />
<img width="540" height="1053" alt="FavoriteFilm" src="https://github.com/user-attachments/assets/00d740ef-48b4-46c8-9252-0b3d4c99dd4b" />
