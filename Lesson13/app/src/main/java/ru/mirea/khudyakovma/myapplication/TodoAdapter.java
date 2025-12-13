package ru.mirea.khudyakovma.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {

    private static final String TAG = "TodoAdapter";

    private final LayoutInflater layoutInflater;
    private final List<Todo> todos;
    private final ApiService apiService;
    private final Context context;

    private final List<String> imageUrls = new ArrayList<>();

    public TodoAdapter(Context context, List<Todo> todoList, ApiService apiService) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.todos = todoList;
        this.apiService = apiService;

        imageUrls.add("https://picsum.photos/seed/todo1/300/300");
        imageUrls.add("https://picsum.photos/seed/todo2/300/300");
        imageUrls.add("https://picsum.photos/seed/todo3/300/300");
        imageUrls.add("https://picsum.photos/seed/todo4/300/300");
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todos.get(position);

        holder.textViewTitle.setText(todo.getTitle());

        holder.checkBoxCompleted.setOnCheckedChangeListener(null);
        holder.checkBoxCompleted.setChecked(Boolean.TRUE.equals(todo.getCompleted()));

        String url = imageUrls.get(position % imageUrls.size());
        Picasso.get()
                .load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .resize(100, 100)
                .centerCrop()
                .into(holder.imageViewTodo);

        holder.checkBoxCompleted.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        todo.setCompleted(isChecked);

                        Call<Todo> call = apiService.updateTodo(
                                todo.getId(),
                                new Todo(todo.getUserId(), todo.getId(), todo.getTitle(), todo.getCompleted())
                        );

                        call.enqueue(new Callback<Todo>() {
                            @Override
                            public void onResponse(Call<Todo> call, Response<Todo> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(context,
                                            "Статус задачи обновлён",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.e(TAG, "onResponse error code: " + response.code());
                                    Toast.makeText(context,
                                            "Ошибка обновления: " + response.code(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Todo> call, Throwable t) {
                                Log.e(TAG, "onFailure: " + t.getMessage());
                                Toast.makeText(context,
                                        "Сетевой сбой: " + t.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }
}
