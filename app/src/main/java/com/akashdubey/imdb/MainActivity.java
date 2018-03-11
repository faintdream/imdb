package com.akashdubey.imdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.akashdubey.imdb.network.MyWebService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static public RecyclerView recyclerView;
    MyWebService myWebService ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview1);
        myWebService= new MyWebService();
        myWebService.getUpcomingMovies();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.in_app_choices, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.itemFavourite:
                break;
            case R.id.itempWatchlist:
                break;
            case R.id.itemRefresh:
                break;

            case R.id.itemMostPopularMovies:
                Toast.makeText(this, "Popular movies", Toast.LENGTH_SHORT).show();
                myWebService.getMostPopularMovies();
                break;

            case R.id.itemUpcomingMovies:
                Toast.makeText(this, "Upcoming movies", Toast.LENGTH_SHORT).show();
                myWebService.getUpcomingMovies();
                break;

            case R.id.itemLatestMovies:
                Toast.makeText(this, "Latest Movies", Toast.LENGTH_SHORT).show();
                myWebService.getLatestMovies();
                break;

            case R.id.itemNowPlayingMovies:
                Toast.makeText(this, "Now Playing movies", Toast.LENGTH_SHORT).show();
                myWebService.getNowPlayingMovies();
                break;
            case R.id.itemTopRatedMovies:
                Toast.makeText(this, "Top rated movies", Toast.LENGTH_SHORT).show();
                myWebService.getTopRatedMovies();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }

}
