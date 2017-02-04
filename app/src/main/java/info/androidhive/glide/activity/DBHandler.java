package info.androidhive.glide.activity;

import info.androidhive.glide.DBConst.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static info.androidhive.glide.DBConst.DATABASE_NAME;
import static info.androidhive.glide.DBConst.KEY_ID;
import static info.androidhive.glide.DBConst.KEY_LARGE;
import static info.androidhive.glide.DBConst.KEY_MEDIUM;
import static info.androidhive.glide.DBConst.KEY_NAME;
import static info.androidhive.glide.DBConst.KEY_SMALL;
import static info.androidhive.glide.DBConst.KEY_TIMESTAMP;
import static info.androidhive.glide.DBConst.TABLE_NAME;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;


    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + KEY_NAME + " TEXT," + KEY_SMALL + " TEXT," +
                    KEY_MEDIUM + " TEXT," + KEY_LARGE + " TEXT," + KEY_TIMESTAMP+ " TEXT" +");";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }

}