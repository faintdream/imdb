package com.akashdubey.imdb.network;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.akashdubey.imdb.DetailsScreen;
import com.akashdubey.imdb.adapter.PosterAdapter;
import com.akashdubey.imdb.model.MovieDetailsModel;

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

import static com.akashdubey.imdb.DetailsScreen.posterRV;
import static com.akashdubey.imdb.model.MovieDetailsModel.posterModelList;
import static com.akashdubey.imdb.network.MovieDetailsService.movieId;

/**
 * this class handles the web service fetch for posters for corresponding moveid
 */

public class PosterService {

    DetailsScreen detailsScreen = new DetailsScreen();
    PosterAdapter posterAdapter;
    private static final String IMAGE = "images";
    private static final String POSTER = "posters";
    private static final String POSTER_PATH = "file_path";
    private String posterUrl
            = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=8496be0b2149805afa458ab8ec27560c&append_to_response=images";

    private String imgBaseUrl;
    OkHttpClient okHttpClient = new OkHttpClient();
    Request request;
    JSONObject jsonObject1, jsonObject2, jsonObject3;
    JSONArray jsonArray;

    public void getPoster() {
        String url = posterUrl;
//        Log.i("LEGO", url);
        request = new Request.Builder().url(url).build();// building the http url to fetch poster path
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String myResponse = response.body().string();
                try {
                    jsonObject1 = new JSONObject(myResponse);
                    jsonObject2 = jsonObject1.getJSONObject(IMAGE);
                    jsonArray = jsonObject2.getJSONArray(POSTER);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject3 = jsonArray.getJSONObject(i);
//                        Log.i("LEGO", "path:" + jsonObject3.getString(POSTER_PATH).toString());
                        imgBaseUrl = "https://image.tmdb.org/t/p/w45" + jsonObject3.getString(POSTER_PATH);
//                        Log.i("LEGO", "url:" + imgBaseUrl);
                        posterModelList.add(new MovieDetailsModel(imgBaseUrl));
                        detailsScreen.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                PublishResultPoster(posterModelList);
                            }
                        });

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    public void PublishResultPoster(List<MovieDetailsModel> posterModelList) {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(detailsScreen, LinearLayoutManager.HORIZONTAL, false);
        posterAdapter = new PosterAdapter(posterModelList);
        posterRV.setLayoutManager(layoutManager);
        posterAdapter.notifyDataSetChanged();
        posterRV.setAdapter(posterAdapter);
    }
}
