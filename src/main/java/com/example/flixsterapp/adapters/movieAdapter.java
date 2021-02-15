package com.example.flixsterapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixsterapp.DetailActivity;
import com.example.flixsterapp.R;
import com.example.flixsterapp.models.movies;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.List;

public class movieAdapter extends RecyclerView.Adapter<movieAdapter.viewHolder> {

    Context context;
    List<movies> movie;

    public movieAdapter(Context context, List<movies> movie){
        this.context = context;
        this.movie = movie;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.list_movie, parent, false);
        return new viewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder " + position);
        movies movies = movie.get(position);

        holder.bind(movies);
    }

    @Override
    public int getItemCount() {
        return movie.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        RelativeLayout container;
        TextView movieTitle;
        TextView movieOverview;
        ImageView posterImage;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.tvTitle);
            movieOverview = itemView.findViewById(R.id.tvOverview);
            posterImage = itemView.findViewById(R.id.tvImage);
            container = itemView.findViewById(R.id.container);
        }

        @NotNull
        public void bind(movies movies) {
            movieTitle.setText(movies.getTitle());
            movieOverview.setText(movies.getOverview());

            String imageUrl;

            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = movies.getBackdropPath();
            }
            else {
                imageUrl = movies.getPosterPath();
            }
            Glide.with(context).load(imageUrl).into(posterImage);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movies));
                    context.startActivity(i);
                }
            });


        }
    }
}
