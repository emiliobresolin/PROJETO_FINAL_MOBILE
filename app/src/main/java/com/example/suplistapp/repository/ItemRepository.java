package com.example.suplistapp.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import com.example.suplistapp.Data.DBHelper;
import com.example.suplistapp.model.Item;

import java.sql.Date;

public class ItemRepository
{
    private final String TAG = "ItemRepository";
    private DBHelper dataBaseHelper;

    public ItemRepository(Context context) {
        this.dataBaseHelper = new DBHelper(context);
    }

    public Item getItemById(int id){
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                "items",
                new String[] {"id", "productName", "expirationDate", "productPrice", "quantity"},
                "id = ?",
                new String[] {String.valueOf(id)},
                null, null, null, null);
        if(cursor != null) cursor.moveToFirst();
        Item item = new Item(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                Double.parseDouble(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)));

        return item;
    }

    public Item getItemByProductName(String productName){
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                "items",
                new String[] {"id", "productName", "expirationDate", "productPrice", "quantity"},
                "productName = ?",
                new String[] {String.valueOf(productName)},
                null, null, null, null);

        if(cursor != null) cursor.moveToFirst();
        Item item;
        try {
            item = new Item(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    Double.parseDouble(cursor.getString(3)),
                    Integer.parseInt(cursor.getString(4)));
        }
        catch (CursorIndexOutOfBoundsException e) {
            e.getMessage();
            return null;
        }

        return item;
    }

    public void postItem(Item item){
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("productName", item.getProductName());
        contentValues.put("expirationDate", item.getExpirationDate());
        contentValues.put("productPrice", item.getProductPrice());
        contentValues.put("quantity", item.getQuantity());
        sqLiteDatabase.insert("items", null, contentValues);
        sqLiteDatabase.close();
    }

    private Item userFromCursor(Cursor cursor) {
        Item item = new Item(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                Double.parseDouble(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)));
        return item;
    }
}