Отчёт по практической работе №7.


Модуль bottomnavigationapp


В данном модуле было реализовано приложение с навигацией между экранами с использованием BottomNavigationView и Navigation Component.
Приложение построено на основе одной Activity. В разметке activity_main.xml размещён NavHostFragment, который отвечает за отображение фрагментов. Навигация между экранами осуществляется через нижнее меню.

В MainActivity получаем NavHostFragment, затем NavController, и связываем его с BottomNavigationView.

    NavHostFragment navHostFragment =
        (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHost);
    if (navHostFragment == null) {
        throw new IllegalStateException("NavHostFragment не найден. Проверь activity_main.xml");
    }
    NavController navController = navHostFragment.getNavController();
    NavigationUI.setupWithNavController(binding.navView, navController);
    
Для управления навигацией был создан файл navigation.xml, в котором описаны все экраны и стартовый фрагмент. Нижнее меню описано в menu.xml и связано с NavController.

<img width="425" height="942" alt="botNavHome" src="https://github.com/user-attachments/assets/c22981e2-c44b-406a-8e7d-a86507206970" />

<img width="427" height="945" alt="botNavInfo" src="https://github.com/user-attachments/assets/621c6f75-92b1-4522-b821-2ac86c4856c6" />

<img width="427" height="947" alt="botNavProf" src="https://github.com/user-attachments/assets/110584a5-725e-4f28-8ab2-294fd60dc508" />


Модуль navigationdrawerapp


В данном модуле было реализовано приложение с боковым навигационным меню (Navigation Drawer) с использованием Navigation Component.
Приложение создано на основе шаблона Navigation Drawer Activity. Основной контейнер приложения реализован с помощью DrawerLayout, внутри которого размещён NavHostFragment.
Для навигации используется файл navigation.xml, а пункты бокового меню описаны в menu.xml. Связь между NavigationView и NavController настроена через NavigationUI.
Также был реализован заголовок бокового меню (nav_header.xml), в котором отображается информация о приложении и данные студента (номер по списку и группа).
В MainActivity настраивается AppBarConfiguration, который определяет, какие экраны считаются верхнеуровневыми, а также связывается NavController с NavigationView.

    NavHostFragment navHostFragment =
        (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);

    NavController navController = navHostFragment.getNavController();

    appBarConfiguration = new AppBarConfiguration.Builder(
        navController.getGraph()
    ).setOpenableLayout(binding.drawerLayout).build();

    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    NavigationUI.setupWithNavController(binding.navView, navController);

<img width="425" height="947" alt="menu" src="https://github.com/user-attachments/assets/89f512df-5688-4f36-a25c-427fbf0d3422" />

<img width="423" height="943" alt="homeNavDraw" src="https://github.com/user-attachments/assets/51bf82b3-9ae5-4702-a94e-f466371e579c" />

<img width="427" height="947" alt="infoNavDraw" src="https://github.com/user-attachments/assets/2aa40372-0219-434b-961a-e39535658517" />

<img width="425" height="945" alt="profNavDraw" src="https://github.com/user-attachments/assets/b055345f-02c6-4db8-82af-f56c7d08dc1b" />


Контрольное задание

movieproject

Было доработано приложение, созданное ранее.
Вместо одного экрана была реализована полноценная навигация между экранами с использованием Navigation Component и Navigation Drawer.
Приложение было разделено на несколько экранов:
экран каталога фильмов,
экран деталей фильма,
экран избранного,
экран профиля пользователя.

Навигация между экранами осуществляется через NavHostFragment, а также через боковое меню Navigation Drawer.

Используемый NavController в MainActivity

    NavController navController = Navigation.findNavController(this, R.id.navHost);
    NavigationUI.setupWithNavController(binding.navView, navController);
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

Для хранения избранных фильмов была реализована локальная база данных с использованием Room.
Это позволило сохранять избранные элементы между запусками приложения.

Была создана:
сущность FavoriteMovieEntity,
DAO-интерфейс FavoriteMovieDao,
база данных AppDatabase.

Инициализация базы данных
    
    AppDatabase db = Room.databaseBuilder(
        context.getApplicationContext(),
        AppDatabase.class,
        "movie_db"
    ).fallbackToDestructiveMigration().build();

Добавление фильма в избранное

    favoriteDao.upsert(
    new FavoriteMovieEntity(
        movie.getId(),
        movie.getTitle(),
        movie.getDescription(),
        movie.getImageUrl(),
        movie.getPrice()
    )
    );

Использовались замоканные данные вместо раельного API.
