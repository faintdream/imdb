package com.akashdubey.imdb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.akashdubey.imdb.MainActivity;
import com.akashdubey.imdb.db.Constants;

import static com.akashdubey.imdb.db.Constants.*;

/**
 * Created by homepc on 20-03-2018.
 */

public class Db extends SQLiteOpenHelper{
    Context context;
    String query="create table if not exists "+ TABLE_NAME+"("+
            ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            TITLE+" VARCHAR, "+
            RELEASE_DATE+" VARCHAR, "+
            POSTER_PATH+" VARCHAR, "+
            POPULARITY+" VARCHAR, "+
            VOTE_AVERAGE+" VARCHAR, "+
            VOTE_COUNT+" VARCHAR, "+
            IS_FAVOURITE+" VARCHAR, "+
            IS_WATCHLIST+" VARCHAR );";



    public Db(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(query);
            Log.i("LEGO", "schema:" + query);
        }catch (Exception e){
            Toast.makeText(context, "Exception encountered, Please visit application stacktrace."
                    , Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.delete(TABLE_NAME, null, null);
            sqLiteDatabase.execSQL(query);
            Log.i("LEGO", "onUpgrade() : " + query);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
}
