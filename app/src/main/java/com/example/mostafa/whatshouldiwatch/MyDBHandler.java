package com.example.mostafa.whatshouldiwatch;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movies.db";
    public static final String TABLE_MOVIES = "movies";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MOVIETITLE = "movietitle";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_MOVIES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_MOVIETITLE + " TEXT" + ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }

    public void addMovie(Movies movie) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_MOVIETITLE, movie.get_movieTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_MOVIES, null, values);
        db.close();
    }

    public Movies findMovie(String movieTitle) {
        String query = "Select * FROM " + TABLE_MOVIES + " WHERE " + COLUMN_MOVIETITLE + " =  \"" + movieTitle + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Movies product;

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            product = new Movies(cursor.getString(1));
            product.set_id(Integer.parseInt(cursor.getString(0)));
            cursor.close();
        } else {
            product = null;
        }
        db.close();
        return product;
    }

    public String generateMovieTitle() {
        SQLiteDatabase db = getWritableDatabase();

        String fetchQuery = "SELECT * FROM " + TABLE_MOVIES + " ORDER BY RANDOM() LIMIT 1";

        Cursor cursor = db.rawQuery(fetchQuery, null);
        cursor.moveToFirst();

        String movieTitle =  cursor.getString(1);
        cursor.close();

        db.close();
        return movieTitle;
    }


    public String databaseToString()
    {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        String fetchQuery = "SELECT * FROM " + TABLE_MOVIES + " WHERE 1";

        Cursor cursor = db.rawQuery(fetchQuery, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            if(cursor.getString(1)!=null)
            {
                dbString += cursor.getString(1);
                dbString += "\n";
            }
        }
        cursor.close();

        db.close();

        return  dbString;
    }


}
