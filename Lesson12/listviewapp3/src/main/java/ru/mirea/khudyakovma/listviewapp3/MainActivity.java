package ru.mirea.khudyakovma.listviewapp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String[] authors = {
            "Фёдор Достоевский",
            "Лев Толстой",
            "Франц Кафка",
            "Джордж Оруэлл",
            "Рэй Брэдбери",
            "Габриэль Гарсиа Маркес",
            "Умберто Эко",
            "Герберт Уэллс",
            "Айзек Азимов",
            "Филип К. Дик",
            "Дж. Р. Р. Толкин",
            "Дж. Р. Р. Толкин",
            "Дж. Р. Р. Толкин",
            "Михаил Булгаков",
            "Эрнест Хемингуэй",
            "Альбер Камю",
            "Харпер Ли",
            "Дэн Симмонс",
            "Нил Гейман",
            "Чак Паланик",
            "Оскар Уайльд",
            "Артур Конан Дойл",
            "Стивен Кинг",
            "Борис и Аркадий Стругацкие",
            "Станислав Лем",
            "Джордж Мартин",
            "Орхан Памук",
            "Харуки Мураками",
            "Виктор Пелевин",
            "Маргарет Этвуд"
    };

    private final String[] books = {
            "Преступление и наказание",
            "Война и мир",
            "Процесс",
            "1984",
            "451° по Фаренгейту",
            "Сто лет одиночества",
            "Имя розы",
            "Машина времени",
            "Основание",
            "Мечтают ли андроиды об электроовцах?",
            "Властелин колец: Братство Кольца",
            "Властелин колец: Две крепости",
            "Властелин колец: Возвращение короля",
            "Мастер и Маргарита",
            "По ком звонит колокол",
            "Чума",
            "Убить пересмешника",
            "Гиперион",
            "Американские боги",
            "Бойцовский клуб",
            "Портрет Дориана Грея",
            "Собака Баскервилей",
            "Сияние",
            "Пикник на обочине",
            "Солярис",
            "Игра престолов",
            "Музей невинности",
            "Норвежский лес",
            "Generation «П»",
            "Рассказ служанки"
    };

    private final boolean[] after30Years = {
            false, false, false, false, false,
            false, false, false, false, false,
            false, false, false, false, false,
            false, false, false, false, false,
            true, true, true, true, true,
            true, true, true, true, true
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                authors
        ) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                String when = after30Years[position]
                        ? "более чем через 30 лет"
                        : "в ближайшие 30 лет";

                text1.setText(authors[position]);
                text2.setText(books[position] + " — " + when);
                return view;
            }
        };

        listView.setAdapter(adapter);
    }
}
