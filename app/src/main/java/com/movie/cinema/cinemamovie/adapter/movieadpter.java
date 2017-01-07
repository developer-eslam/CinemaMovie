package com.movie.cinema.cinemamovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.movie.cinema.cinemamovie.model.moviemodel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class  movieadpter extends BaseAdapter  {


    Context context;
    List<moviemodel> moviemodelList;
    LayoutInflater layoutInflater;
    public  movieadpter(Context context,List<moviemodel>moviemodels){

        this.context=context;
        this.moviemodelList=moviemodels;

        //case context not null
        if(context!=null){

            layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        }

    }
    @Override
    public int getCount() {
        return moviemodelList.size();
    }

    @Override
    public Object getItem(int position) {
        return moviemodelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        ImageView imageView;

        //case contentview is null
        if (convertView == null) {
            imageView=new ImageView(context);


            //load images movie from server by picasso
            Picasso.with(context).load("http://image.tmdb.org/t/p/w500/" +
                    moviemodelList.get(position).getPoster_path()).into(imageView);

        }else{

            imageView = (ImageView) convertView;

        }


        return  imageView;
    }



}