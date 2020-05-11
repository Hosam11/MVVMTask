package com.example.mvvplab.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvplab.data.api.RepositoryName;
import com.example.mvvplab.data.model.Movie;

import java.util.List;

import static com.example.mvvplab.utils.SharedFunctional.TAG;


public class MyViewModel extends ViewModel {


    MutableLiveData<List<Movie>> liveDataMovies;

    private RepositoryName repoMovie;

    public MyViewModel() {
        Log.i(TAG, "ViewModelMovie >>  constructor ");
        repoMovie = new RepositoryName();
    }


    /**
     *  check if liveDataMovies is null created else return it
     * @return liveDataMovies
     */
    public MutableLiveData<List<Movie>> getMovies() {
        if (liveDataMovies == null) {
            Log.i(TAG, "ViewModelMovie >> liveDataMovis is null: ");
            liveDataMovies = repoMovie.getMoviesFromApi();
        }
        return liveDataMovies;
    }


}
