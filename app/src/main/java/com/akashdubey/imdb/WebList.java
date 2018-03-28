package com.akashdubey.imdb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.akashdubey.imdb.adapter.MovieAdapter;
import com.akashdubey.imdb.adapter.UserMovieListAdapter;
import com.akashdubey.imdb.db.DbHelper;
import com.akashdubey.imdb.network.MyWebService;

import static com.akashdubey.imdb.db.Constants.ID;
import static com.akashdubey.imdb.db.Constants.IS_FAVOURITE;
import static com.akashdubey.imdb.db.Constants.IS_WATCHLIST;
import static com.akashdubey.imdb.db.Constants.POPULARITY;
import static com.akashdubey.imdb.db.Constants.POSTER_PATH;
import static com.akashdubey.imdb.db.Constants.RELEASE_DATE;
import static com.akashdubey.imdb.db.Constants.TABLE_NAME;
import static com.akashdubey.imdb.db.Constants.TITLE;
import static com.akashdubey.imdb.db.Constants.VOTE_AVERAGE;
import static com.akashdubey.imdb.db.Constants.VOTE_COUNT;
import static com.akashdubey.imdb.db.DbHelper.dbHelper;
import static com.akashdubey.imdb.db.DbHelper.sqLiteDatabase;

/**
 * This class handles display of user choice movie data data
 */

public class WebList extends MainActivity {

    public static MovieAdapter movieAdapter;
    public static MyWebService myWebService;
    public static RecyclerView recyclerView;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview1);
        if (dbHelper == null) {
            dbHelper = new DbHelper(this);
        }

        String type="";
        try{
            type = getIntent().getExtras().getString("type");
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        if (type.isEmpty()) {
            type="xxx";
        } else {

            switch (type) {

                case "popular":
                    myWebService.getMostPopularMovies();
                    break;

                case "upcoming":
                    myWebService.getUpcomingMovies();
                    break;

                case "latest":
                    myWebService.getLatestMovies();
                    break;

                case "playing":
                    myWebService.getNowPlayingMovies();
                    break;

                case "toprated":
                    myWebService.getTopRatedMovies();
                    break;
            }

        }
    }
}

//        if (status.equals("yes") && userMovieListcursor != null) {
//            Toast.makeText(this, "Count: " + userMovieListcursor.getCount(), Toast.LENGTH_SHORT).show();
//        } else {
//
//
//            String category = getIntent().getExtras().getString("search");
//            String[] args = {"yes"};
//            dbHelper.openConnection();
//
//            if (category == null) {
//                Toast.makeText(this, "Nothing to show", Toast.LENGTH_SHORT).show();
//            } else {
//                switch (category) {
//                    case "favourites":
//                        userMovieListcursor = sqLiteDatabase.query(TABLE_NAME,
//                                new String[]{ID, TITLE, RELEASE_DATE, POSTER_PATH, POPULARITY, VOTE_AVERAGE,
//                                        VOTE_COUNT, IS_FAVOURITE, IS_WATCHLIST}, IS_FAVOURITE + "=?"
//                                , args, null, null, null);
//                        break;
//                    case "watchlater":
//                        userMovieListcursor = sqLiteDatabase.query(TABLE_NAME,
//                                new String[]{ID, TITLE, RELEASE_DATE, POSTER_PATH, POPULARITY, VOTE_AVERAGE,
//                                        VOTE_COUNT, IS_FAVOURITE, IS_WATCHLIST}, IS_WATCHLIST + "=?"
//                                , args, null, null, null);
//                        break;
//
//                    default:
//                        Toast.makeText(this, "Nothing to show", Toast.LENGTH_SHORT).show();
//                        break;
//
//                }
//
//                if (userMovieListcursor.getCount() < 1) {
//                    Toast.makeText(this, "Try adding some movies ", Toast.LENGTH_LONG).show();
//                } else {
//                    publishResultUserList(userMovieListcursor);
//
//
//                }
//
//            }
//        }
//
//    }
//
//
//    private void publishResultUserList(Cursor cursor) {
//        UserMovieListAdapter userMovieListAdapter = new UserMovieListAdapter(cursor);
//        umlRV.setLayoutManager(new LinearLayoutManager(mainActivity));
//        userMovieListAdapter.notifyDataSetChanged();
//        umlRV.setAdapter(userMovieListAdapter);
//    }


//}
