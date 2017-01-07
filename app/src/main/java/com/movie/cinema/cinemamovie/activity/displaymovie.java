package com.movie.cinema.cinemamovie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.movie.cinema.cinemamovie.R;
import com.movie.cinema.cinemamovie.movietask.movietaskretirvedatafromdb;

public class displaymovie extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //layout display movie
        setContentView(R.layout.display_contact_layout);

        movietaskretirvedatafromdb backgroundtask =new movietaskretirvedatafromdb(this);

        //get key mpvie from class movietaskretirvedatafromdb
        backgroundtask.execute("get_data_movie");
    }
}
