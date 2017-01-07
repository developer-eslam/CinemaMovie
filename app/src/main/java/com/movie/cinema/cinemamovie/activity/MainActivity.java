package com.movie.cinema.cinemamovie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.movie.cinema.cinemamovie.R;
import com.movie.cinema.cinemamovie.movieinterface.movieinterface;
import com.movie.cinema.cinemamovie.network.Network;
import com.movie.cinema.cinemamovie.model.moviemodel;
import com.movie.cinema.cinemamovie.fragment.mainfragment;
import com.movie.cinema.cinemamovie.fragment.detailsfragment;
public class MainActivity extends AppCompatActivity implements movieinterface {


    //members data
    mainfragment mainfragment;
    detailsfragment detailsfragment;

    boolean TwoPane;

    Network network;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //layout activity main
        setContentView(R.layout.activity_main);

        //create object from class Network
        network=new Network(getApplicationContext());


        //check network is online if true
        if(network.isOnline()){

            Toast.makeText(getApplicationContext(),"network open",Toast.LENGTH_SHORT).show();
            FrameLayout fPanel2 = (FrameLayout) findViewById(R.id.description_fragment);

            //check fpanel2 is null
            if (null == fPanel2) {
                TwoPane = false;
            } else {
                TwoPane = true;
            }


            //check if savedInstanceState is null

            if(null==savedInstanceState) {
                mainfragment = new mainfragment();

                getFragmentManager().beginTransaction().add(R.id.main_fragment, mainfragment).commit();

            }
        }else if(!network.isOnline()){
            Toast.makeText(getApplicationContext(),"connection failed",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(MainActivity.this,checknetwok.class);
            startActivity(intent);

        }




    }
    //method from interface movieinterface to insert data and check if device in case onepane or twoopane

    public void setselectedmovie(moviemodel m) {


        //case mtwopane is true
        if (TwoPane) {

            Bundle bundle = new Bundle();
            bundle.putSerializable("result",m);
            detailsfragment = new detailsfragment();

            if (detailsfragment.getArguments() == null) {

                detailsfragment.setArguments(bundle);
                mainfragment.getFragmentManager().beginTransaction()
                        .replace(R.id.description_fragment, detailsfragment).commit();
            }else if(detailsfragment!=null) {

                mainfragment.getFragmentManager().beginTransaction().detach(detailsfragment).attach(detailsfragment).commit();
            }

            //case one pane
        }else {


            Intent i = new Intent(this, detailsactivity.class);
            i.putExtra("result",m);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(network.isOnline()){
            getMenuInflater().inflate(R.menu.menu,menu);
            return true;
        }
        else {

            return false;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id =item.getItemId();
        if(id==R.id.mostpopuler){

            mainfragment.updateMoviesbymostpopular();
            return  true;
        }
        else if(id==R.id.hightrate){
            mainfragment.updateMoviesbymosthigestrate();

            return  true;
        }
        else if(id==R.id.showallmovies){

            startActivity(new Intent(MainActivity.this,displaymovie.class));
            return  true;

        }


        return super.onOptionsItemSelected(item);
    }

}







