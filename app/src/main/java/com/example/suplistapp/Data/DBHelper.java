package com.example.suplistapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper
{
    public static final String DBNAME = "Login.db";
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        String stm =  "create table users (id INTEGER PRIMARY KEY, name TEXT," +
            "userLogin TEXT, password TEXT);";
        MyDB.execSQL(stm);
        String stn = "create table items (id INTEGER PRIMARY KEY, productName TEXT," +
                "expirationDate TEXT, productPrice TEXT, quantity TEXT);";
        MyDB.execSQL(stn);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists items");
    }
}
