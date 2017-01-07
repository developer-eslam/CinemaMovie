package com.movie.cinema.cinemamovie.movietask;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.movie.cinema.cinemamovie.R;
import com.movie.cinema.cinemamovie.adapter.videosmovieadpater;
import com.movie.cinema.cinemamovie.fragment.detailsfragment;
import com.movie.cinema.cinemamovie.model.modelvideo;

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
public class videomoviejson  extends AsyncTask<String , String ,List<modelvideo>> {

    private String results = "results";
    private String key =  "key";
    private String type = "type";
    private List<modelvideo>modelvideos;
    JSONObject jsonObject1;
    modelvideo modelvideo;
    RecyclerView recyclerView;
    detailsfragment detailsfragment;

    public videomoviejson(detailsfragment detailsfragment){
        this.detailsfragment=detailsfragment;
    }
    @Override
    protected List<modelvideo> doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);

            HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            httpURLConnection.connect();
            StringBuffer stringBuffer =new StringBuffer();
            String line;
            while ((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
            String finaljson = stringBuffer.toString();
            JSONObject jsonObject = new JSONObject(finaljson);
            JSONArray jsonArray = jsonObject.getJSONArray(results);
            modelvideos = new ArrayList<>();
            for(int i = 0 ; i <jsonArray.length() ; i++){

                jsonObject1 = jsonArray.getJSONObject(i);
                modelvideo = new modelvideo();
                modelvideo.setType(jsonObject1.getString(type));
                modelvideo.setKey(jsonObject1.getString(key));


                modelvideos.add(modelvideo);


            }
            return  modelvideos;

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
    protected void onPostExecute(List<modelvideo> modelvideos) {
        super.onPostExecute(modelvideos);


        try {
            recyclerView = (RecyclerView) detailsfragment.getActivity().findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(detailsfragment.getActivity().getApplicationContext()));
            videosmovieadpater indianMoviesAdapter = new videosmovieadpater(detailsfragment.getActivity().getApplicationContext(), modelvideos, detailsfragment);


            recyclerView.setAdapter(indianMoviesAdapter);
        }catch (Exception e){

        }





    }
}






