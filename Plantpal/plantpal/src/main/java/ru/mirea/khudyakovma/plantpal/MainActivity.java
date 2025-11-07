package ru.mirea.khudyakovma.plantpal;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

import ru.mirea.khudyakovma.plantpal.PlantList;
import ru.mirea.khudyakovma.plantpal.R;
import ru.mirea.khudyakovma.plantpal.weather;

public class MainActivity extends AppCompatActivity {
    private MaterialButton btnOpenPlants;
    private MaterialButton btnOpenWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOpenPlants = findViewById(R.id.btnOpenPlants);
        btnOpenWeather = findViewById(R.id.btnOpenWeather);

        btnOpenPlants.setOnClickListener(v ->
                startActivity(new Intent(this, PlantList.class)));

        btnOpenWeather.setOnClickListener(v ->
                startActivity(new Intent(this, weather.class)));
    }
}
