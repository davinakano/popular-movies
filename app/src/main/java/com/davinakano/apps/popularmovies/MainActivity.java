package com.davinakano.apps.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.davinakano.apps.popularmovies.Data.Movie;
import com.davinakano.apps.popularmovies.Data.NetworkUtils;
import com.davinakano.apps.popularmovies.Data.PopularMoviesPayload;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterClickHandler {

    private RecyclerView mRecyclerView;
    private MoviesAdapter mMoviesAdapter;

    private TextView mErrorMessageTextView;
    private ProgressBar mLoadingProgressBar;

    private NetworkUtils mNetworkUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialising views
        mErrorMessageTextView = (TextView) findViewById(R.id.tv_error_message);
        mLoadingProgressBar = (ProgressBar) findViewById(R.id.pb_loading);

        // RecyclerView setup
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_grid);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(false);

        // Adapter setup
        mMoviesAdapter = new MoviesAdapter(this, this);
        mRecyclerView.setAdapter(mMoviesAdapter);

        mNetworkUtils = new NetworkUtils();
        getMostPopularMovies();
    }

    public void getMostPopularMovies() {
        Call<PopularMoviesPayload> call = mNetworkUtils.retrofitGetPopularMovies();

        // Async call using Retrofit
        call.enqueue(new Callback<PopularMoviesPayload>() {
            @Override
            public void onResponse(Call<PopularMoviesPayload> call, Response<PopularMoviesPayload> response) {
                mLoadingProgressBar.setVisibility(View.INVISIBLE);
                showMoviesDataView();
                mMoviesAdapter.setMovieData(response.body().getResults());
            }

            @Override
            public void onFailure(Call<PopularMoviesPayload> call, Throwable t) {
                mLoadingProgressBar.setVisibility(View.INVISIBLE);
                showErrorMessage();
            }
        });
    }

    public void getHighestRatingMovies() {
        Call<PopularMoviesPayload> call = mNetworkUtils.retrofitGetTopRatedMovies();

        // Async call using Retrofit
        call.enqueue(new Callback<PopularMoviesPayload>() {
            @Override
            public void onResponse(Call<PopularMoviesPayload> call, Response<PopularMoviesPayload> response) {
                mLoadingProgressBar.setVisibility(View.INVISIBLE);
                showMoviesDataView();
                mMoviesAdapter.setMovieData(response.body().getResults());
            }

            @Override
            public void onFailure(Call<PopularMoviesPayload> call, Throwable t) {
                mLoadingProgressBar.setVisibility(View.INVISIBLE);
                showErrorMessage();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int optionSelected = item.getItemId();

        if (optionSelected == R.id.action_sort_rating) {
            getHighestRatingMovies();
            return true;
        } else if (optionSelected == R.id.action_sort_most_popular) {
            getMostPopularMovies();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(Movie m) {
        Intent i = new Intent(this, MovieDetailsActivity.class);
        i.putExtra(Intent.EXTRA_PACKAGE_NAME, m);
        startActivity(i);
    }

    private void showMoviesDataView() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageTextView.setVisibility(View.VISIBLE);
    }
}
