package com.example.mvvplab.data.api;


import androidx.lifecycle.MutableLiveData;

import com.example.mvvplab.data.model.Movie;

import java.util.List;

public class RepositoryName  {

    // to call method download inside apiProvider
    ApiProvider providerMovie = new ApiProvider();

    public MutableLiveData<List<Movie>> getMoviesFromApi() {
        return providerMovie.requestMovies();
    }


}
