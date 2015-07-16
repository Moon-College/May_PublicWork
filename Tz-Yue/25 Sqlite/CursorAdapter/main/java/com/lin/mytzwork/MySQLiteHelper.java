package com.lin.mytzwork;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/7/14.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.beginTransaction();

        try {
            db.execSQL("create table words(_id integer primary key autoincrement,word text)");

            ContentValues values = new ContentValues();
            values.put("word", "cancel");
            db.insert("words", null, values);

            values.clear();
            values.put("word", "cell");
            db.insert("words", null, values);

            values.clear();
            values.put("word", "car");
            db.insert("words", null, values);

            values.clear();
            values.put("word", "cat");
            db.insert("words", null, values);

            values.clear();
            values.put("word", "cut");
            db.insert("words", null, values);

            values.clear();
            values.put("word", "call");
            db.insert("words", null, values);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
