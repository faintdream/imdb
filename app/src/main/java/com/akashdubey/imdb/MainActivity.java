package com.akashdubey.imdb;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.akashdubey.imdb.network.MyWebService;

import static com.akashdubey.imdb.WebList.myWebService;
import static com.akashdubey.imdb.db.DbHelper.dbHelper;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Bundle bundle;
    public static Cursor userMovieListcursor;
//public static MovieAdapter movieAdapter;
//public static  MyWebService myWebService ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWebService = new MyWebService();
        myWebService.getMostPopularMovies();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.in_app_choices, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.itemFavourite:
                Toast.makeText(this, "Favourite movies", Toast.LENGTH_SHORT).show();
                intent = new Intent(MainActivity.this, UserMovieList.class);
                bundle = new Bundle();
                bundle.putString("search", "favourites");
                bundle.putString("refresh", "no");
                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.itemWatchlist:
                Toast.makeText(this, "Watch bucket list", Toast.LENGTH_SHORT).show();
                intent = new Intent(MainActivity.this, UserMovieList.class);
                bundle = new Bundle();
                bundle.putString("search", "watchlater");
                bundle.putString("refresh", "no");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.itemRefresh:
                intent = new Intent(MainActivity.this, UserMovieList.class);
                bundle = new Bundle();
                bundle.putString("refresh", "yes");
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            case R.id.itemMostPopularMovies:
                intent = new Intent(MainActivity.this, WebList.class);
                bundle = new Bundle();
                bundle.putString("type", "popular");
                intent.putExtras(bundle);
                startActivity(intent);
//                myWebService.getMostPopularMovies();
                break;


            case R.id.itemUpcomingMovies:
                intent = new Intent(MainActivity.this, WebList.class);
                bundle = new Bundle();
                bundle.putString("type", "upcoming");
                intent.putExtras(bundle);
                startActivity(intent);
//                myWebService.getUpcomingMovies();
                break;

            case R.id.itemLatestMovies:
                Toast.makeText(this, "Latest Movies not functional", Toast.LENGTH_SHORT).show();
//                myWebService.getLatestMovies();
                break;

            case R.id.itemNowPlayingMovies:
                intent = new Intent(MainActivity.this, WebList.class);
                bundle = new Bundle();
                bundle.putString("type", "playing");
                intent.putExtras(bundle);
                startActivity(intent);
//                Toast.makeText(this, "Now Playing movies", Toast.LENGTH_SHORT).show();
//                myWebService.getNowPlayingMovies();
                break;
            case R.id.itemTopRatedMovies:
                intent = new Intent(MainActivity.this, WebList.class);
                bundle = new Bundle();
                bundle.putString("type", "toprated");
                intent.putExtras(bundle);
                startActivity(intent);
//                Toast.makeText(this, "Top rated movies", Toast.LENGTH_SHORT).show();
//                myWebService.getTopRatedMovies();
                break;
        }


        return super.onOptionsItemSelected(item);
    }


}
