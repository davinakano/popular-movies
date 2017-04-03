package com.davinakano.apps.popularmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by davinakano on 02/04/2017.
 */

public interface MovieDBAPI {

    @GET("movie/popular")
    Call<PopularMoviesPayload> getPopularMovies(@Query("api_key") String apiKey);
}
