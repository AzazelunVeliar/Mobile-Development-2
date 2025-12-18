package ru.mirea.khudyakovma.fragmentmanagerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SimpleStringAdapter extends RecyclerView.Adapter<SimpleStringAdapter.VH> {

    public interface OnClick {
        void onClick(String value);
    }

    private final List<String> data;
    private final OnClick onClick;

    public SimpleStringAdapter(List<String> data, OnClick onClick) {
        this.data = data;
        this.onClick = onClick;
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tv;
        VH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(android.R.id.text1);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        String value = data.get(position);
        holder.tv.setText(value);
        holder.itemView.setOnClickListener(v -> onClick.onClick(value));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
