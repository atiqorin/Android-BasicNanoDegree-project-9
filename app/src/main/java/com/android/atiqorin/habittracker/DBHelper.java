package com.android.atiqorin.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atiqorin on 7/4/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = DBContract.FeedEntry.DATABASE_VERSION;
    private static final String DATABASE_ACTIVITY = DBContract.FeedEntry.DATABASE_ACTIVITY;
    private static final String KEY_ID = DBContract.FeedEntry.KEY_ID;
    private static final String KEY_ACTION = DBContract.FeedEntry.KEY_ACTION;
    private static final String KEY_TIME = DBContract.FeedEntry.KEY_TIME;
    private static  final String TABLE_NAME= DBContract.FeedEntry.TABLE_NAME;
    Context context;
    public DBHelper(Context context) {

        super(context, DATABASE_ACTIVITY, null, DATABASE_VERSION);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE "+TABLE_NAME+" ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TIME +" TEXT, " + KEY_ACTION+" TEXT )";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    //for deleting all entries
    public void deleteAllEntries(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }
    //deleting database
    public void deleteDatabase(){
        context.deleteDatabase(DATABASE_ACTIVITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS userHabit" );
        onCreate(db);
    }

    // Adding new User Activites..
    void addActivity(Habit useractivity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TIME, useractivity.getDuration());
        values.put(KEY_ACTION, useractivity.getAction());

        // Insert Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }



    // Get All User Activity
    public List<Habit> getUserActivites() {
        List<Habit> getAllData = new ArrayList<Habit>();
        // Select All
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping and adding to list
        if (cursor.moveToFirst()) {
            do {
                Habit content = new Habit();
                content.setId(Integer.parseInt(cursor.getString(0)));
                content.setDuration(cursor.getString(1));
                content.setAction(cursor.getString(2));
                getAllData.add(content);
            } while (cursor.moveToNext());
        }

        // return User Activites list
        return getAllData;
    }



    // Updating User Activity
    public int updateUserActivity(Habit content) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TIME, content.getDuration());
        values.put(KEY_ACTION, content.getAction());

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(content.getId()) });
    }
}
