package ru.mirea.khudyakovma.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;
    private ApiService apiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiService = RetrofitClient.getApiService();

        loadTodos();
    }

    private void loadTodos() {
        Call<List<Todo>> call = apiService.getTodos();
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Todo> todos = response.body();
                    todoAdapter = new TodoAdapter(MainActivity.this, todos, apiService);
                    recyclerView.setAdapter(todoAdapter);
                } else {
                    Log.e(TAG, "onResponse error code: " + response.code());
                    Toast.makeText(MainActivity.this,
                            "Ошибка загрузки списка: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(MainActivity.this,
                        "Сетевой сбой: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
