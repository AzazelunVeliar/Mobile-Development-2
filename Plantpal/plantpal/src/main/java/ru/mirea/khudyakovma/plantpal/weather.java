package ru.mirea.khudyakovma.plantpal;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import ru.mirea.khudyakovma.domain.models.Weather;
import ru.mirea.khudyakovma.plantpal.R;
import ru.mirea.khudyakovma.plantpal.di.AppModule;
import ru.mirea.khudyakovma.plantpal.di.AppModuleExtensions;
import ru.mirea.khudyakovma.plantpal.ui.WeatherVMFactory;
import ru.mirea.khudyakovma.plantpal.ui.WeatherViewModel;

public class weather extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvTemperature;
    private TextView tvCondition;
    private ProgressBar progress;
    private FloatingActionButton fabRefresh;
    private WeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        toolbar = findViewById(R.id.toolbarWeather);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvCondition = findViewById(R.id.tvCondition);
        progress = findViewById(R.id.progressWeather);
        fabRefresh = findViewById(R.id.fabRefresh);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AppModule module = new AppModule(getApplicationContext());
        AppModuleExtensions ext = new AppModuleExtensions(module);
        WeatherVMFactory factory = ext.provideWeatherVMFactory();
        viewModel = new ViewModelProvider(this, factory).get(WeatherViewModel.class);

        viewModel.getWeather().observe(this, this::render);
        fabRefresh.setOnClickListener(v -> { progress.setVisibility(View.VISIBLE); viewModel.refresh(); });
    }

    private void render(Weather w) {
        if (w == null) return;
        tvTemperature.setText(Math.round(w.getTemperature()) + "°C");
        tvCondition.setText(w.getForecast());
        progress.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) { finish(); return true; }
        return super.onOptionsItemSelected(item);
    }
}
