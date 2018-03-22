package com.akashdubey.imdb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import static com.akashdubey.imdb.db.Constants.*;
import com.akashdubey.imdb.adapter.UserMovieListAdapter;

import static com.akashdubey.imdb.MainActivity.recyclerView;
import static com.akashdubey.imdb.db.DbHelper.dbHelper;
import static com.akashdubey.imdb.db.DbHelper.sqLiteDatabase;

/**
 * Created by homepc on 22-03-2018.
 */

public class UserMovieList extends AppCompatActivity {
    RecyclerView umlRV;
    Cursor cursor;
//    private CursorListener cursorListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_movie_list_view);
        umlRV = findViewById(R.id.umlRV);
        String [] args={"yes"};
        dbHelper.openConnection();
        cursor = sqLiteDatabase.query(TABLE_NAME,
                new String[]{ID, TITLE, RELEASE_DATE, POSTER_PATH, POPULARITY, VOTE_AVERAGE,
                        VOTE_COUNT, IS_FAVOURITE, IS_WATCHLIST}, IS_FAVOURITE + "=?"
                , args, null, null, null);
        if (cursor.getCount()<1){
            Toast.makeText(this, "Try adding some of your favourite movies ", Toast.LENGTH_LONG).show();
        }else{
                publishResultUserList(cursor);

        }



    }
        private void publishResultUserList(Cursor cursor) {
//        MainActivity mainActivity = new MainActivity();
            UserMovieListAdapter userMovieListAdapter = new UserMovieListAdapter(cursor);
            umlRV.setLayoutManager(new LinearLayoutManager(this));
            userMovieListAdapter.notifyDataSetChanged();
            umlRV.setAdapter(userMovieListAdapter);
        }




}
