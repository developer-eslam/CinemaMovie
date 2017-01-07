package com.movie.cinema.cinemamovie.database;

import android.provider.BaseColumns;

public class moviedataspsifcdatabase
{

    public  abstract static  class  moviedata implements BaseColumns{
        //attributes columns database
        public static  final  String TABLE_NAME = "Favorite";
        public  static  final  String MOVIE_ID = "id";
        public static  final  String MOVIE_TITLE = "title";
        public static  final  String MOVIE_OVERVEW = "overview";
        public static  final  String MOVIE_POPULARITY = "popularity";

    }
}
