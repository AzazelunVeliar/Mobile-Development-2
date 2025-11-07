package ru.mirea.khudyakovma.plantpal;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import ru.mirea.khudyakovma.plantpal.R;
import ru.mirea.khudyakovma.plantpal.di.AppModule;
import ru.mirea.khudyakovma.plantpal.di.AppModuleExtensions;
import java.util.List;
import ru.mirea.khudyakovma.domain.models.Plant;
import ru.mirea.khudyakovma.plantpal.ui.PlantAdapter;
import ru.mirea.khudyakovma.plantpal.ui.PlantListViewModel;
import ru.mirea.khudyakovma.plantpal.ui.PlantVMFactory;

public class PlantList extends AppCompatActivity implements PlantAdapter.OnEditClickListener {
    private Toolbar toolbar;
    private SearchView searchView;
    private RecyclerView recycler;
    private TextView tvEmpty;
    private PlantAdapter adapter;
    private PlantListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_list);
        toolbar = findViewById(R.id.toolbarPlants);
        searchView = findViewById(R.id.searchView);
        recycler = findViewById(R.id.recyclerPlants);
        tvEmpty = findViewById(R.id.tvEmpty);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PlantAdapter(this);
        recycler.setAdapter(adapter);

        AppModule module = new AppModule(getApplicationContext());
        AppModuleExtensions ext = new AppModuleExtensions(module);
        PlantVMFactory factory = ext.providePlantVMFactory();
        viewModel = new ViewModelProvider(this, factory).get(PlantListViewModel.class);

        viewModel.getPlants().observe(this, this::render);

        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String q) { viewModel.setQuery(q); return true; }
            @Override public boolean onQueryTextChange(String q) { viewModel.setQuery(q); return true; }
        });
    }

    private void render(List<Plant> plants) {
        adapter.submitList(plants);
        tvEmpty.setVisibility(plants == null || plants.isEmpty() ? TextView.VISIBLE : TextView.GONE);
    }

    @Override
    public void onEditClick(Plant plant) { }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) { finish(); return true; }
        return super.onOptionsItemSelected(item);
    }
}
