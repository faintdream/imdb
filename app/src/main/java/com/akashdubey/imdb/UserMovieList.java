package com.akashdubey.imdb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import static com.akashdubey.imdb.MainActivity.userMovieListcursor;
import static com.akashdubey.imdb.db.Constants.*;

import com.akashdubey.imdb.adapter.UserMovieListAdapter;


import static com.akashdubey.imdb.db.DbHelper.dbHelper;
import static com.akashdubey.imdb.db.DbHelper.sqLiteDatabase;

/**
 * This class handles display of user choice movie data data
 */

public class UserMovieList extends MainActivity {
    MainActivity mainActivity = new MainActivity();

    RecyclerView umlRV;
//    public static Cursor cursor;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_movie_list_view);
        umlRV = findViewById(R.id.umlRV);
        String status = getIntent().getExtras().getString("refresh");

        if (status.equals("yes") && userMovieListcursor != null) {
            Toast.makeText(this, "Count: " + userMovieListcursor.getCount(), Toast.LENGTH_SHORT).show();
        } else {


            String category = getIntent().getExtras().getString("search");
            String[] args = {"yes"};
            dbHelper.openConnection();

            if (category == null) {
                Toast.makeText(this, "Nothing to show", Toast.LENGTH_SHORT).show();
            } else {
                switch (category) {
                    case "favourites":
                        userMovieListcursor = sqLiteDatabase.query(TABLE_NAME,
                                new String[]{ID, TITLE, RELEASE_DATE, POSTER_PATH, POPULARITY, VOTE_AVERAGE,
                                        VOTE_COUNT, IS_FAVOURITE, IS_WATCHLIST}, IS_FAVOURITE + "=?"
                                , args, null, null, null);
                        break;
                    case "watchlater":
                        userMovieListcursor = sqLiteDatabase.query(TABLE_NAME,
                                new String[]{ID, TITLE, RELEASE_DATE, POSTER_PATH, POPULARITY, VOTE_AVERAGE,
                                        VOTE_COUNT, IS_FAVOURITE, IS_WATCHLIST}, IS_WATCHLIST + "=?"
                                , args, null, null, null);
                        break;

                    default:
                        Toast.makeText(this, "Nothing to show", Toast.LENGTH_SHORT).show();
                        break;

                }

                if (userMovieListcursor.getCount() < 1) {
                    Toast.makeText(this, "Try adding some movies ", Toast.LENGTH_LONG).show();
                } else {
                    publishResultUserList(userMovieListcursor);


                }

            }
        }

    }


    private void publishResultUserList(Cursor cursor) {
        UserMovieListAdapter userMovieListAdapter = new UserMovieListAdapter(cursor);
        umlRV.setLayoutManager(new LinearLayoutManager(mainActivity));
        userMovieListAdapter.notifyDataSetChanged();
        umlRV.setAdapter(userMovieListAdapter);
    }


}
