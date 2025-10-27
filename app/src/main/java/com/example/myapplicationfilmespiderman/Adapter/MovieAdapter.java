package com.example.myapplicationfilmespiderman.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.myapplicationfilmespiderman.DetailActivity;
import com.example.myapplicationfilmespiderman.R;
import com.example.myapplicationfilmespiderman.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final Context context;
    private final List<Movie> movies;
    private static final String IMAGE_BASE = "https://image.tmdb.org/t/p/w500";

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie m = movies.get(position);
        holder.textTitle.setText(m.getTitle() != null ? m.getTitle() : "—");
        String year = m.getYear();
        holder.textYear.setText((year != null && year.length() >= 4) ? year.substring(0,4) : (year != null ? year : ""));

        String posterPath = m.getPosterPath();
        String imageUrl = (posterPath != null) ? IMAGE_BASE + posterPath : null;
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imagePoster);

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, DetailActivity.class);
            i.putExtra("title", m.getTitle());
            i.putExtra("year", m.getYear());
            i.putExtra("poster", imageUrl);
            i.putExtra("description", m.getDescription());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() { return movies != null ? movies.size() : 0; }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePoster;
        TextView textTitle, textYear;
        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePoster = itemView.findViewById(R.id.imagePoster);
            textTitle = itemView.findViewById(R.id.textTitle);
            textYear = itemView.findViewById(R.id.textYear);
        }
    }
}
