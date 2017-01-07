package com.movie.cinema.cinemamovie.movietask;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.movie.cinema.cinemamovie.R;
import com.movie.cinema.cinemamovie.adapter.movieadpatersreviews;
import com.movie.cinema.cinemamovie.fragment.detailsfragment;
import com.movie.cinema.cinemamovie.model.modelreviewmovies;

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
public class reviewsmovieasync  extends AsyncTask<String, String, List<modelreviewmovies>> {


    RecyclerView recyclerView;

    private String results = "results";
    private String id = "id";
    private String author = "author";
    private String content = "content";

    detailsfragment detailsfragment;
    private List<modelreviewmovies> modelreviewsmovieList;
    modelreviewmovies modelreviewsmovie;
    JSONObject jsonObject1;
    public reviewsmovieasync(detailsfragment detailsfragment){
        this.detailsfragment=detailsfragment;
    }

    @Override
    protected List<modelreviewmovies> doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            String finaljson = stringBuffer.toString();
            JSONObject jsonObject = new JSONObject(finaljson);
            JSONArray jsonArray = jsonObject.getJSONArray(results);
            modelreviewsmovieList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {

                jsonObject1 = jsonArray.getJSONObject(i);
                modelreviewsmovie = new modelreviewmovies();
                modelreviewsmovie.setAuthor(jsonObject1.getString(author));
                modelreviewsmovie.setContent(jsonObject1.getString(content));

                modelreviewsmovieList.add(modelreviewsmovie);


            }
            return modelreviewsmovieList;

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
    protected void onPostExecute(List<modelreviewmovies> modelreviewsmovies) {
        super.onPostExecute(modelreviewsmovies);
            try {
                recyclerView = (RecyclerView) detailsfragment.getActivity().findViewById(R.id.recyclerView2);
                recyclerView.setLayoutManager(new LinearLayoutManager(detailsfragment.getActivity().getApplicationContext()));
                movieadpatersreviews indianMoviesAdapter = new movieadpatersreviews(detailsfragment.getActivity().getApplicationContext(), modelreviewsmovies, detailsfragment);


                recyclerView.setAdapter(indianMoviesAdapter);
            }catch (Exception e){}



    }
}

