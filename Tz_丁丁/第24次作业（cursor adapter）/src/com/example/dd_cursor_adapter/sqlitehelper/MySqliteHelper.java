package com.example.dd_cursor_adapter.sqlitehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteHelper extends SQLiteOpenHelper {

	public MySqliteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// ½¨±í
		db.beginTransaction();
		try {
			db.execSQL("create table words(_id integer primary key autoincrement,word varchar(20))");
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
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
