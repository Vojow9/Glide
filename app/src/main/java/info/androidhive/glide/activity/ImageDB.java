package info.androidhive.glide.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import info.androidhive.glide.model.Image;

import static info.androidhive.glide.DBConst.KEY_LARGE;
import static info.androidhive.glide.DBConst.KEY_MEDIUM;
import static info.androidhive.glide.DBConst.KEY_NAME;
import static info.androidhive.glide.DBConst.KEY_SMALL;
import static info.androidhive.glide.DBConst.KEY_TIMESTAMP;
import static info.androidhive.glide.DBConst.TABLE_NAME;

/**
 * Created by Wojtek on 04.02.2017.
 */

public class ImageDB {

    private DBHandler dbHandler ;

    public ImageDB (Context context) {
        this.dbHandler = new DBHandler(context);
    }

    public void addImage(Image image) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, image.getName());
            values.put(KEY_SMALL, image.getSmall());
            values.put(KEY_MEDIUM, image.getMedium());
            values.put(KEY_LARGE, image.getLarge());
            values.put(KEY_TIMESTAMP, image.getTimestamp());

            db.insert(TABLE_NAME, null, values);
        } finally {
            db.close();
        }
    }

    public Image getImage(int position) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        try {
            String[] projection = { KEY_NAME, KEY_SMALL, KEY_MEDIUM, KEY_LARGE, KEY_TIMESTAMP };

            String sortOrder = KEY_TIMESTAMP + " DESC";

            Cursor cursor = db.query(TABLE_NAME,  // The table to query
                    projection,                   // The columns to return
                    null,                         // The columns for the WHERE clause
                    null,                         // The values for the WHERE clause
                    null,                         // don't group the rows
                    null,                         // don't filter by row groups
                    sortOrder                     // The sort order
            );

            return getImageFromCursor(position, cursor);
        } finally {
            db.close();
        }
    }

    private Image getImageFromCursor(int position, Cursor cursor) {
        try {
            cursor.moveToPosition(position);

            String name = cursor.getString(0);
            String small = cursor.getString(1);
            String medium = cursor.getString(2);
            String large = cursor.getString(3);
            String timestamp = cursor.getString(3);

            cursor.close();

            return new Image(name, small,medium,large,timestamp);
        } finally {
            cursor.close();
        }
    }

    public int getImageNumber() {
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        try {
            return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME, null, null);
        } finally {
            db.close();
        }
    }
}
