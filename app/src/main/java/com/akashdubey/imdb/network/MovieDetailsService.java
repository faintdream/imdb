package com.akashdubey.imdb.network;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by homepc on 13-03-2018.
 */

public class MovieDetailsService {


    private static final String BUDGET = "budget";
    private static final String REVENUE = "TBD";
    private static final String TITLE = "original_title";
    private static final String OVERVIEW = "overview";
    private static final String RELEASE_DATE = "TBD";
    private static final String VOTE_AVERAGE = "TBD";


    public static String movieId = "19404";
    private String movieDetailUrl
            = "http://api.themoviedb.org/3/movie/" + movieId + "?api_key=8496be0b2149805afa458ab8ec27560c";
    private String posterUrl
            = "http://api.themoviedb.org/3/movie/" + movieId + "/images?api_key=8496be0b2149805afa458ab8ec27560c";
    private String castCrewUrl
            = "http://api.themoviedb.org/3/movie/" + movieId + "/credits?api_key=8496be0b2149805afa458ab8ec27560c";
    private String trailerUrl
            = "http://api.themoviedb.org/3/movie/" + movieId + "/videos?api_key=8496be0b2149805afa458ab8ec27560c";

    private String dynamicImageURL = "TBD.jpeg";
    private String imageBaseUrl =
            "http://image.tmdb.org/t/p/w500/" + dynamicImageURL;


    OkHttpClient okHttpClient = new OkHttpClient();
    Request request;
    JSONObject jsonObject;

    public void getMovieDetail() {
        String url = movieDetailUrl;

        request = new Request.Builder().url(url).build(); // building the http request okHttp way
        okHttpClient.newCall(request).enqueue(new Callback() { //sending the request in separate child thread
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String myResponse = response.body().string().toString(); // collecting the result in String object
                try {
                    jsonObject = new JSONObject(myResponse);

                    Log.i("LEGO", jsonObject.getString(TITLE));

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    public void getPoster() {


    }

    public void getTrailer() {

    }

    public void getCast() {

    }

    public void getCrew() {

    }

    public interface MovieIdListener {
        public void setMovieId(String id);
    }
}
