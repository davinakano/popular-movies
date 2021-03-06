package com.davinakano.apps.popularmovies.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by davinakano on 02/04/2017.
 */

public interface MovieDBAPI {

    @GET("movie/popular")
    Call<PopularMoviesPayload> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<PopularMoviesPayload> getTopRatedMovies(@Query("api_key") String apiKey);
}
