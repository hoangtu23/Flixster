package com.codepath.tule.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.tule.flixster.R;
import com.codepath.tule.flixster.activities.DetailActivity;
import com.codepath.tule.flixster.models.Movie;
import com.codepath.tule.flixster.utilities.Constants;

import org.parceler.Parcels;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviewViewHolder> {

    Context mContext;
    List<Movie> mMovies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.mContext = context;
        this.mMovies = movies;
    }

    @NonNull
    @Override
    public MoviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(mContext).inflate(R.layout.item_movie, parent, false);
        return new MoviewViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviewViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    class MoviewViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public MoviewViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.movieContainer);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl = "";
            if (mContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.getBackdropPath();
            }
            else { // portrait
                imageUrl = movie.getPosterPath();
            }
            Glide.with(mContext).load(imageUrl).into(ivPoster);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext, movie.getTitle(), Toast.LENGTH_SHORT).show();
                    // navigate to detail activity
                    Intent detailIntent = new Intent(mContext, DetailActivity.class);
                    detailIntent.putExtra(Constants.KEY_TITLE, movie.getTitle());
                    detailIntent.putExtra(Constants.KEY_OVERVIEW, movie.getOverview());
                    detailIntent.putExtra(Constants.KEY_RATING, movie.getVoteAverage());
                    detailIntent.putExtra(Constants.KEY_MOVIE, Parcels.wrap(movie));
                    mContext.startActivity(detailIntent);
                }
            });
        }
    }

}
