package ru.mirea.khudyakovma.plantpal.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import ru.mirea.khudyakovma.domain.models.Plant;
import ru.mirea.khudyakovma.plantpal.R;

public class PlantAdapter extends ListAdapter<Plant, PlantAdapter.VH> {
    public interface OnEditClickListener { void onEditClick(Plant plant); }
    private final OnEditClickListener listener;

    public PlantAdapter(OnEditClickListener listener) {
        super(DIFF);
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        Plant p = getItem(position);
        h.tvName.setText(p.getName());
        h.tvNote.setText(p.getNote() == null || p.getNote().isEmpty() ? "Нет заметки" : p.getNote());
        h.btnEdit.setOnClickListener(v -> listener.onEditClick(p));
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvNote;
        ImageButton btnEdit;
        VH(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvNote = itemView.findViewById(R.id.tvNote);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }

    private static final DiffUtil.ItemCallback<Plant> DIFF = new DiffUtil.ItemCallback<Plant>() {
        @Override
        public boolean areItemsTheSame(@NonNull Plant oldItem, @NonNull Plant newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @Override
        public boolean areContentsTheSame(@NonNull Plant oldItem, @NonNull Plant newItem) {
            if (!oldItem.getName().equals(newItem.getName())) return false;
            String a = oldItem.getNote() == null ? "" : oldItem.getNote();
            String b = newItem.getNote() == null ? "" : newItem.getNote();
            return a.equals(b);
        }
    };
}
