package com.example.mvvplab.data.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvplab.data.model.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static com.example.mvvplab.utils.SharedFunctional.BASE_URL2;
import static com.example.mvvplab.utils.SharedFunctional.TAG;


public class ApiProvider {


    /**
     *
      * @return liveData of movies
     */
    public MutableLiveData<List<Movie>> requestMovies(){
        Log.i(TAG, "RepositoryMovie >> start: ");
        MutableLiveData<List<Movie>> liveDataMovies = new MutableLiveData<>();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL2)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIRequestsInterface apiInterface = retrofit.create(APIRequestsInterface.class);

        Call<List<Movie>> call = apiInterface.getMovies();

        call.enqueue(new Callback<List<Movie>>() {

            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "RepositoryMovie >> onResponse: ");
                    Log.i(TAG, "onResponse: size is >>" + response.body().get(0).getTitle());

                    liveDataMovies.setValue(response.body());
                } else {
                    System.out.println("error body is >> " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.i(TAG, "RepositoryMovie >> onFailure: ");
            }
        });

        return liveDataMovies;
    }



    public interface APIRequestsInterface {

        //"https://api.androidhive.info/json/movies.json"
        @GET("movies.json")
        Call<List<Movie>> getMovies();

    }


}
