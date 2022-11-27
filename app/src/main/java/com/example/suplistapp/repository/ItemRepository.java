package com.example.suplistapp.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import com.example.suplistapp.Data.DBHelper;
import com.example.suplistapp.model.Item;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository
{
    private final String TAG = "ItemRepository";
    private DBHelper dataBaseHelper;

    public ItemRepository(Context context) {
        this.dataBaseHelper = new DBHelper(context);
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM items";
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                Item item = new Item(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    Double.parseDouble(cursor.getString(3)),
                    Integer.parseInt(cursor.getString(4)),
                    cursor.getString(5)
                );
                items.add(item);
            } while (cursor.moveToNext());
        }

        return items;
    }

    public List<Item> getAllDailyItems() {
        List<Item> dailyItems = new ArrayList<>();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query(
            "items",
            new String[] {"id", "productName", "expirationDate", "productPrice", "quantity", "listType"},
            "listType = ?",
            new String[] {"daily"},
            null, null, null, null
        );
        if(cursor.moveToFirst()) {
            do {
                Item item = new Item(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    Double.parseDouble(cursor.getString(3)),
                    Integer.parseInt(cursor.getString(4)),
                    cursor.getString(5)
                );
                dailyItems.add(item);
            } while (cursor.moveToNext());
        }

        return dailyItems;
    }

    public List<Item> getAllWeeklyItems() {
        List<Item> weeklyItems = new ArrayList<>();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query(
            "items",
            new String[] {"id", "productName", "expirationDate", "productPrice", "quantity", "listType"},
            "listType = ?",
            new String[] {"weekly"},
            null, null, null, null
        );
        if(cursor.moveToFirst()) {
            do {
                Item item = new Item(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    Double.parseDouble(cursor.getString(3)),
                    Integer.parseInt(cursor.getString(4)),
                    cursor.getString(5)
                );
                weeklyItems.add(item);
            } while (cursor.moveToNext());
        }

        return weeklyItems;
    }

    public List<Item> getAllMonthlyItems() {
        List<Item> monthlyItems = new ArrayList<>();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query(
            "items",
            new String[] {"id", "productName", "expirationDate", "productPrice", "quantity", "listType"},
            "listType = ?",
            new String[] {"monthly"},
            null, null, null, null
        );
        if(cursor.moveToFirst()) {
            do {
                Item item = new Item(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    Double.parseDouble(cursor.getString(3)),
                    Integer.parseInt(cursor.getString(4)),
                    cursor.getString(5)
                );
                monthlyItems.add(item);
            } while (cursor.moveToNext());
        }

        return monthlyItems;
    }

    public Item getItemById(int id){
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                "items",
                new String[] {"id", "productName", "expirationDate", "productPrice", "quantity", "listType"},
                "id = ?",
                new String[] {String.valueOf(id)},
                null, null, null, null);
        if(cursor != null) cursor.moveToFirst();
        Item item = new Item(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                Double.parseDouble(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)),
                cursor.getString(5)
        );

        return item;
    }

    public Item getItemByProductName(String productName){
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                "items",
                new String[] {"id", "productName", "expirationDate", "productPrice", "quantity", "listType"},
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
                    Integer.parseInt(cursor.getString(4)),
                    cursor.getString(5)
            );
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
        contentValues.put("listType", item.getListType());
        sqLiteDatabase.insert("items", null, contentValues);
        sqLiteDatabase.close();
    }

    public void deleteItem(Item item){
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        sqLiteDatabase.delete(
            "items",
            "id = ?",
            new String[] {String.valueOf(item.getId())}
        );
        sqLiteDatabase.close();
    }

    public void updateItem(Item item){
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("productName", item.getProductName());
        contentValues.put("expirationDate", item.getExpirationDate());
        contentValues.put("productPrice", item.getProductPrice());
        contentValues.put("quantity", item.getQuantity());
        contentValues.put("listType", item.getListType());
        sqLiteDatabase.update(
            "items", contentValues,
            "id = ?",
            new String[] { String.valueOf(item.getId())}
            );
        sqLiteDatabase.close();
    }

    private Item itemFromCursor(Cursor cursor) {
        Item item = new Item(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                Double.parseDouble(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)),
                cursor.getString(5)
        );
        return item;
    }
}