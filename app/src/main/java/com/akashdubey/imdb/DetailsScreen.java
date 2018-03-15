package com.akashdubey.imdb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.akashdubey.imdb.network.MovieDetailsService;
import com.akashdubey.imdb.network.PosterService;

import static com.akashdubey.imdb.network.MovieDetailsService.*;

/**
 * This class loads the UI for details screen and set the movie id for
 * further processing
 */


public class DetailsScreen extends AppCompatActivity implements MovieIdListener{
    MovieIdListener movieIdListener = (MovieIdListener)this ;
    public static RecyclerView movieDetailRV,posterRV;
    String movieId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_screen_view);
        movieDetailRV=findViewById(R.id.movieDetailRV);
        posterRV=findViewById(R.id.posterRV);
        movieId=getIntent().getExtras().getString("movieId");
        movieIdListener.setMovieId(movieId);
        MovieDetailsService movieDetailsService= new MovieDetailsService();
        movieDetailsService.getMovieDetail();

        PosterService posterService=new PosterService();
        posterService.getPoster();

        Toast.makeText(this, "Movie Id "+getIntent().getExtras().getString("movieId"), Toast.LENGTH_LONG).show();
    }


    @Override
    public void setMovieId(String id) {

        MovieDetailsService.movieId=id;

    }
}
