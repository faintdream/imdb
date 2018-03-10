package com.akashdubey.imdb.network;

import android.support.v7.widget.LinearLayoutManager;

import com.akashdubey.imdb.MainActivity;
//import com.akashdubey.imdb.model.MovieAdapter;
import com.akashdubey.imdb.adapter.MovieAdapter;
import com.akashdubey.imdb.model.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.akashdubey.imdb.MainActivity.recyclerView;
import static com.akashdubey.imdb.model.MovieModel.movieModelList;
//import static com.akashdubey.imdb.model.MovieModel;

/**
 * Created by homepc on 07-03-2018.
 */

public class MyWebService {
    public static final String VOTE_COUNT="vote_count";
    public static final String VOTE_AVERAGE="vote_average";
    public static final String POPULARITY="popularity";
    public static final String MOVIE_IMAGE="poster_path";
    public static final String MOVIE_TITLE="title";
    public static final String RELEASE_DATE="release_date";


    MainActivity mainActivity=new MainActivity();
    OkHttpClient okHttpClient=new OkHttpClient();
    Request request;
    JSONObject jsonObject;
    JSONArray jsonArray;
//    private MovieModel movieModel;


    void getMostPopularMovies(){
        String url="";
    }

    public void getUpcomingMovies(){

        String url="http://api.themoviedb.org/3/movie/upcoming?api_key=8496be0b2149805afa458ab8ec27560c";
        request= new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String myResponse=response.body().string().toString();
                try {
                    jsonObject= new JSONObject(myResponse);
                    jsonArray=jsonObject.getJSONArray("results");
                    for (int i=0;i<jsonArray.length();i++){

                        jsonObject=jsonArray.getJSONObject(i);
                        String imgBaseUrl="https://image.tmdb.org/t/p/w185"+jsonObject.getString(MOVIE_IMAGE);
                        movieModelList.add(new MovieModel(
                                jsonObject.getString(MOVIE_TITLE),
                                jsonObject.getString(RELEASE_DATE),
                                jsonObject.getString(POPULARITY),
                                jsonObject.getString(VOTE_COUNT),
                                imgBaseUrl,
                                jsonObject.getString(VOTE_AVERAGE)
                                )
                        );
                    }

                    mainActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            publishResult(movieModelList);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    void getLatestMovies(){
        String url="";
    }

    void getNowPlayingMovies(){
        String url="";
    }

    void getTopRatedMovies(){
        String url="";
    }


    private void publishResult(List<MovieModel> movieModelList) {


         recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));
         MovieAdapter movieAdapter= new MovieAdapter(movieModelList);
         recyclerView.setAdapter(movieAdapter);
//       mainActivity.customListViewAdapter = new CustomListViewAdapter(movieAdapters);
//       rv.setAdapter(new CustomListViewAdapter(MovieAdapter.movieList));
    }
}
