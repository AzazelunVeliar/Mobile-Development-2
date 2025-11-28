package ru.mirea.khudyakovma.recyclerviewapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder {

    private final ImageView imageViewEvent;
    private final TextView textViewEventTitle;
    private final TextView textViewEventDescription;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        imageViewEvent = itemView.findViewById(R.id.imageViewEvent);
        textViewEventTitle = itemView.findViewById(R.id.textViewEventTitle);
        textViewEventDescription = itemView.findViewById(R.id.textViewEventDescription);
    }

    public ImageView getImageViewEvent() {
        return imageViewEvent;
    }

    public TextView getTextViewEventTitle() {
        return textViewEventTitle;
    }

    public TextView getTextViewEventDescription() {
        return textViewEventDescription;
    }
}
