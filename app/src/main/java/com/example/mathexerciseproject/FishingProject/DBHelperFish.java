package com.example.mathexerciseproject.FishingProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;

public class DBHelperFish extends SQLiteOpenHelper {

    private static final String TABLE_RECORD = "tblfishusers";
    private static final String DATABASENAME = "fishuser.db";
    private static final int DATABASEVERSION = 5;

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_BALANCE = "balance";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_BUCKETSIZE = "bucketsize";
    private static final String COLUMN_FISHAMOUNT = "fishamount";

    private static final String[] allColumns = {COLUMN_ID, COLUMN_BALANCE, COLUMN_NAME, COLUMN_BUCKETSIZE, COLUMN_FISHAMOUNT};

    private static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " +
            TABLE_RECORD + "(" +
            COLUMN_NAME + " TEXT UNIQUE," +
            COLUMN_BALANCE + " INT," +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_FISHAMOUNT + " INT," +
            COLUMN_BUCKETSIZE + " INT);";

    private SQLiteDatabase database;

    public DBHelperFish(@Nullable Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    // creating the database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORD);
        onCreate(sqLiteDatabase);
    }
    // get the user back with the id
    // also possible to return only the id
    public long insert(User user, Context context) {
        database = getWritableDatabase(); // get access to write the database
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getUserName());
        values.put(COLUMN_BALANCE, user.getBalance());
        values.put(COLUMN_FISHAMOUNT, user.getFishAmount());
        values.put(COLUMN_BUCKETSIZE, user.getBucketSize());
        long id = database.insert(TABLE_RECORD, null, values);
        user.setId(id);
        database.close();
        return id;
    }
    // return all rows in table
    public ArrayList<com.example.mathexerciseproject.FishingProject.User> selectAll(){
        database = getReadableDatabase(); // get access to read the database
        ArrayList<com.example.mathexerciseproject.FishingProject.User> users = new ArrayList<>();
        Cursor cursor = database.query(TABLE_RECORD, allColumns, null, null, null, null, null); // cursor points at a certain row
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
                int balance = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_BALANCE));
                int bucketsize = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_BUCKETSIZE));
                int fishamount = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FISHAMOUNT));
                com.example.mathexerciseproject.FishingProject.User user= new com.example.mathexerciseproject.FishingProject.User(name, balance, id, bucketsize,  fishamount);
                users.add(user);
            }
        }
        cursor.close();
        database.close();
        return users;
    }

//        public ArrayList<User> genericSelectById(String id)
//        {
//            String[] vals = { id };
//            // if using the rawQuery
//            // String query = "SELECT * FROM " + TABLE_RECORD + " WHERE " + COLUMN_NAME + " = ?";
//            String column = COLUMN_ID;
//            return select(column,vals);
//        }
//            public ArrayList<User> select(String column,String[] values) {
//            database = getReadableDatabase(); // get access to read the database
//            ArrayList<User> users = new ArrayList<>();
//            // Two options,
//            // since query cannot be created in compile time there is no difference
//            //Cursor cursor = database.rawQuery(query, values);
//            Cursor cursor= database.query(TABLE_RECORD, allColumns, COLUMN_ID +" = ? ", values, null, null, null); // cursor points at a certain row
//            if (cursor.getCount() > 0) {
//                while (cursor.moveToNext()) {
//                    String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
//                    int balance = cursor.getInt(cursor.getColumnIndex(COLUMN_BALANCE));
//                    int bucketSize = cursor.getInt(cursor.getColumnIndex(COLUMN_BUCKETSIZE));
//                    long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
//                    int fishAmount = cursor.getInt(cursor.getColumnIndex(COLUMN_FISHAMOUNT));
//                    User user = new User(name, balance, id, bucketSize, fishAmount);
//                    users.add(user);
//                }// end while
//            } // end if
//            database.close();
//            return users;
//        }
    // update a specific user
    public void update(com.example.mathexerciseproject.FishingProject.User user)
    {
        database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getUserName());
        values.put(COLUMN_BALANCE, user.getBalance());
        values.put(COLUMN_FISHAMOUNT, user.getFishAmount());
        values.put(COLUMN_BUCKETSIZE, user.getBucketSize());
        database.update(TABLE_RECORD, values, COLUMN_ID + "=" + user.getId(), null);
        database.close();
    }
}
