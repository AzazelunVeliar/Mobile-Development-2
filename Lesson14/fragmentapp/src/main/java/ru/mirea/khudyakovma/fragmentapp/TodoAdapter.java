package ru.mirea.khudyakovma.fragmentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.VH> {

    private final List<TodoItem> items;

    public TodoAdapter(List<TodoItem> items) {
        this.items = items;
    }

    static class VH extends RecyclerView.ViewHolder {
        CheckBox cb;
        VH(@NonNull View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.cbDone);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        TodoItem item = items.get(position);

        holder.cb.setText(item.title);

        holder.cb.setOnCheckedChangeListener(null);
        holder.cb.setChecked(item.done);

        holder.cb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.done = isChecked;
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
