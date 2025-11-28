package ru.mirea.khudyakovma.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Event> events = getEvents();
        EventAdapter adapter = new EventAdapter(events);
        recyclerView.setAdapter(adapter);
    }

    private List<Event> getEvents() {
        List<Event> list = new ArrayList<>();

        int img = R.mipmap.ic_launcher;

        list.add(new Event(
                "Падение Западной Римской империи (476 г.)",
                "Переход Запада от античности к Средневековью после свержения Ромула Августула.",
                img
        ));
        list.add(new Event(
                "Крещение Руси (988 г.)",
                "Принятие христианства князем Владимиром и начало христианизации Древней Руси.",
                img
        ));
        list.add(new Event(
                "Великие географические открытия (XV–XVI вв.)",
                "Открытие новых маршрутов и континентов, формирование мировой торговли.",
                img
        ));
        list.add(new Event(
                "Великобритания: промышленная революция (XVIII–XIX вв.)",
                "Переход от ручного труда к машинному производству и фабрикам.",
                img
        ));
        list.add(new Event(
                "Французская революция (1789–1799 гг.)",
                "Падение монархии во Франции и утверждение идей гражданских прав.",
                img
        ));
        list.add(new Event(
                "Отмена крепостного права в России (1861 г.)",
                "Реформа Александра II, освободившая крестьян от личной зависимости.",
                img
        ));
        list.add(new Event(
                "Первая мировая война (1914–1918 гг.)",
                "Крупнейший вооружённый конфликт начала XX века, изменивший политическую карту мира.",
                img
        ));
        list.add(new Event(
                "Октябрьская революция в России (1917 г.)",
                "Приход к власти большевиков и начало советского периода истории.",
                img
        ));
        list.add(new Event(
                "Вторая мировая война (1939–1945 гг.)",
                "Мировой конфликт с участием большинства государств планеты.",
                img
        ));
        list.add(new Event(
                "Победа в Великой Отечественной войне (1945 г.)",
                "Разгром нацистской Германии и окончание войны в Европе.",
                img
        ));
        list.add(new Event(
                "Создание ООН (1945 г.)",
                "Учреждение международной организации для поддержания мира и безопасности.",
                img
        ));
        list.add(new Event(
                "Полет Юрия Гагарина в космос (1961 г.)",
                "Первый полет человека в космос на корабле «Восток-1».",
                img
        ));
        list.add(new Event(
                "Высадка человека на Луну (1969 г.)",
                "Экипаж «Аполлона-11» совершил первую высадку людей на поверхность Луны.",
                img
        ));
        list.add(new Event(
                "Распад СССР (1991 г.)",
                "Завершение существования Советского Союза и образование новых независимых государств.",
                img
        ));
        list.add(new Event(
                "Начало эры Интернета (конец XX в.)",
                "Широкое распространение глобальной сети и цифровых технологий.",
                img
        ));

        return list;
    }
}
