package com.example.animecatalogfinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "anime.db";



    private static final String CREATE_ANIME_TABLE = "CREATE TABLE " + DatabaseInfo.ANIME_TABLE + "(" + DatabaseInfo.ANIME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DatabaseInfo.ANIME_NAME + " TEXT, " + DatabaseInfo.ANIME_GENRES + " TEXT, " + DatabaseInfo.ANIME_SYNOPSIS + " TEXT, " + DatabaseInfo.ANIME_DATE + " TEXT, " + DatabaseInfo.ANIME_CHARACTERS + " TEXT, " + DatabaseInfo.ANIME_IMAGE + " TEXT)";
    private static final String DELETE_ANIME_TABLE = "DROP TABLE IF EXISTS " + DatabaseInfo.ANIME_TABLE;

    public DataBaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ANIME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_ANIME_TABLE);
        onCreate(db);
    }

}
