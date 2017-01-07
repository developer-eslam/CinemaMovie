package com.movie.cinema.cinemamovie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.movie.cinema.cinemamovie.R;
import com.movie.cinema.cinemamovie.network.Network;

public class checknetwok extends Activity {

    Button button;
    Network network;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checknetwok);
        button=(Button)findViewById(R.id.button);

        network=new Network(getApplicationContext());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(network.isOnline()){
                    startActivity(new Intent(checknetwok.this,MainActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
