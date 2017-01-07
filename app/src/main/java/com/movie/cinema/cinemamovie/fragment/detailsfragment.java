package com.movie.cinema.cinemamovie.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.movie.cinema.cinemamovie.BuildConfig;
import com.movie.cinema.cinemamovie.R;
import com.movie.cinema.cinemamovie.activity.checknetwok;
import com.movie.cinema.cinemamovie.database.moviedatabase;
import com.movie.cinema.cinemamovie.model.moviemodel;
import com.movie.cinema.cinemamovie.movietask.reviewsmovieasync;
import com.movie.cinema.cinemamovie.movietask.videomoviejson;
import com.movie.cinema.cinemamovie.network.Network;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class detailsfragment extends Fragment {

    //views movie
    TextView tvtitle, tvovote_count, tvpopulairty, tvrelasedate,tvovote_average;
    Button save, play;

    Network network;
    //atributes movie
    ImageView imageView;
    moviemodel moviemodel;
    moviedatabase moviedatabase;
    String release_date;
    String original_title;
    String vote_count;
    String vote_average;
    String popularity;
    String id;
    String gettingImageUrl;
    //cursor db
    Cursor cursor;

    //list moviemodel
    List<moviemodel>moviemodelList;

    public detailsfragment() {


        cursor = null;


        moviemodelList=new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        //create object form view to inflate data movie
        View view = inflater.inflate(R.layout.detailsfragment, container, false);

        //intiatian views movie
        imageView = (ImageView) view.findViewById(R.id.image);
        save = (Button) view.findViewById(R.id.save);
        play = (Button) view.findViewById(R.id.btnplay);
        moviemodel = new moviemodel();
        tvtitle = (TextView) view.findViewById(R.id.title);
        tvovote_count = (TextView) view.findViewById(R.id.vote_count);
        tvovote_average = (TextView) view.findViewById(R.id.vote_average);
        tvpopulairty = (TextView) view.findViewById(R.id.popularity);
        tvrelasedate=(TextView)view.findViewById(R.id.date);

        //create bundle object
        Bundle bundle = getArguments();


        //check bundle

        //in case not null

        if(bundle!=null) {

            moviemodel = (com.movie.cinema.cinemamovie.model.moviemodel) bundle.getSerializable("result");
                moviemodelList.add(moviemodel);
                for (int i = 0; i < moviemodelList.size(); i++) {
                    original_title = moviemodelList.get(i).getOriginal_title();
                    popularity = String.valueOf(moviemodelList.get(i).getPopularity());
                    release_date = moviemodelList.get(i).getRelease_date();
                    vote_count = moviemodelList.get(i).getVote_count();
                    vote_average = moviemodelList.get(i).getVote_average();
                    gettingImageUrl = moviemodelList.get(i).getPoster_path();
                    id = moviemodel.getId();
                }

     }else{
            return  null;
        }


        //save favorite movie
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    moviedatabase = new moviedatabase(getActivity(), detailsfragment.this);

                    moviedatabase.savefavoritemovie(moviemodel.getId(),moviemodel.getOriginal_title(),moviemodel.getOverview());

                    save.setEnabled(false);
                    Toast.makeText(getActivity(), "data saved done", Toast.LENGTH_SHORT).show();

                }
        });

        network =new Network(getActivity());
        if(network.isOnline()) {
            Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w500/" + gettingImageUrl).into(imageView);

            tvtitle.setText("original_title :" + original_title);
            tvovote_count.setText("vote count :" + vote_count);
            tvovote_average.setText("vote average :" + vote_average);
            tvpopulairty.setText("popularity : " + popularity);
            tvrelasedate.setText("release_date :" + release_date);
            new videomoviejson(detailsfragment.this).execute("http://api.themoviedb.org/3/movie/" + id + "/videos?api_key=" + BuildConfig.OPEN_MOVIE_APP_API_KEY);
            new reviewsmovieasync(detailsfragment.this).execute(" http://api.themoviedb.org/3/movie/" + id + "/reviews?api_key=" + BuildConfig.OPEN_MOVIE_APP_API_KEY);
            return view;
        }else
        {
            Toast.makeText(getActivity(),"Connection Field",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getActivity(),checknetwok.class);
            startActivity(intent);
        }
        return view;


    }
}
