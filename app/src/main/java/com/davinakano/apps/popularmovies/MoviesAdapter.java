package com.davinakano.apps.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by davinakano on 02/04/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder>{

    private List<Movie> mMovieData;
    private Context mContext;
    private final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185/";

    public MoviesAdapter(Context context) {
        mContext = context;
    }

    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder {

        public final TextView mMovieNameTextView;
        public final ImageView mMovieCoverImageView;

        public MoviesAdapterViewHolder(View itemView) {
            super(itemView);
            mMovieNameTextView = (TextView) itemView.findViewById(R.id.tv_movie_name);
            mMovieCoverImageView = (ImageView) itemView.findViewById(R.id.iv_movie_cover);
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
}
