package com.example.pc.quanlitaichinhcanhan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataModel extends SQLiteOpenHelper {

    public DataModel(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(StringBuilder sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql.toString());
    }

    public Cursor GetData(StringBuilder sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql.toString(),null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
