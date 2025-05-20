package com.example.mathexerciseproject.FishingProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.IOException;

public class DBHelperFish extends SQLiteOpenHelper {

    private static final String TABLE_RECORD = "tblfishusers";
    private static final String DATABASENAME = "fishuser.db";
    private static final int DATABASEVERSION = 1;

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_BALANCE = "balance";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_BUCKETSIZE = "bucketsize";
    private static final String COLUMN_FISHAMOUNT = "fishamount";

    private static final String[] allColumns = {COLUMN_ID, COLUMN_BALANCE, COLUMN_NAME};

    private static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " +
            TABLE_RECORD + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_NAME + " TEXT UNIQUE," +
            COLUMN_BALANCE + " INT," +
            COLUMN_FISHAMOUNT + " INT," +
            COLUMN_BUCKETSIZE + " INT);";

    private SQLiteDatabase database;

    public DBHelperFish(@Nullable Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    // get the user back with the id
    // also possible to return only the id
    public long insert(User user, Context context) {
        database = getWritableDatabase(); // get access to write the database
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.getId());
        values.put(COLUMN_BALANCE, user.getBalance());
        values.put(COLUMN_NAME, user.getUserName());
        values.put(COLUMN_FISHAMOUNT, user.getFishAmount());
        values.put(COLUMN_BUCKETSIZE, user.getBucketSize());

        // stored as Binary Large OBject ->  BLOB
//        try {
//            values.put(COLUMN_PICTURE, getBytes(context, user.getUri()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        long id = database.insert(TABLE_RECORD, null, values);
        user.setId(id);
        database.close();
        return id;
    }
}
