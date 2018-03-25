package com.akashdubey.imdb.network;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.akashdubey.imdb.DetailsScreen;
import com.akashdubey.imdb.MainActivity;
import com.akashdubey.imdb.adapter.MovieDetailAdapter;
import com.akashdubey.imdb.model.MovieDetailsModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.akashdubey.imdb.DetailsScreen.movieDetailRV;
import static com.akashdubey.imdb.model.MovieDetailsModel.movieDetailsModelList;

/**
 * Created by homepc on 13-03-2018.
 */

public class MovieDetailsService {

//    private String mTitle, mReleaseDate,
//            mPosterImage, mVoteAverage, mOverview, mBudget, mRevenue,
//            mTrailerImage, mCastImage, mCrewImage, mVoteCount;

    DetailsScreen detailsScreen = new DetailsScreen();
    MovieDetailAdapter movieDetailAdapter;
    MainActivity mainActivity=new MainActivity();
    private boolean isFavourite, isWatchLater;
    private static final String MOVIE_IMAGE = "poster_path";
    private static final String BUDGET = "budget";
    private static final String REVENUE = "revenue";
    private static final String TITLE = "original_title";
    private static final String OVERVIEW = "overview";
    private static final String RELEASE_DATE = "release_date";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String VOTE_COUNT = "vote_count";
    private static final String RELEASE_STATUS = "status";


    public static String movieId = "CRAP_BY_AKASH";
    private String movieDetailUrl
            = "http://api.themoviedb.org/3/movie/" + movieId + "?api_key=8496be0b2149805afa458ab8ec27560c";
    private String castUrl
            = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=8496be0b2149805afa458ab8ec27560c&append_to_response=cast";
    private String crewUrl
            = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=8496be0b2149805afa458ab8ec27560c&append_to_response=crew";
    private String trailerUrl
            = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=8496be0b2149805afa458ab8ec27560c&append_to_response=trailer";
    private String dynamicImageURL = "TBD.jpeg";
    private String imageBaseUrl =
            "http://image.tmdb.org/t/p/w92/" + dynamicImageURL;


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
                    dynamicImageURL = jsonObject.getString(MOVIE_IMAGE);
                    imageBaseUrl =
                            "http://image.tmdb.org/t/p/w500/" + dynamicImageURL;
                    movieDetailsModelList.add(new MovieDetailsModel(
                                    imageBaseUrl,
                                    jsonObject.getString(TITLE),
                                    jsonObject.getString(RELEASE_DATE),
                                    jsonObject.getString(VOTE_AVERAGE),
                                    jsonObject.getString(OVERVIEW),
                                    jsonObject.getString(BUDGET),
                                    jsonObject.getString(REVENUE),
                                    jsonObject.getString(VOTE_COUNT),
                                    jsonObject.getString(RELEASE_STATUS)

                            )
                    );

                    detailsScreen.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            publishResultMovieDetail(movieDetailsModelList);
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }


    public void publishResultMovieDetail(List<MovieDetailsModel> movieDetailsModelList) {
        movieDetailAdapter = new MovieDetailAdapter(movieDetailsModelList);
        movieDetailRV.setLayoutManager(new LinearLayoutManager(mainActivity));
        movieDetailAdapter.notifyDataSetChanged();
        movieDetailRV.setAdapter(movieDetailAdapter);

    }

    public interface MovieIdListener {
        public void setMovieId(String id);
    }

}
