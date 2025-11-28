package ru.mirea.khudyakovma.scrollviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout wrapper = findViewById(R.id.wrapper);
        LayoutInflater inflater = LayoutInflater.from(this);

        long current = 1L;

        for (int i = 1; i <= 100; i++) {
            View view = inflater.inflate(R.layout.item_progression, wrapper, false);

            TextView textView = view.findViewById(R.id.textView);
            String text = "a" + i + " = " + current;
            textView.setText(text);

            wrapper.addView(view);

            current *= 2;
        }
    }
}
