package com.movie.cinema.cinemamovie.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.movie.cinema.cinemamovie.fragment.detailsfragment;
public class moviedatabase extends SQLiteOpenHelper {



    detailsfragment detailsfragment;
    moviedataspsifcdatabase.moviedata moviedata;

    //database name
    static  final  String DATABASE_NAME = "moviesdatabase";
    //database version
    static  final  int DATABSE_VERSION  = 1;
    public moviedatabase(Context context,detailsfragment detailsfragment){

        super(context ,DATABASE_NAME, null , DATABSE_VERSION);
        this.detailsfragment=detailsfragment;
    }

    //method to create database
    @Override
    public void onCreate(SQLiteDatabase db) {

        //statment sql to create database
        String sql = "CREATE TABLE "
                + moviedata.TABLE_NAME + " ("
                + moviedata._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + moviedata.MOVIE_ID + " INTEGER NOT NULL UNIQUE,"
                + moviedata.MOVIE_TITLE + " TEXT,"
                + moviedata.MOVIE_OVERVEW + " varchar(50),"
                + moviedata.MOVIE_POPULARITY + " INTEGER" +")";

        //execute database
        db.execSQL(sql);

    }

    //upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS " + moviedata.TABLE_NAME);
        onCreate(db);
    }


    //method to save fovritemovie
    public void savefavoritemovie(String id,String original_title,String  overview){

        //write database
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //create object from class contentvalues to insert data in database
        ContentValues values =new ContentValues();


        values.put(moviedata.MOVIE_ID , id);
        values.put(moviedata.MOVIE_TITLE ,  original_title);
        values.put(moviedata.MOVIE_OVERVEW , overview);

        //statment insert in sql
        sqLiteDatabase.insert(moviedata.TABLE_NAME , null , values);

    }


    //retrive spesifc movie from database
    public Cursor getmoviesfavorite(SQLiteDatabase sqLiteDatabase){
        String []movieatributes  = {moviedata.MOVIE_TITLE,moviedata.MOVIE_OVERVEW};
        Cursor cursor =  sqLiteDatabase.query(moviedata.TABLE_NAME,movieatributes,null,null,null,null,null);
        return  cursor;
    }


}
