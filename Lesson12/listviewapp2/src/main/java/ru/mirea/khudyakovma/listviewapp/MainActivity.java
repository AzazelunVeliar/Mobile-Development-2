package ru.mirea.khudyakovma.listviewapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.LayoutInflater;
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
            "Филип Дик"
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
            "Мечтают ли андроиды об электроовцах?"
    };

    private final boolean[] after30Years = {
            false, false, false, false, false,
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

                text1.setText(authors[position]);

                String when = after30Years[position]
                        ? "более чем через 30 лет"
                        : "в ближайшие 30 лет";

                text2.setText(books[position] + " — " + when);
                return view;
            }
        };

        listView.setAdapter(adapter);
    }
}
