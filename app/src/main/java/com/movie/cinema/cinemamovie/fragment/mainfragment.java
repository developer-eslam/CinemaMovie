package com.movie.cinema.cinemamovie.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.movie.cinema.cinemamovie.BuildConfig;
import com.movie.cinema.cinemamovie.R;
import com.movie.cinema.cinemamovie.movietask.movietask;

public class mainfragment extends Fragment  {

   public GridView gridView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentmovieview,container,false);
        gridView=(GridView)view.findViewById(R.id.grid);




        new movietask(mainfragment.this,gridView).execute("http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key="+ BuildConfig.OPEN_MOVIE_APP_API_KEY);


        return view;
    }



    public void updateMoviesbymostpopular() {

        new movietask(this,gridView).execute("http://api.themoviedb.org/3/movie/popular?api_key="+BuildConfig.OPEN_MOVIE_APP_API_KEY);
    }

    public void updateMoviesbymosthigestrate() {


        new movietask(this,gridView).execute("http://api.themoviedb.org/3/movie/top_rated?api_key="+BuildConfig.OPEN_MOVIE_APP_API_KEY);
    }

}
