package com.movie.cinema.cinemamovie.model;

import java.io.Serializable;


public class moviemodel implements Serializable {



    // attributes data class
    private String poster_path;
    private String overview;
    private String original_title;
    private String title;
    private String backdrop_path;
    private float popularity;
    private String release_date;
    private  String  id;
    private String vote_count;
    private  String vote_average;

    public moviemodel( ){}





    public  moviemodel(String oringal_title,String overview){

        this.original_title=oringal_title;
        this.overview=overview;
    }
    //setter and getter spesfic attributes data class

    public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public float getPopularity() {
            return popularity;
        }

        public void setPopularity(float popularity) {
            this.popularity = popularity;
        }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }
}

