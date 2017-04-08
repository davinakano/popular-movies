package com.davinakano.apps.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.davinakano.apps.popularmovies.Data.Movie;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by davinakano on 02/04/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder>{

    private List<Movie> mMovieData;
    private Context mContext;
    private final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185/";
    private final MoviesAdapterClickHandler mClickHandler;

    public MoviesAdapter(Context context, MoviesAdapterClickHandler clickHandler) {
        mContext = context;
        mClickHandler = clickHandler;
    }

    // To facilitate the click handler on Activity
    public interface MoviesAdapterClickHandler {
        void onClick(Movie m);
    }

    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mMovieNameTextView;
        public final ImageView mMovieCoverImageView;

        public MoviesAdapterViewHolder(View itemView) {
            super(itemView);
            mMovieNameTextView = (TextView) itemView.findViewById(R.id.tv_movie_name);
            mMovieCoverImageView = (ImageView) itemView.findViewById(R.id.iv_movie_cover);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Movie m = mMovieData.get(adapterPosition);
            mClickHandler.onClick(m);
        }
    }

    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.movie_grid_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutId, null);
        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesAdapterViewHolder holder, int position) {
        Movie movie = mMovieData.get(position);
        holder.mMovieNameTextView.setText(movie.getTitle());
        Picasso.with(mContext)
                .load(IMAGE_BASE_URL + movie.getPosterPath())
                .into(holder.mMovieCoverImageView);
    }

    @Override
    public int getItemCount() {
        if (mMovieData != null) {
            return mMovieData.size();
        }
        return 0;
    }

    public void setMovieData(List<Movie> movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }

    public void sortByRating() {
        if (mMovieData != null) {
            Collections.sort(mMovieData, new Comparator<Movie>() {

                @Override
                public int compare(Movie o1, Movie o2) {
                    return o2.getVoteAverage().compareTo(o1.getVoteAverage());
                }
            });
            notifyDataSetChanged();
        }
    }

    public void sortByReleaseDate() {
        if (mMovieData != null) {
            Collections.sort(mMovieData, new Comparator<Movie>() {

                @Override
                public int compare(Movie o1, Movie o2) {
                    return o2.getReleaseDate().compareTo(o1.getReleaseDate());
                }
            });
            notifyDataSetChanged();
        }
    }
}
