package com.akashdubey.imdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * This class loads the UI for details screen and set the movie id for
 * further processing
 */

public class DetailsScreen extends AppCompatActivity {
    MovieIdListener movieIdListener;
    String movieId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view);
        movieId=getIntent().getExtras().getString("movieId");
        movieIdListener.setMovieId(movieId);
//        Intent intent= new Intent();
//        Bundle bundle=intent.getExtras();

        Toast.makeText(this, "Movie Id "+getIntent().getExtras().getString("movieId"), Toast.LENGTH_LONG).show();
    }

    public interface MovieIdListener{
        public String setMovieId(String id);
    }
}
