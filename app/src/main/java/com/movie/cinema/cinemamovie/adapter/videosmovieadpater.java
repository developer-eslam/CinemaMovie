package com.movie.cinema.cinemamovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.movie.cinema.cinemamovie.R;
import com.movie.cinema.cinemamovie.fragment.detailsfragment;
import com.movie.cinema.cinemamovie.model.modelvideo;

import java.util.List;


public class videosmovieadpater extends  RecyclerView.Adapter<videosmovieadpater.Holder> {
    List<modelvideo> modelvideoList;

     Context Context;
    detailsfragment detailsfragment;

    public videosmovieadpater(Context context, List<modelvideo> holderList, detailsfragment detailsfragment){

        this.modelvideoList=holderList;
        this.Context=context;
        this.detailsfragment=detailsfragment;

    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_android_version,parent,false);
        Holder holder=new Holder(row);
        return holder;
    }


    @Override
    public void onBindViewHolder(Holder holder, final int position) {


        holder.traillers.setText(modelvideoList.get(position).getType() + position);

        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsfragment.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + modelvideoList.get(position).getKey())));

            }
        });

    }
    @Override
    public int getItemCount()
    {
        if(modelvideoList!=null){

            return modelvideoList.size();

        }
        return  0;
    }


    public class Holder extends RecyclerView.ViewHolder{


        TextView traillers;
        Button play;

        public Holder(View itemView) {

            super(itemView);
            traillers=(TextView)itemView.findViewById(R.id.txttarillers);
            play =(Button)itemView.findViewById(R.id.btnplay);


        }
    }
}


