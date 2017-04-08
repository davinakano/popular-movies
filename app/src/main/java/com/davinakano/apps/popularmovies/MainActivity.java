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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterClickHandler {

    private RecyclerView mRecyclerView;
    private MoviesAdapter mMoviesAdapter;

    private TextView mErrorMessageTextView;
    private ProgressBar mLoadingProgressBar;

    private static final String API_KEY = BuildConfig.API_KEY;

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

        mMoviesAdapter = new MoviesAdapter(this, this);
        mRecyclerView.setAdapter(mMoviesAdapter);

        // Retrofit setup
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        MovieDBAPI client = retrofit.create(MovieDBAPI.class);
        Call<PopularMoviesPayload> call = client.getPopularMovies(API_KEY);

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
            mMoviesAdapter.sortByRating();
            return true;
        } else if (optionSelected == R.id.action_sort_release_date) {
            mMoviesAdapter.sortByReleaseDate();
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
