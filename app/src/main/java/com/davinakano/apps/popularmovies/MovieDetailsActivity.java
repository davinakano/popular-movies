package com.davinakano.apps.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.davinakano.apps.popularmovies.Data.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView mMovieNameTextView;
    private TextView mMovieOverviewTextView;
    private ImageView mMoviePosterImageView;
    private TextView mMovieRatingTextView;
    private TextView mMovieReleaseDateTextView;

    private final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w342/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // View setup
        mMovieNameTextView        = (TextView)  findViewById(R.id.tv_details_movie_original_title);
        mMoviePosterImageView     = (ImageView) findViewById(R.id.iv_details_movie_poster);
        mMovieRatingTextView      = (TextView)  findViewById(R.id.tv_details_movie_rating);
        mMovieOverviewTextView    = (TextView)  findViewById(R.id.tv_details_movie_overview);
        mMovieReleaseDateTextView = (TextView)  findViewById(R.id.tv_details_movie_release_date);

        // Get movie from Intent and assign to views
        Movie m = (Movie) getIntent().getSerializableExtra(Intent.EXTRA_PACKAGE_NAME);
        mMovieNameTextView.setText(m.getTitle());
        mMovieOverviewTextView.setText(m.getOverview());
        mMovieRatingTextView.setText(m.getVoteAverage().toString() + " / 10");
        mMovieReleaseDateTextView.setText(m.getReleaseDate());
        Picasso.with(this)
                .load(IMAGE_BASE_URL + m.getBackdropPath())
                .into(mMoviePosterImageView);
    }
}
