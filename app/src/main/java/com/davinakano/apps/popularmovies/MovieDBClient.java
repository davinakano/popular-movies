package com.davinakano.apps.popularmovies;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by davinakano on 02/04/2017.
 */

public interface MovieDBClient {

    @GET("/movie/popular")
    Call<Movie[]> getPopularMovies();
}
