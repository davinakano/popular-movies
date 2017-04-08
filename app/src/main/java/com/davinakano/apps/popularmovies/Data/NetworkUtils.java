package com.davinakano.apps.popularmovies.Data;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.davinakano.apps.popularmovies.BuildConfig.API_KEY;

/**
 * Created by davinakano on 08/04/2017.
 */

public class NetworkUtils {

    // Retrofit setup
    public static Call<PopularMoviesPayload> retrofitGetPopularMovies() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        MovieDBAPI client = retrofit.create(MovieDBAPI.class);
        return client.getPopularMovies(API_KEY);
    }
}
