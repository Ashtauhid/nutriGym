package com.example.mazhar.nutrigym;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "food.db";
    public static final String TABLE_NAME = "food_item";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FOOD_NAME";
    public static final String COL_3 = "CALORIE";
    public static final String COL_4 = "CATAGORY";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,FOOD_NAME TEXT, CALORIE DOUBLE, CATAGORY TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String food_name, Double calories, String catagory) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, food_name);
        contentValues.put(COL_3, calories);
        contentValues.put(COL_4, catagory);
        long res = db.insert(TABLE_NAME, null, contentValues);
        return !(res == -1);
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Cursor getSearchData(String name){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where "+COL_2+" LIKE '%"+name+"%'",null);

        return res;
    }
}

