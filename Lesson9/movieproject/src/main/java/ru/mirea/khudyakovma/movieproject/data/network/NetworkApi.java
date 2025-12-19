package ru.mirea.khudyakovma.movieproject.data.network;

import java.util.ArrayList;
import java.util.List;
import ru.mirea.khudyakovma.movieproject.domain.models.Movie;

public class NetworkApi implements DummyJsonApi {

    private final List<Movie> movies = new ArrayList<>();

    public NetworkApi() {
        movies.add(new Movie(1, "Интерстеллар", "Фантастическая история о поиске нового дома для человечества.", "https://i.imgur.com/8UG2Q8C.jpeg", 8.6));
        movies.add(new Movie(2, "Начало", "Вор проникает в сны, чтобы внедрить идею в подсознание.", "https://i.imgur.com/4s5WmQp.jpeg", 8.8));
        movies.add(new Movie(3, "Матрица", "Мир оказывается симуляцией, а истина опаснее иллюзии.", "https://i.imgur.com/2l5rG4R.jpeg", 8.7));
        movies.add(new Movie(4, "Бойцовский клуб", "История о двойной жизни и бунте против системы.", "https://i.imgur.com/MsKZ9bN.jpeg", 8.8));
        movies.add(new Movie(5, "Зелёная миля", "Тюремный надзиратель сталкивается с чудом и трагедией.", "https://i.imgur.com/0eQZQ0y.jpeg", 8.6));
        movies.add(new Movie(6, "Властелин колец: Братство кольца", "Путешествие, чтобы уничтожить Кольцо и спасти Средиземье.", "https://i.imgur.com/7x2mJ1N.jpeg", 8.9));
        movies.add(new Movie(7, "Тёмный рыцарь", "Бэтмен против Джокера: хаос против порядка.", "https://i.imgur.com/0E2bLkO.jpeg", 9.0));
        movies.add(new Movie(8, "Форрест Гамп", "Жизнь простого человека, который оказался в центре эпохи.", "https://i.imgur.com/yQb7QYd.jpeg", 8.8));
        movies.add(new Movie(9, "Побег из Шоушенка", "Надежда и свобода в стенах тюрьмы.", "https://i.imgur.com/Rp1Qd3W.jpeg", 9.3));
        movies.add(new Movie(10, "Гладиатор", "Рим, месть и честь на арене.", "https://i.imgur.com/3qg4fQy.jpeg", 8.5));
    }

    @Override
    public List<Movie> getMovies() {
        return new ArrayList<>(movies);
    }

    @Override
    public Movie getMovieById(int id) {
        for (Movie m : movies) {
            if (m.getId() == id) return m;
        }
        return null;
    }
}
