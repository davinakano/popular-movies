package com.davinakano.apps.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by davinakano on 02/04/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder>{

    private String[] mMovieData;

    public MoviesAdapter() {

    }

    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder {

        public final TextView mMovieNameTextView;

        public MoviesAdapterViewHolder(View itemView) {
            super(itemView);
            mMovieNameTextView = (TextView) itemView.findViewById(R.id.tv_movie_name);
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
        String movieName = mMovieData[position]; // TODO: This needs to be changed later
        holder.mMovieNameTextView.setText(movieName);
    }

    @Override
    public int getItemCount() {
        if (mMovieData != null) {
            return mMovieData.length;
        }
        return 0;
    }

    // TODO: This parameter needs to be changed later
    public void setMovieData(String[] movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }
}
