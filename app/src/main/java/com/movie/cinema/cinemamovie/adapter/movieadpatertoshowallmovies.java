package com.movie.cinema.cinemamovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movie.cinema.cinemamovie.R;
import com.movie.cinema.cinemamovie.model.moviemodel;

import java.util.ArrayList;
import java.util.List;



public class movieadpatertoshowallmovies  extends ArrayAdapter {
    //member data
    List<moviemodel> list=new ArrayList<>();


    public movieadpatertoshowallmovies(Context context, int resource) {
        super(context, resource);

    }
    //add data moviemodel in list spesifc moviemodel
    public void add(moviemodel object) {
        list.add(object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row =convertView;
        movieholder movieholder;
        //case view is null
        if (row == null) {

            //inflate layout to show the data to user
            LayoutInflater layoutInflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row =layoutInflater.inflate(R.layout.display_contact_row,parent,false);
            //create object from movieholder to draw data
            movieholder = new movieholder();


            movieholder.orignal_title= (TextView) row.findViewById(R.id.orignaltitle_movie);
            movieholder.overview= (TextView) row.findViewById(R.id.overview_movie);
            row.setTag(movieholder);
        }
        //case view not null
        else{

            movieholder = (movieadpatertoshowallmovies.movieholder) row.getTag();

        }
        moviemodel moviemodel=(moviemodel) getItem(position);


        movieholder.orignal_title.setText(moviemodel.getOriginal_title());
        movieholder.overview.setText(moviemodel.getOverview());

        return row;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public int getCount() {
        return list.size();
    }
    static  class  movieholder{
        TextView orignal_title;
        TextView overview;
    }

}
