package com.akashdubey.imdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.akashdubey.imdb.model.MovieModel;
import com.akashdubey.imdb.network.MyWebService;

public class MainActivity extends AppCompatActivity {
    static public RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView=findViewById(R.id.recyclerview1);
        MyWebService myWebService=new MyWebService();
        myWebService.getUpcomingMovies();
    }
}
