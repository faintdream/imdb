package com.akashdubey.imdb.network;

import android.support.v7.widget.LinearLayoutManager;

import com.akashdubey.imdb.DetailsScreen;
import com.akashdubey.imdb.adapter.CrewAdapter;
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

import static com.akashdubey.imdb.DetailsScreen.crewRV;
import static com.akashdubey.imdb.model.MovieDetailsModel.crewModelList;
import static com.akashdubey.imdb.network.MovieDetailsService.movieId;

/**
 * Created by homepc on 16-03-2018.
 */

public class CrewService {

    DetailsScreen detailsScreen = new DetailsScreen();
    CrewAdapter crewAdapter;

    private String crewUrl = "http://api.themoviedb.org/3/movie/" + movieId + "/credits?api_key=8496be0b2149805afa458ab8ec27560c";
    private String relativePath = "SOME_CRAP_PATH_AKASH";
    private String imgUrl =
            "http://image.tmdb.org/t/p/w45";

    private static final String CREW = "crew";
    private static final String NAME = "name";
    private static final String PROFILE = "profile_path";

    OkHttpClient okHttpClient = new OkHttpClient();
    Request request;
    Response response;
    JSONObject jsonObject1, jsonObject2, jsonObject3;
    JSONArray jsonArray;

    public void getCrew() {
        String url = crewUrl;

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
                    jsonArray = jsonObject1.getJSONArray(CREW);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject2 = jsonArray.getJSONObject(i);
                        relativePath = jsonObject2.getString(PROFILE);
                        imgUrl = "http://image.tmdb.org/t/p/w45" + relativePath;
                        crewModelList.add(new MovieDetailsModel(
                                "",
                                "",
                                imgUrl,
                                jsonObject2.getString(NAME)
                        ));

                        detailsScreen.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                PublishResultCrew(crewModelList);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void PublishResultCrew(List<MovieDetailsModel> crewModelList){
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(detailsScreen,LinearLayoutManager.HORIZONTAL,false);
        crewAdapter=new CrewAdapter(crewModelList);
        crewRV.setLayoutManager(linearLayoutManager);
        crewAdapter.notifyDataSetChanged();
        crewRV.setAdapter(crewAdapter);
    }
}
