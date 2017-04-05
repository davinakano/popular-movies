package com.davinakano.apps.popularmovies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by davinakano on 02/04/2017.
 */

public class PopularMoviesPayload {
    @SerializedName("results")
    @Expose
    private List<Movie> results = null;

    public List<Movie> getResults() {
        return results;
    }
}
