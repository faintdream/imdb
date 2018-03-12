package com.akashdubey.imdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by homepc on 12-03-2018.
 */

public class DetailsScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view);

//        Intent intent= new Intent();
//        Bundle bundle=intent.getExtras();

        Toast.makeText(this, "Movie Id "+getIntent().getExtras().getString("movieId"), Toast.LENGTH_LONG).show();
    }

}
