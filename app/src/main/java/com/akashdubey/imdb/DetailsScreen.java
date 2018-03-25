package com.akashdubey.imdb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.akashdubey.imdb.model.MovieDetailsModel;
import com.akashdubey.imdb.network.CastService;
import com.akashdubey.imdb.network.CrewService;
import com.akashdubey.imdb.network.MovieDetailsService;
import com.akashdubey.imdb.network.PosterService;
import com.akashdubey.imdb.network.TrailerService;

import static com.akashdubey.imdb.network.MovieDetailsService.*;

/**
 * This class loads the UI for details screen and set the movie id for
 * further processing
 */


public class DetailsScreen extends MainActivity implements MovieIdListener {
    MovieIdListener movieIdListener = (MovieIdListener) this;
    public static RecyclerView movieDetailRV, posterRV, trailerRV, castRV, crewRV;
    String movieId;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MovieDetailsModel.movieDetailsModelList.clear();
        MovieDetailsModel.posterModelList.clear();
        MovieDetailsModel.trailerModelList.clear();
        MovieDetailsModel.castModelList.clear();
        MovieDetailsModel.crewModelList.clear();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_screen_view);
        movieDetailRV = findViewById(R.id.movieDetailRV);
        posterRV = findViewById(R.id.posterRV);
        trailerRV = findViewById(R.id.trailerRV);
        castRV = findViewById(R.id.dtlCastRV);
        crewRV = findViewById(R.id.crewRV);
        movieId = getIntent().getExtras().getString("movieId");
        movieIdListener.setMovieId(movieId);
        MovieDetailsService movieDetailsService = new MovieDetailsService();
        movieDetailsService.getMovieDetail();

        PosterService posterService = new PosterService();
        posterService.getPoster();

        TrailerService trailerService = new TrailerService();
        trailerService.getTrailer();

        CastService castService = new CastService();
        castService.getCast();

        CrewService crewService = new CrewService();
        crewService.getCrew();
    }


    @Override
    public void setMovieId(String id) {
        MovieDetailsService.movieId = id;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
