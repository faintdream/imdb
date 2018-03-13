package com.akashdubey.imdb.network;

import com.akashdubey.imdb.DetailsScreen;
import com.akashdubey.imdb.DetailsScreen.MovieIdListener;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by homepc on 13-03-2018.
 */

public class MovieDetailsService  implements MovieIdListener{

    private String movieId="TBD";
    private String movieDetailUrl
            ="http://api.themoviedb.org/3/movie/"+movieId+"?api_key=8496be0b2149805afa458ab8ec27560c";
    private String posterUrl
            ="http://api.themoviedb.org/3/movie/"+movieId+"/images?api_key=8496be0b2149805afa458ab8ec27560c";
    private String castCrewUrl
            ="http://api.themoviedb.org/3/movie/"+movieId+"/credits?api_key=8496be0b2149805afa458ab8ec27560c";
    private String trailerUrl
            ="http://api.themoviedb.org/3/movie/"+movieId+"/videos?api_key=8496be0b2149805afa458ab8ec27560c";

    private String dynamicImageURL="TBD.jpeg";
    private String imageBaseUrl=
            "http://image.tmdb.org/t/p/w500/"+dynamicImageURL;


    OkHttpClient okHttpClient = new OkHttpClient();
    Request request;
    JSONObject jsonObject;

 public void getMovieDetail(){

     String url=movieDetailUrl;


 }
 public void getPoster(){


 }
 public  void getTrailer(){

 }
 public void getCast(){

 }
 public void getCrew(){

 }

    @Override
    public String setMovieId(String id) {
        this.movieId=id;
        return  this.movieId;
    }
}
