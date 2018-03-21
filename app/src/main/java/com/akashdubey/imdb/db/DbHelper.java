package com.akashdubey.imdb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.akashdubey.imdb.db.Constants.DB_NAME;
import static com.akashdubey.imdb.db.Constants.DB_VERSION;

/**
 * Created by homepc on 20-03-2018.
 */

public class DbHelper {

    public static SQLiteDatabase sqLiteDatabase;
    private Context context;
    private Db db;
    public static DbHelper dbHelper;

    public DbHelper(Context context) {
        this.context = context;
        db = new Db(this.context, DB_NAME, null, DB_VERSION);
    }


    //initialize the DB Helper instance just once
    public static DbHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DbHelper(context);
        }
        return dbHelper;
    }

    public void openConnection() {
        try {
            sqLiteDatabase = db.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            sqLiteDatabase = db.getReadableDatabase();
        }


    }

    public void closeConnection() {
        try{
            if(sqLiteDatabase.isOpen()){
                sqLiteDatabase.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
