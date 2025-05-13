package com.example.mathexerciseproject.FishingProject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperFish extends SQLiteOpenHelper {

    private static final String TABLE_RECORD = "tblusers";
    private static final String DATABASENAME = "user.db";
    private static final int DATABASEVERSION = 1;

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_BALANCE = "Balance";
    private static final String COLUMN_NAME = "name";

    private static final String[] allColums = {COLUMN_ID, COLUMN_BALANCE, COLUMN_NAME};

    private static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " +
            TABLE_RECORD + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_NAME + " TEXT UNIQUE," +
            COLUMN_BALANCE + " INT);";

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
}
