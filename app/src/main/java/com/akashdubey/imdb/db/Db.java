package com.akashdubey.imdb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
            IS_WATCHLIST+" VARCHAR, UNIQUE ON CONFLICT REPLACE );";



    public Db(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
//        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        context.deleteDatabase(DB_NAME);
    }
}
