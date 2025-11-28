package ru.mirea.khudyakovma.movieproject.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.khudyakovma.movieproject.R;
import ru.mirea.khudyakovma.movieproject.domain.models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> items = new ArrayList<>();

    public void setItems(List<Movie> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item_view, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = items.get(position);
        holder.titleView.setText(movie.getName());
        holder.posterView.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView posterView;
        TextView titleView;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            posterView = itemView.findViewById(R.id.imageViewPoster);
            titleView = itemView.findViewById(R.id.textViewTitle);
        }
    }
}
