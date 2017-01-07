package com.movie.cinema.cinemamovie.movietask;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.Toast;

import com.movie.cinema.cinemamovie.R;
import com.movie.cinema.cinemamovie.adapter.movieadpatertoshowallmovies;
import com.movie.cinema.cinemamovie.database.moviedatabase;
import com.movie.cinema.cinemamovie.database.moviedataspsifcdatabase;
import com.movie.cinema.cinemamovie.fragment.detailsfragment;
import com.movie.cinema.cinemamovie.model.moviemodel;

public class movietaskretirvedatafromdb extends AsyncTask<String , moviemodel, String> {

    Context ctx;
    movieadpatertoshowallmovies movieadpatertoshowallmovies;
    Activity activity;
    GridView gridView;
    moviedatabase moviedatabase;
    detailsfragment detailsfragment;
    moviedataspsifcdatabase.moviedata moviedata;

    public movietaskretirvedatafromdb(Context ctx)
    {
        this.ctx=ctx;
        activity = (Activity)ctx;



    }



    @Override
    protected void onProgressUpdate(moviemodel... values) {
        movieadpatertoshowallmovies.add(values[0]);
    }

    @Override
    protected String doInBackground(String... params) {


        String retrivefavoritemovie = params[0];
        moviedatabase = new moviedatabase(ctx,detailsfragment);


        //insert key to send this key in details movie
        if(retrivefavoritemovie.equals("get_data_movie")) {

            gridView=(GridView)activity.findViewById(R.id.grid_item);

            SQLiteDatabase sqLiteDatabase = moviedatabase.getWritableDatabase();
            movieadpatertoshowallmovies = new movieadpatertoshowallmovies(ctx,R.layout.display_contact_row);
            //calling method to get movie from db
            Cursor cursor = moviedatabase.getmoviesfavorite(sqLiteDatabase);
            String oringal_titles, overviews;

            //goto next record in table database
            while (cursor.moveToNext()) {
                oringal_titles = ("original title : " +cursor.getString(cursor.getColumnIndex(moviedata.MOVIE_TITLE)));
                overviews = ("overview :" +cursor.getString(cursor.getColumnIndex(moviedata.MOVIE_OVERVEW)));

                moviemodel contact = new moviemodel(oringal_titles,overviews);
                publishProgress(contact);
            }

            return "get_data_movie";
        }
        return null;
    }


    @Override
    protected void onPostExecute(String  s) {

        //if key get_data_movie is found
        if(s.equals("get_data_movie")){

            gridView.setAdapter(movieadpatertoshowallmovies);

        }else{
            Toast.makeText(ctx,s,Toast.LENGTH_LONG).show();

        }
    }
}

