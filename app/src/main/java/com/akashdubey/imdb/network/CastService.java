package com.akashdubey.imdb.network;

import android.support.v7.widget.LinearLayoutManager;

import com.akashdubey.imdb.DetailsScreen;
import com.akashdubey.imdb.adapter.CastAdapter;
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

import static com.akashdubey.imdb.DetailsScreen.castRV;
import static com.akashdubey.imdb.model.MovieDetailsModel.castModelList;
import static com.akashdubey.imdb.network.MovieDetailsService.movieId;

/**
 * This class handles fetch movie star cast from webservice
 */

public class CastService {

    DetailsScreen detailsScreen= new DetailsScreen();
    CastAdapter castAdapter;


    private String castUrl=
            "http://api.themoviedb.org/3/movie/"+movieId+"/credits?api_key=8496be0b2149805afa458ab8ec27560c";

    private String relativePath="SOME_CRAP_PATH_AKASH";
    private String imgUrl=
            "http://image.tmdb.org/t/p/w45";


    private static final String CAST="cast";
    private static final String NAME="name";
    private static final String PROFILE="profile_path";

    OkHttpClient okHttpClient=new OkHttpClient();
    Request request;
    Response response;
    JSONObject jsonObject1, jsonObject2, jsonObject3;
    JSONArray jsonArray;


    public void getCast(){
        String url=castUrl;
        request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String myResponse=response.body().string();
                try {
                    jsonObject1=new JSONObject(myResponse);
                    jsonArray= jsonObject1.getJSONArray(CAST);
                    for (int i=0;i<jsonArray.length();i++){
                        jsonObject2=jsonArray.getJSONObject(i);
                        relativePath=jsonObject2.getString(PROFILE);
                        imgUrl="http://image.tmdb.org/t/p/w45"+relativePath;
                        castModelList.add(new MovieDetailsModel("",
                                imgUrl,
                                jsonObject2.getString(NAME)
                                ));
                    }
                    detailsScreen.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            PublishResultCast(castModelList);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

        public void PublishResultCast(List<MovieDetailsModel> castModelList){
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(detailsScreen,LinearLayoutManager.HORIZONTAL,false);
            castAdapter=new CastAdapter(castModelList);
            castRV.setLayoutManager(layoutManager);
            castAdapter.notifyDataSetChanged();
            castRV.setAdapter(castAdapter);
        }
}
