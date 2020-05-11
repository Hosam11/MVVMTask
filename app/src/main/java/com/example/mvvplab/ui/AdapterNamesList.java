package com.example.mvvplab.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvplab.R;
import com.example.mvvplab.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

import static com.example.mvvplab.utils.SharedFunctional.TAG;

public class AdapterNamesList extends RecyclerView.Adapter<AdapterNamesList.NamesViewHolder> {

    List<Movie> movies = new ArrayList<>();


    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public NamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.activity_single_name, parent, false);
        NamesViewHolder viewHolder = new NamesViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NamesViewHolder holder, int position) {
        holder.tvName.setText(movies.get(position).getTitle());
        Log.i(TAG, "onBindViewHolder: getTitle >> " + movies.get(position).getTitle());
        holder.tvMobile.setText(String.valueOf(movies.get(position).getRating()));


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class NamesViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView tvName;
        public TextView tvMobile;


        public NamesViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvMobile = itemView.findViewById(R.id.tvTitle);
        }


    }


}
