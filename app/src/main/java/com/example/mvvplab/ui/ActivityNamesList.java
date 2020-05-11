package com.example.mvvplab.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvplab.R;
import com.example.mvvplab.data.model.Movie;
import com.example.mvvplab.viewmodels.MyViewModel;

import java.util.List;

import static com.example.mvvplab.utils.SharedFunctional.TAG;


public class ActivityNamesList extends AppCompatActivity {


    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AdapterNamesList myAdapter;

    MyViewModel viewModelMovie;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names_list);

        recyclerView = findViewById(R.id.rvMovies);

        progressBar = findViewById(R.id.progressBar);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        // linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        myAdapter = new AdapterNamesList();

        recyclerView.setAdapter(myAdapter);

        viewModelMovie = new ViewModelProvider(this).get(MyViewModel.class);

        // register for listener
        attachUserListObserver();

    }

    private void attachUserListObserver() {
        progressBar.setVisibility(View.VISIBLE);
        viewModelMovie.getMovies().observe(this, moviesList -> {
            // set to adapter
            Log.i(TAG, "ActivityMovieList >> onChanged:  + size >> "  + moviesList.size());
            // update the list inside adapter then notify the adapter to update ui
            Log.i(TAG, "ActivityMovieList >> onChanged: getTitle >> " + moviesList.get(0).getTitle());
            myAdapter.setMovies(moviesList);
            myAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
        });
    }


}
