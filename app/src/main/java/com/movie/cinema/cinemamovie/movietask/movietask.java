package com.movie.cinema.cinemamovie.movietask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.movie.cinema.cinemamovie.adapter.movieadpter;
import com.movie.cinema.cinemamovie.fragment.detailsfragment;
import com.movie.cinema.cinemamovie.fragment.mainfragment;
import com.movie.cinema.cinemamovie.model.moviemodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class movietask extends AsyncTask<String,String,List<moviemodel>> {

    //attributes member data class
    Bundle bundle;

    GridView gridView;
    JSONObject jsonObject;
    mainfragment mainfragment;
    JSONArray jsonArray;
    detailsfragment detailsfragment;

    moviemodel moviemodel;
    private List<moviemodel> moviemodels;
    private String poster_path = "poster_path";
    private String jsonresut = "results";
    String original_title = "original_title";
    String popularity = "popularity";
    String release_date = "release_date";
    String vote_count = "vote_count";
    String vote_average = "vote_average";
    public  movietask(mainfragment mainfragment,GridView gview)
    {
        this.gridView=gview;
        this.mainfragment=mainfragment;

    }

    @Override
    protected List<moviemodel> doInBackground(String... params) {


        try {

            URL url = new URL(params[0]);

            //open connection
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //retrive data after connection
            httpURLConnection.setRequestMethod("GET");
            //conenct
            httpURLConnection.connect();

            //read text url line by line by stream
            InputStream inputStream = httpURLConnection.getInputStream();
            //read inputsream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            //check read not null
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            String json = stringBuffer.toString();
            //intailtion jsonobject and get result by array
            jsonObject = new JSONObject(json);

            jsonArray = jsonObject.getJSONArray(jsonresut);
            moviemodels = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {


                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                //calling method settter from class moviemodel to display data
                 moviemodel = new moviemodel();
                moviemodel.setPoster_path(jsonObject1.getString(poster_path));
                moviemodel.setOriginal_title(jsonObject1.getString(original_title));
                moviemodel.setVote_count(jsonObject1.getString(vote_count));
                moviemodel.setVote_average(jsonObject1.getString(vote_average));
                moviemodel.setPopularity(Float.parseFloat(jsonObject1.getString(popularity)));
                moviemodel.setRelease_date((jsonObject1.getString(release_date)));
                moviemodel.setOverview(jsonObject1.getString("overview"));
                moviemodel.setId(jsonObject1.getString("id"));

                //add movie after calling data moviemodel
                moviemodels.add(moviemodel);
            }
            return moviemodels;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    protected void onPostExecute(final List<moviemodel> moviemodels) {
        super.onPostExecute(moviemodels);

        //check gridview and mainfragment not null
        if(gridView!=null){
            if(mainfragment!=null ){
                 detailsfragment = new detailsfragment();
                bundle = new Bundle();

                final movieadpter movieadpater = new movieadpter(mainfragment.getActivity(), moviemodels);
                mainfragment.gridView.setAdapter(movieadpater);


                mainfragment. gridView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        ((com.movie.cinema.cinemamovie.movieinterface.movieinterface)mainfragment.getActivity()).setselectedmovie(moviemodels.get(position));
                    }

                });
            }

        }
    }





}


