package ru.mirea.khudyakovma.movieproject.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.khudyakovma.movieproject.databinding.MovieItemViewBinding;
import ru.mirea.khudyakovma.movieproject.domain.models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }

    private final List<Movie> items = new ArrayList<>();
    private final OnMovieClickListener listener;

    public MovieAdapter(OnMovieClickListener listener) {
        this.listener = listener;
    }

    public void submitList(List<Movie> movies) {
        items.clear();
        if (movies != null) items.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemViewBinding binding = MovieItemViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = items.get(position);
        holder.binding.title.setText(movie.getTitle());
        holder.binding.subtitle.setText("Рейтинг: " + movie.getPrice());
        Glide.with(holder.binding.poster.getContext())
                .load(movie.getImageUrl())
                .centerCrop()
                .into(holder.binding.poster);
        holder.binding.getRoot().setOnClickListener(v -> {
            if (listener != null) listener.onMovieClick(movie);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        final MovieItemViewBinding binding;

        MovieViewHolder(MovieItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
