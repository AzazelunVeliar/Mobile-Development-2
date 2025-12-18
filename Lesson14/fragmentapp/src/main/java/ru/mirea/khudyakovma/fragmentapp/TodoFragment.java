package ru.mirea.khudyakovma.fragmentapp;

import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class TodoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int number = requireArguments().getInt("my_number_student");
        TextView tvStudent = view.findViewById(R.id.tvStudent);
        tvStudent.setText("Номер по списку: " + number);

        List<TodoItem> todos = Arrays.asList(
                new TodoItem("Сделать практику по Fragment", true),
                new TodoItem("Добавить RecyclerView", true),
                new TodoItem("Сдать отчёт", false)
        );

        RecyclerView rv = view.findViewById(R.id.rvTodos);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv.setAdapter(new TodoAdapter(todos));
    }
}
