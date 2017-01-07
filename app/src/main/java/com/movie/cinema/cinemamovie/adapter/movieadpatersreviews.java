package com.movie.cinema.cinemamovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movie.cinema.cinemamovie.R;
import com.movie.cinema.cinemamovie.model.modelreviewmovies;
import com.movie.cinema.cinemamovie.fragment.detailsfragment;

import java.util.List;


public class movieadpatersreviews extends  RecyclerView.Adapter<movieadpatersreviews.Holder> {

    //member data
    List<modelreviewmovies> androidmodels;

     Context Context;
    modelreviewmovies moviemodel;

    detailsfragment detailsfragment;
    public movieadpatersreviews(Context context, List<modelreviewmovies> holderList,detailsfragment detailsfragment){

        this.androidmodels=holderList;
        this.Context=context;
        this.detailsfragment=detailsfragment;

        moviemodel=new modelreviewmovies();
    }



    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_item,parent,false);
        Holder holder=new Holder(row);
        return holder;
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {

        //create object form class StringBuffer to append data
        StringBuffer stringBuffer1=new StringBuffer();
        StringBuffer stringBuffer2=new StringBuffer();

        stringBuffer1.append("author : "+"\n");
        stringBuffer2.append("content: "+"\n");

        holder.author.setText(stringBuffer1 + androidmodels.get(position).getAuthor());

        holder.content.setText(stringBuffer2 + androidmodels.get(position).getContent());

    }
    //get item count from class moviemodel
    @Override
    public int getItemCount()
    {
        if(androidmodels!=null){
            return androidmodels.size();

        }
        return  0;
    }



    public class Holder extends RecyclerView.ViewHolder{


        TextView author,content;

        public Holder(View itemView) {

            super(itemView);
            author=(TextView)itemView.findViewById(R.id.txtauother);
            content=(TextView)itemView.findViewById(R.id.txtcontent);


        }
    }
}





