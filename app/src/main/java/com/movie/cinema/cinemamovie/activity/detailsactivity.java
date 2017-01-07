package com.movie.cinema.cinemamovie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.movie.cinema.cinemamovie.R;
import com.movie.cinema.cinemamovie.fragment.detailsfragment;

public class detailsactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //layout deltals activity
        setContentView(R.layout.activity_detailsactivity);
        //check saveinstance is null
        if(savedInstanceState==null){
            //create bundle object
            Bundle bundle =new Bundle();
            bundle.putSerializable("result",getIntent().getExtras().getSerializable("result"));
            detailsfragment detailsfragment = new detailsfragment();
            //insert all details movie by setarguments
            detailsfragment.setArguments(bundle);
            getFragmentManager().beginTransaction().add(R.id.llDetailsContainer,detailsfragment).commit();
        }
    }
}
