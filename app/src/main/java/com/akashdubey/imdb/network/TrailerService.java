package com.akashdubey.imdb.network;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.akashdubey.imdb.DetailsScreen;
import com.akashdubey.imdb.adapter.TrailerAdapter;
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

import static com.akashdubey.imdb.DetailsScreen.trailerRV;
import static com.akashdubey.imdb.model.MovieDetailsModel.trailerModelList;
import static com.akashdubey.imdb.network.MovieDetailsService.movieId;

/**
 * this class handles webservice calls for fetching trailer details
 */

public class TrailerService {
    DetailsScreen detailsScreen = new DetailsScreen();
    TrailerAdapter trailerAdapter;

    private static final String RESULT = "results";
    private static final String TRAILER_KEY = "key";
    private static final String TRAILER_NAME = "name";
    private String trailerUrl
            = "https://api.themoviedb.org/3/movie/" + movieId + "/videos?api_key=8496be0b2149805afa458ab8ec27560c";


    private String imgBaseUrl;
    OkHttpClient okHttpClient = new OkHttpClient();
    Request request;
    JSONObject jsonObject1, jsonObject2;
    JSONArray jsonArray;

    public void getTrailer() {
        String url = trailerUrl;
        request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String myResponse = response.body().string();
                try {
                    jsonObject1 = new JSONObject(myResponse);
                    jsonArray = jsonObject1.getJSONArray(RESULT);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject2 = jsonArray.getJSONObject(i);
//                      Log.i("LEGO","Video key :"+jsonObject2.getString("key"));
                        trailerModelList.add(new MovieDetailsModel
                                (
                                   jsonObject2.getString(TRAILER_KEY),
                                   jsonObject2.getString(TRAILER_NAME)
                                ));

                    }


                    detailsScreen.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            PublishResultTrailer(trailerModelList);
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void PublishResultTrailer(List<MovieDetailsModel> trailerModelList) {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(detailsScreen, LinearLayoutManager.HORIZONTAL, false);
        trailerAdapter = new TrailerAdapter(trailerModelList);
        trailerRV.setLayoutManager(layoutManager);

        trailerAdapter.notifyDataSetChanged();
        trailerRV.setAdapter(trailerAdapter);
    }


}
