package com.davinakano.apps.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MoviesAdapter mMoviesAdapter;

    private TextView mErrorMessageTextView;
    private ProgressBar mLoadingProgressBar;

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

        mMoviesAdapter = new MoviesAdapter(this);
        mRecyclerView.setAdapter(mMoviesAdapter);

        // TODO: Remove fake data
        Movie[] fakeData = {
                new Movie("Moana", "http://images6.fanpop.com/image/photos/39800000/Moana-Book-Cover-disneys-moana-39830830-338-500.jpg"),
                new Movie("La La Land", "https://www.ost.co/img/209740.jpg"),
                new Movie("Taken 2", "http://pinoyexchange.net/pexmoviepromo/promo_list/Taken2/images/movieimage-synopsis.jpg"),
                new Movie("Moonlight", "https://www.cinematerial.com/media/posters/md/pm/pmx5eoj1.jpg?v=1481217882")
        };
        mMoviesAdapter.setMovieData(fakeData);
        showMoviesDataView();
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
