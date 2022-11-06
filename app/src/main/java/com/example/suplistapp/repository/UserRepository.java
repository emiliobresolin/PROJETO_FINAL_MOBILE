package com.example.suplistapp.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import com.example.suplistapp.Data.DBHelper;
import com.example.suplistapp.model.User;

public class UserRepository {

  private final String TAG = "UserRepository";
  private DBHelper dataBaseHelper;

  public UserRepository(Context context) {
    this.dataBaseHelper = new DBHelper(context);
  }

  public User getUserById(int id){
    SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.query(
        "users",
        new String[] {"id", "name", "userLogin", "password"},
        "id = ?",
        new String[] {String.valueOf(id)},
        null, null, null, null);
    if(cursor != null) cursor.moveToFirst();
    User user = new User(
        Integer.parseInt(cursor.getString(0)),
        cursor.getString(1),
        cursor.getString(2),
        cursor.getString(3));
    return user;
  }

  public User getUserByUserLogin(String userLogin){
    SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.query(
        "users",
        new String[] {"id", "name", "userLogin", "password"},
        "userLogin = ?",
        new String[] {String.valueOf(userLogin)},
        null, null, null, null);

    if(cursor != null) cursor.moveToFirst();
    User user;
    try {
      user = new User(
          Integer.parseInt(cursor.getString(0)),
          cursor.getString(1),
          cursor.getString(2),
          cursor.getString(3));
    }
    catch (CursorIndexOutOfBoundsException e) {
      e.getMessage();
      return null;
    }

    return user;
  }

  public void postUser(User user){
    SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("name", user.getName());
    contentValues.put("userLogin", user.getUserLogin());
    contentValues.put("password", user.getPassword());
    sqLiteDatabase.insert("users", null, contentValues);
    sqLiteDatabase.close();
  }

  private User userFromCursor(Cursor cursor) {
    User user = new User(
        cursor.getInt(0),
        cursor.getString(1),
        cursor.getString(2),
        cursor.getString(3));
    return user;
  }
}
