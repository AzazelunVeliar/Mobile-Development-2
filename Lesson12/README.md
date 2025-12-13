Отчёт по практической работе №4.

Был создан модуль ScrollView.
В нём выводится геометрическая прогрессия со знаменателем 2 до 100 элемента. Был размещён scrollview, а внутри вертикальный LinearLayout

<img width="475" height="1060" alt="scrollview1" src="https://github.com/user-attachments/assets/67b5ca25-ccae-40f0-999c-7bfab505c29d" />

<img width="476" height="1066" alt="scrollview2" src="https://github.com/user-attachments/assets/65bd885b-562e-4134-a8cd-de6f7524d8b1" />

<img width="476" height="1065" alt="scrollview3" src="https://github.com/user-attachments/assets/52bc0466-c6a9-4d98-af21-cc0ef4e496c7" />

Создан модуль ListViewApp, в котором реализовано отображение списка авторов и книг с помощью компонента ListView.
Для наполнения списка использован ArrayAdapter с макетом simple_list_item_2, чтобы отображать два текста: имя автора и название книги.

<img width="476" height="1062" alt="listview1" src="https://github.com/user-attachments/assets/e10ec4b8-b485-4361-828c-b388a4109eb6" />

<img width="472" height="1062" alt="listview2" src="https://github.com/user-attachments/assets/4c7bb2ae-2a62-46c0-859a-15d06d668244" />

Создан модуль с реализацией списка исторических событий на основе RecyclerView.
Созданы: собственная модель данных, разметка элемента (CardView + ConstraintLayout), ViewHolder, RecyclerView.Adapter.
Подключён LinearLayoutManager, обеспечивающий вертикальный список.

<img width="473" height="1051" alt="recyclerview1" src="https://github.com/user-attachments/assets/0baf290f-a054-4f5e-aff2-a3b1dd09b870" />

<img width="477" height="1062" alt="recyclerview2" src="https://github.com/user-attachments/assets/250097b1-63ae-420d-805b-7932aa630419" />

Так же было модифицировано приложение из практики №1.
Создана заглушка данных в репозитории (MovieRepositoryImpl), возвращающая список фильмов (имитация получения данных от внешнего API).
Добавлен метод getMovies() в интерфейс репозитория.
Создана ViewModel (MovieViewModel), которая хранит список фильмов в LiveData и поставляет его в UI.
Создан компонент отображения списка: RecyclerView на экране, адаптер MovieAdapter, разметка movie_item_view.xml.
MainActivity переписана, теперь она инициализирует RecyclerView, ViewModel и наблюдает за LiveData.

<img width="528" height="1187" alt="Movieproject" src="https://github.com/user-attachments/assets/b953b494-eaae-423f-b026-aec397dd71f4" />
